package spring.shopapp.services.auth;

import com.nimbusds.jose.JOSEException;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.request.LogoutRequest;
import spring.shopapp.dtos.request.RefreshRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.models.User;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(User user);
    void logout(LogoutRequest request);
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
