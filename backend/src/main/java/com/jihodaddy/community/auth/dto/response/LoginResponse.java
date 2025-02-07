package com.jihodaddy.community.auth.dto.response;

public record LoginResponse(
        String email,
        String accessToken,
        String refreshToken
) {
}
