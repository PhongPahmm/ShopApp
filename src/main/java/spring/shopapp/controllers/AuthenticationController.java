package spring.shopapp.controllers;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.AuthenticationRequest;
import spring.shopapp.dtos.request.LogoutRequest;
import spring.shopapp.dtos.request.RefreshRequest;
import spring.shopapp.dtos.response.AuthenticationResponse;
import spring.shopapp.services.auth.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
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
    @PostMapping("/log-out")
    public ApiResponse<String> logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request);
        return ApiResponse.<String>builder()
                .data("Token has been invalidated")
                .message("Logout successful")
                .build();
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> logout(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.refreshToken(request))
                .message("Refresh successful")
                .build();
    }

}
