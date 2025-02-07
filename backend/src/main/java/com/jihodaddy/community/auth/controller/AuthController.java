package com.jihodaddy.community.auth.controller;

import com.jihodaddy.community.auth.dto.request.LoginRequest;
import com.jihodaddy.community.auth.dto.request.SignUpRequest;
import com.jihodaddy.community.auth.dto.response.LoginResponse;
import com.jihodaddy.community.auth.service.AuthService;
import com.jihodaddy.community.common.response.ApiResponse;
import com.jihodaddy.community.domain.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.createSuccess(authService.login(request.email(), request.password())));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Boolean>> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(ApiResponse.createSuccess(authService.logout(request, response)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Member>> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(ApiResponse.createSuccess(authService.signUp(request)));
    }
}
