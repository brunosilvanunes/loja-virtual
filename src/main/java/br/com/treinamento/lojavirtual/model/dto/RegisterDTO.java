package br.com.treinamento.lojavirtual.model.dto;

import br.com.treinamento.lojavirtual.enumeration.RoleEnum;

public record RegisterDTO(String username, String password, RoleEnum role) {
}
