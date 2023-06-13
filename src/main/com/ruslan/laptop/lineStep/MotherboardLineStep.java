package com.ruslan.laptop.lineStep;

import com.ruslan.laptop.part.Motherboard;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProductPart;

public class MotherboardLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Creating Motherboard");
        return new Motherboard();
    }
}
