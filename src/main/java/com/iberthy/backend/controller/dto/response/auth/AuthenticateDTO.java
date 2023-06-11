package com.iberthy.backend.controller.dto.response.auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticateDTO {
    private String username;
    private List<String> roles;
    private String token;
}
