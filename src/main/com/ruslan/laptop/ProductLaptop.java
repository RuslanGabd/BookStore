package com.ruslan.laptop;

import com.ruslan.laptop.part.Body;
import com.ruslan.service.IProduct;
import com.ruslan.service.IProductPart;

public class ProductLaptop implements IProduct {
    private IProductPart body;
    private IProductPart motherboard;
    private IProductPart monitor;

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.body = productPart;
        System.out.println("Laptop Body installed");
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        this.motherboard = productPart;
        System.out.println("Laptop Motherboard installed");
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.monitor = productPart;
        System.out.println("Laptop Monitor installed");
    }
}
