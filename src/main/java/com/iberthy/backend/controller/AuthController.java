package com.iberthy.backend.controller;

import com.iberthy.backend.service.dto.request.RequestAuthDTO;
import com.iberthy.backend.service.dto.response.ResponseAuthDTO;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.service.TokenService;
import com.iberthy.backend.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Auth", description = " ")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ApiOperation("Autenticação dos usuários da API")
    public ResponseEntity<ResponseAuthDTO> auth(@RequestBody @Validated RequestAuthDTO auth){

        UserDetails user;

        try {
            var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

            user = (UserDetails) authenticate.getPrincipal();

        } catch (Exception ex) {
            throw new GenericException(Message.authInvalid);
        }

        return ResponseEntity.ok(new ResponseAuthDTO(user, tokenService.generateToken(auth.getUsername())));
    }

}
