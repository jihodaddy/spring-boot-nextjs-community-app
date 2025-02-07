package com.jihodaddy.community.domain.member.entity;

import com.jihodaddy.community.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private LocalDateTime loginLastDate;

    @Builder
    public Member(String email, String password, SocialType socialType, LocalDateTime loginLastDate) {
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.loginLastDate = loginLastDate;
    }

    public void updateLoginDate() {
        this.loginLastDate = LocalDateTime.now();
    }
}
