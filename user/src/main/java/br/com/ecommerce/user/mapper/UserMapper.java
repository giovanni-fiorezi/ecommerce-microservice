package br.com.ecommerce.user.mapper;

import br.com.ecommerce.user.dto.UserDto;
import br.com.ecommerce.user.entity.User;

/**
 * Classe responsavel por converter Entidade em DTO e vice-versa
 */

public class UserMapper {

    public static UserDto entityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setLogin(user.getLogin());
        return dto;
    }

    public static User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        return entity;
    }

}
