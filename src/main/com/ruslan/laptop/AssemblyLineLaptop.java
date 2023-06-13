package com.ruslan.laptop;

import com.ruslan.service.IAssemblyLine;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProduct;
import com.ruslan.service.IProductPart;

import java.util.ArrayList;
import java.util.List;

public class AssemblyLineLaptop implements IAssemblyLine {
    private final List<ILineStep> lineSteps;

    public AssemblyLineLaptop(List<ILineStep> lineSteps) {
        this.lineSteps = new ArrayList<>(lineSteps);
    }


    @Override
    public IProduct assembleProduct(IProduct product) {

        System.out.println("Assembly line laptop product started:");

        List<IProductPart> parts = new ArrayList<>();
        for (ILineStep iLineStep : lineSteps) {
            parts.add(iLineStep.buildProductPart());
        }

        product.installFirstPart(parts.get(0));
        product.installSecondPart(parts.get(1));
        product.installThirdPart(parts.get(2));

        System.out.println("Assembly line laptop product finished.");

        return product;
    }
}
