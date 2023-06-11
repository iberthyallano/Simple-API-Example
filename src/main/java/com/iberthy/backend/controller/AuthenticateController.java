package com.iberthy.backend.controller;

import com.iberthy.backend.controller.Mapper.AuthenticateMapper;
import com.iberthy.backend.controller.dto.request.auth.AuthenticateGetDTO;
import com.iberthy.backend.controller.dto.response.auth.AuthenticateDTO;
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
@Api(tags = "Authenticate", description = " ")
public class AuthenticateController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticateMapper authenticateMapper;

    @PostMapping
    @ApiOperation("Autenticação dos usuários da API")
    public ResponseEntity<AuthenticateDTO> authenticate(@RequestBody @Validated AuthenticateGetDTO auth){
        UserDetails user;
        String token;
        try {
            var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
            user = (UserDetails) authenticate.getPrincipal();
            token = tokenService.generateToken(auth.getUsername());
        } catch (Exception ex) {
            throw new GenericException(Message.authInvalid);
        }
        return ResponseEntity.ok(authenticateMapper.mapAuthDTO(user, token));
    }

}
