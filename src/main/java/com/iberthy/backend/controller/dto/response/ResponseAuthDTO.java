package com.iberthy.backend.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseAuthDTO {

    private String username;
    private List<String> roles;
    private String token;

    public ResponseAuthDTO(UserDetails userDetails, String token){
        this.username = userDetails.getUsername();
        this.token = "Bearer " + token;
        this.roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
    }

}
