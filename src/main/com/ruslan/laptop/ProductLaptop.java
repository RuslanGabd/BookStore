package com.ruslan.laptop;

import com.ruslan.service.IProduct;
import com.ruslan.service.IProductPart;

public class ProductLaptop implements IProduct {


    @Override
    public void installFirstPart(IProductPart productPart) {
        System.out.println("Install first part: " + productPart.getName());
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        System.out.println("Install second part:" + productPart.getName());
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        System.out.println("Install third part:" + productPart.getName());
    }
}
