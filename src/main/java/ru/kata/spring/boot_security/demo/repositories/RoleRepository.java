package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entity.Role;


public interface RoleRepository {
    Role findRoleById(long id);
}
