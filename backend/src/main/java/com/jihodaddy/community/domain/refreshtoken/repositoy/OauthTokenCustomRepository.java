package com.jihodaddy.community.domain.refreshtoken.repositoy;

import com.jihodaddy.community.domain.refreshtoken.entity.OauthToken;

import java.time.LocalDateTime;

public interface OauthTokenCustomRepository {
    OauthToken validateRefreshToken(String accessToken, String refreshToken, LocalDateTime currentDate);
}
