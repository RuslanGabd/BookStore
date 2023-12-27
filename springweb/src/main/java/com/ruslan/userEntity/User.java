package com.ruslan.userEntity;


import com.ruslan.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "bookstore")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<Integer> implements Serializable {


    @Size(min = 3, message = "Not less than 3 characters")
    private String username;

    @Size(min = 6, message = "Not less than 6 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public User(String userName, String password) {
        super();
    }
}
