package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.services.auth.AuthenticationService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("log-in")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.authenticate(request))
                .message("Successfully logged in")
                .build();
    }
}
