package com.ruslan.userEntity;

import com.ruslan.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "role")
public class Role extends BaseEntity<Integer> {
    private String name;
}
