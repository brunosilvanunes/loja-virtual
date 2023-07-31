package br.com.treinamento.lojavirtual.controller;

import br.com.treinamento.lojavirtual.model.dto.AuthorizationDTO;
import br.com.treinamento.lojavirtual.model.dto.UserDTO;
import br.com.treinamento.lojavirtual.model.entities.User;
import br.com.treinamento.lojavirtual.service.TokenService;
import br.com.treinamento.lojavirtual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @ResponseBody
    public AuthorizationDTO login(@RequestBody UserDTO userDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        AuthorizationDTO authorizationDTO = new AuthorizationDTO();

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();
        var token = tokenService.gerarToken(user);

        authorizationDTO.setToken(token);

        return authorizationDTO;

    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return service.register(userDTO);
    }
}
