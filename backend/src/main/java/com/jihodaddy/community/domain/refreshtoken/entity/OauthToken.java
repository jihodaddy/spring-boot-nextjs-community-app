package com.jihodaddy.community.domain.refreshtoken.entity;

import com.jihodaddy.community.common.entity.BaseEntity;
import com.jihodaddy.community.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class OauthToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = true, length = 200)
    private String accessToken;

    @Column(nullable = true, length = 200)
    private String refreshToken;

    @Column
    private LocalDateTime expireDate;

    @Builder
    public OauthToken(Member member, String accessToken, String refreshToken, LocalDateTime expireDate) {
        this.member = member;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireDate = expireDate;
    }

    public void addTokenValueAndExpireDate(String accessToken, String refreshToken, LocalDateTime expireDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireDate = expireDate;
    }

    public static OauthToken toEntity(String accessToken, String refreshToken, Member member, LocalDateTime expireDate) {
        return OauthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .member(member)
                .expireDate(expireDate)
                .build();
    }
}
