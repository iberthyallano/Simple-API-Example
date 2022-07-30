package com.iberthy.backend.controller;

import com.iberthy.backend.controller.dto.request.RequestAuthDTO;
import com.iberthy.backend.controller.dto.response.ResponseAuthDTO;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.service.TokenService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<ResponseAuthDTO> auth(@RequestBody @Validated RequestAuthDTO authDTO){

        UserDetails user;

        try {
            var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));

            user = (UserDetails) authenticate.getPrincipal();

        } catch (Exception ex) {
            throw new GenericException(Message.authInvalid);
        }

        return ResponseEntity.ok(new ResponseAuthDTO(user, tokenService.generateToken(authDTO.getUsername())));
    }

}
