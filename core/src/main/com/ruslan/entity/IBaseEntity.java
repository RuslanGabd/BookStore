package com.ruslan.entity;

import java.io.Serializable;

public  interface IBaseEntity<T extends Serializable> {

    void setId(T id);



    T getId();
}

