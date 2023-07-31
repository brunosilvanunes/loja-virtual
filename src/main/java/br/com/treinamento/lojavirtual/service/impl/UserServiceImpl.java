package br.com.treinamento.lojavirtual.service.impl;

import br.com.treinamento.lojavirtual.model.dto.UserDTO;
import br.com.treinamento.lojavirtual.model.entities.User;
import br.com.treinamento.lojavirtual.repository.UserRepository;
import br.com.treinamento.lojavirtual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user = new User();

        String hash = new BCryptPasswordEncoder().encode(userDTO.getPassword());

        user.setUsername(userDTO.getUsername());
        user.setPassword(hash);

        repository.save(user);

        return userDTO;
    }
}
