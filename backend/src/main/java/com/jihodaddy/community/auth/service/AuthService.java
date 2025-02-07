package com.jihodaddy.community.auth.service;

import com.jihodaddy.community.auth.dto.request.SignUpRequest;
import com.jihodaddy.community.auth.dto.response.LoginResponse;
import com.jihodaddy.community.auth.security.jwt.JwtUtil;
import com.jihodaddy.community.auth.security.jwt.UserDetailsServiceImpl;
import com.jihodaddy.community.domain.member.entity.Member;
import com.jihodaddy.community.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginResponse login(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var accessToken = jwtUtil.generateAccessToken((UserDetails) authentication.getPrincipal());
        var refreshToken = "refreshToken";
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return new LoginResponse(username, accessToken, refreshToken);
    }

    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        if (auth.getPrincipal() != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            auth.setAuthenticated(false);
            return true;
        }
        return false;
    }

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
