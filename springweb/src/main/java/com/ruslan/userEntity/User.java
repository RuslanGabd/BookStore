package com.ruslan.userEntity;


import com.ruslan.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "bookstore")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<Integer> implements Serializable {



    @Size(min = 3, message = "Not less than 3 characters")
    @NotNull
    @Column(name = "username")
    private String username;

    @Size(min = 6, message = "Not less than 6 characters")
    @NotNull
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
