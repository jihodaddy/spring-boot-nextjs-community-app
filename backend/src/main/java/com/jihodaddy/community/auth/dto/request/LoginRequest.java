package com.jihodaddy.community.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식이 올 바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀 번호를 입력 해 주세요.")
        String password
) {
}
