package spring.shopapp.services.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.request.LogoutRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.models.Role;
import spring.shopapp.models.Token;
import spring.shopapp.models.User;
import spring.shopapp.repositories.TokenRepository;
import spring.shopapp.repositories.UserRepository;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TokenRepository tokenRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("My domain") // Website's domain
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(1, ChronoUnit.HOURS)
                        .toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        Role userRole = user.getRole();
        if(userRole != null) {
            stringJoiner.add(user.getRole().getName());
        }
        return "ROLE_" + stringJoiner;
    }
    public void logout(LogoutRequest request) {
        try {
            log.info("Processing logout request for token: {}", request.getToken());

            var signToken = verifyToken(request.getToken());
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            String username = signToken.getJWTClaimsSet().getSubject();
            String jit = signToken.getJWTClaimsSet().getJWTID();

            log.info("Logout request for user: {}", username);

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            // Kiểm tra nếu token đã bị thu hồi
            var existingToken = tokenRepository.findByToken(jit);
            if (existingToken.isPresent()) {
                log.warn("Token has already been revoked: {}", jit);
                return;
            }

            // Lưu token vào DB để đánh dấu là revoked
            Token invalidatedToken = Token.builder()
                    .expirationDate(expiryTime)
                    .token(jit)
                    .expired(true)
                    .revoked(true) // Quan trọng: Đánh dấu token là revoked
                    .tokenType("LOG_OUT")
                    .user(user)
                    .build();

            tokenRepository.save(invalidatedToken);
            log.info("Token revoked successfully for user: {}", username);
        } catch (ParseException e) {
            log.error("Invalid token format: {}", e.getMessage());
            throw new RuntimeException("Invalid token");
        } catch (AppException e) {
            log.error("Authentication error: {}", e.getMessage());
            throw e;
        } catch (JOSEException e) {
            throw new RuntimeException("Token verification failed", e);
        }
    }



    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        String jit = signedJWT.getJWTClaimsSet().getJWTID();

        var verified = signedJWT.verify(verifier);
        if (!(verified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Kiểm tra xem token đã bị thu hồi chưa
        var revokedToken = tokenRepository.findByToken(jit);
        if (revokedToken.isPresent() && revokedToken.get().getRevoked()) {
            log.warn("Attempt to use revoked token: {}", jit);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

}
