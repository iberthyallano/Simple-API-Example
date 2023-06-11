package com.iberthy.backend.controller.dto.request.auth;

import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticateGetDTO {
    @NotBlank(message = Message.userNameNotBlank)
    private String username;

    @NotBlank(message = Message.passwordNotBlank)
    private String password;
}
