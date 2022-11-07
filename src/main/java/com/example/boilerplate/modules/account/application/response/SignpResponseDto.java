package com.example.boilerplate.modules.account.application.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class SignpResponseDto {
    private String email;

    private String nickname;

    private String password;

    private boolean admin;
}
