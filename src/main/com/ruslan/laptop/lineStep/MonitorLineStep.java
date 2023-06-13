package com.ruslan.laptop.lineStep;

import com.ruslan.laptop.part.Monitor;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProductPart;

public class MonitorLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Creating Monitor");
        return new Monitor();
    }
}
