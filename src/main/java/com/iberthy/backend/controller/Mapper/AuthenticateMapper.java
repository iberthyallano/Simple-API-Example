package com.iberthy.backend.controller.Mapper;
import com.iberthy.backend.controller.dto.response.auth.AuthenticateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class AuthenticateMapper {

    public AuthenticateDTO mapAuthDTO(UserDetails userDetails, String token){
        var auth = new AuthenticateDTO();

        auth.setUsername(userDetails.getUsername());
        auth.setToken("Bearer " + token);

        var roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
        auth.setRoles(roles);

        return auth;
    }
}
