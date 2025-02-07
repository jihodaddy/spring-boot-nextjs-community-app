package com.jihodaddy.community.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @Email(message = "이메일을 입력해 주세요.")
        String email,
        @NotBlank(message = "비밀 번호를 입력해 주세요.")
        String password
) {
}
