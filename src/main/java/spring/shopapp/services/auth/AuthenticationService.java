package spring.shopapp.services.auth;

import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
