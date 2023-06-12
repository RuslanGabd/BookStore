package com.ruslan.laptop;

import com.ruslan.service.ILineStep;
import com.ruslan.service.IProductPart;

public class LineStepLaptop implements ILineStep {

    @Override
    public IProductPart buildProductPart(String name) {
        System.out.println("Build product part for laptop " + name + " started");
        IProductPart result = new ProductPartLaptop();
        System.out.println("Setting product part " + name);
        result.setName(name);
        System.out.println("Build product part finished " + name);
        return result;
    }
}
