package com.ruslan.laptop;

import com.ruslan.service.IProductPart;

public class ProductPartLaptop implements IProductPart {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
