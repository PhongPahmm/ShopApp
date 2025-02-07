package spring.shopapp.services.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return AuthenticationResponse.builder()
                .authenticated(true)
                .build();
    }
}
