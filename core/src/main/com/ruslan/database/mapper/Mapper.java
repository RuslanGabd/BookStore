package com.ruslan.database.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}

