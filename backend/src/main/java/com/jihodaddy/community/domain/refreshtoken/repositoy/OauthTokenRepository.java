package com.jihodaddy.community.domain.refreshtoken.repositoy;

import com.jihodaddy.community.domain.member.entity.Member;
import com.jihodaddy.community.domain.refreshtoken.entity.OauthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {

    OauthToken findByMember(Member member);
}
