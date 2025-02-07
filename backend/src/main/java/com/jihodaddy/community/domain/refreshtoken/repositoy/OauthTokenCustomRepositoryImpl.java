package com.jihodaddy.community.domain.refreshtoken.repositoy;

import com.jihodaddy.community.domain.refreshtoken.entity.OauthToken;
import com.jihodaddy.community.domain.refreshtoken.entity.QOauthToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.jihodaddy.community.domain.member.entity.QMember.member;
import static com.jihodaddy.community.domain.refreshtoken.entity.QOauthToken.oauthToken;

@Repository
@RequiredArgsConstructor
public class OauthTokenCustomRepositoryImpl implements OauthTokenCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public OauthToken validateRefreshToken(String accessToken, String refreshToken, LocalDateTime currentDate) {
        return queryFactory.selectFrom(oauthToken)
                .leftJoin(oauthToken.member, member)
                .where(oauthToken.refreshToken.eq(accessToken)
                        .and(oauthToken.refreshToken.eq(refreshToken))
                        .and(oauthToken.expireDate.after(currentDate)))
                .fetchFirst();
    }
}
