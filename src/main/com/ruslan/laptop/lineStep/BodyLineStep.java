package com.ruslan.laptop.lineStep;

import com.ruslan.laptop.part.Body;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProductPart;

public class BodyLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Creating Body");
        return new Body();
    }
}
