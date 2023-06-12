package com.ruslan.laptop;

import com.ruslan.service.IAssemblyLine;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProduct;
import com.ruslan.service.IProductPart;

public class AssemblyLineLaptop implements IAssemblyLine {

    ILineStep lineStep = new LineStepLaptop();

    @Override
    public IProduct assembleProduct(IProduct product) {
        System.out.println("Assembly line auto product started");
        IProductPart firstPart = lineStep.buildProductPart("Body");
        product.installFirstPart(firstPart);
        IProductPart secondPart = lineStep.buildProductPart("MotherBoard");
        product.installSecondPart(secondPart);
        IProductPart thirdPart = lineStep.buildProductPart("Monitor");
        product.installThirdPart(thirdPart);
        System.out.println("Assembly line auto product finished");
        return product;
    }
}
