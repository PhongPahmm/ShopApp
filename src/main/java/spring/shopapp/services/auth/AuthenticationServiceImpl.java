package spring.shopapp.services.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.models.Role;
import spring.shopapp.models.User;
import spring.shopapp.repositories.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
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
}
