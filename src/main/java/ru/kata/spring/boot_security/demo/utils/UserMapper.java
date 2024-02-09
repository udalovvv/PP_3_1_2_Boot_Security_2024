package ru.kata.spring.boot_security.demo.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.User;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {

//            UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
        UserDTO userToUserDto(User user);
        User userDtoToUser(UserDTO userDto);

}
