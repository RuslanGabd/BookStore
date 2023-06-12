package com.ruslan;

import com.ruslan.laptop.AssemblyLineLaptop;
import com.ruslan.laptop.ProductLaptop;
import com.ruslan.service.IAssemblyLine;
import com.ruslan.service.IProduct;

public class Main {
    public static void main(String[] args) {

        IAssemblyLine iAssemblyLine = new AssemblyLineLaptop();
        IProduct product = new ProductLaptop();

        iAssemblyLine.assembleProduct(product);


    }

}
