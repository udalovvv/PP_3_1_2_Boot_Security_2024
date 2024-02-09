package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class UserDTO {

    private long id;

    @NonNull
    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина имени доджна быть от 2 до 20 символов")
    private String firstName;

    @NonNull
    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина фамилии доджна быть от 2 до 20 символов")
    private String lastName;

    @NonNull
    @Min(value = 0, message = "Возрат не должен быть меньше 0")
    @Max(value = 150, message = "Возраст не должен быть больше 150")
    private byte age;

    @NonNull
    @NotEmpty(message = "Не может быть пустым")
    @Email(message = "Email не корректен")
    private String email;

    @NotEmpty(message = "Не может быть пустым")
    private String  password;

    @NonNull
    private Set<Role> roles;
}
