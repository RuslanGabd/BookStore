package com.ruslan.userEntity;


import com.ruslan.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "bookstore")

public class User extends BaseEntity<Integer> implements Serializable {


    @Size(min = 3, message = "Not less than 3 characters")
    @Column(name = "username")
    private String username;

    @Size(min = 6, message = "Not less than 6 characters")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "UserID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "RoleID", referencedColumnName = "id"))
    private List<Role> listRoles ;



}
