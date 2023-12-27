package com.ruslan.userEntity;

import com.ruslan.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles extends BaseEntity<Integer> {
    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "`RoleID`")
    private Role role;



}
