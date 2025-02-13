package spring.shopapp.services.auth;

import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.request.LogoutRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.models.User;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(User user);
    void logout(LogoutRequest request);
}
