package com.criscahub.erp_lite.controllers.auth;

import com.criscahub.erp_lite.dtos.AuthRequest;
import com.criscahub.erp_lite.dtos.AuthResponse;
import com.criscahub.erp_lite.security.dtos.AppUserDetails;
import com.criscahub.erp_lite.security.services.JWTServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JWTServices jwtServices;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> loging(@RequestBody AuthRequest authRequest){
        final var authetication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(),authRequest.password()
                )
        );
        final var userDetails = (AppUserDetails) authetication.getPrincipal();
        assert userDetails != null;
        final var token = jwtServices.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));


    }

}
