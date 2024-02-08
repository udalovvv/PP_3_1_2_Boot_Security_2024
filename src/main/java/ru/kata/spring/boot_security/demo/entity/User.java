package ru.kata.spring.boot_security.demo.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина имени доджна быть от 2 до 20 символов")
    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина фамилии доджна быть от 2 до 20 символов")
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 0, message = "Возрат не должен быть меньше 0")
    @Max(value = 150, message = "Возраст не должен быть больше 150")
    @NonNull
    @Column(name = "age")
    private byte age;

    @NotEmpty(message = "Не может быть пустым")
    @Email(message = "Email не корректен")
    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Не может быть пустым")
    @NonNull
    @Column(name = "password")
    private String  password;

    @NonNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public void addRole(Role role) {
        roles.add(role);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
