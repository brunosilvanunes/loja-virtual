package br.com.treinamento.lojavirtual.repositories;

import br.com.treinamento.lojavirtual.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
