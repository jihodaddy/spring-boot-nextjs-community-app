package com.jihodaddy.community.domain.member.service;

import com.jihodaddy.community.auth.dto.request.SignUpRequest;
import com.jihodaddy.community.domain.member.entity.Member;
import com.jihodaddy.community.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(SignUpRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException();
        }
        return memberRepository.save(Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password())).build());

    }
}
