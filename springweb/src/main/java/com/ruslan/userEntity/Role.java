package com.ruslan.userEntity;

import com.ruslan.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "Role")
public class Role extends BaseEntity<Integer> {
    @Column(name = "role_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "listRoles")
    private List<User> listUser;
}
