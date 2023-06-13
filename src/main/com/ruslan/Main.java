package com.ruslan;

import com.ruslan.laptop.AssemblyLineLaptop;
import com.ruslan.laptop.ProductLaptop;
import com.ruslan.laptop.lineStep.BodyLineStep;
import com.ruslan.laptop.lineStep.MonitorLineStep;
import com.ruslan.laptop.lineStep.MotherboardLineStep;
import com.ruslan.service.IAssemblyLine;
import com.ruslan.service.ILineStep;
import com.ruslan.service.IProduct;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<ILineStep> lineSteps = new ArrayList<>();
        lineSteps.add(new BodyLineStep());
        lineSteps.add(new MotherboardLineStep());
        lineSteps.add(new MonitorLineStep());

        IAssemblyLine iAssemblyLine = new AssemblyLineLaptop(lineSteps);
        IProduct laptop = iAssemblyLine.assembleProduct(new ProductLaptop());
        System.out.println();
        System.out.println("Laptop created!");
    }

}
