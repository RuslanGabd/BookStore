package com.ruslan.ui;


import com.ruslan.ui.action.*;

import java.util.Arrays;

public class Builder {

    private Menu rootMenu = new Menu();

    public void buildMenu() {

        MenuItem showListBookByAlphabet = new MenuItem("Books sorted by Alphabet",
                new BookListByAlphabetService(), rootMenu);
//        showListBookByAlphabet.setTitle("Books sorted by Alphabet");
//        showListBookByAlphabet.setAction(new BookListByAlphabetService());
//        showListBookByAlphabet.setNextMenu(rootMenu);
        MenuItem showListBookByDate = new MenuItem("Books sorted by Date Publication",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByStatus = new MenuItem("Books sorted by Status",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByPrice = new MenuItem("Books sorted by Price",
                new BookListByDatePublication(), rootMenu);
        Menu menuShowListBooks = new Menu("Show list of books", Arrays.asList(showListBookByAlphabet,
                showListBookByDate, showListBookByStatus, showListBookByPrice));

        MenuItem showListBooks = new MenuItem("Show lists of books", null, menuShowListBooks);
        //________________________________________________________________

        MenuItem showListOrderByDateExecution = new MenuItem("Orders sorted by Date Execution",
                new OrderListByDateExecution(), rootMenu);
        MenuItem showListOrderByPrice = new MenuItem("Orders sorted by Price",
                new OrderListByPrice(), rootMenu);
        MenuItem showListOrderByStatus = new MenuItem("Orders sorted by Status",
                new OrderListByStatus(), rootMenu);

//        MenuItem showListOrderForPeriod = new MenuItem("Orders sorted by Status",
//                new OrderListCompletedForPeriod(), rootMenu);
        Menu menuShowListOrders = new Menu("Show list of orders", Arrays.asList(showListOrderByDateExecution,
                showListOrderByPrice, showListOrderByStatus));
        MenuItem showListOrders = new MenuItem("Show lists of orders", null, menuShowListOrders);
        //________________________________________________________________



        //________________________________________________________________


        MenuItem showListRequestsByNumber = new MenuItem("Request sorted by number",
                new RequestListByNumber(), rootMenu);
        MenuItem showListRequestsByAlphabetically = new MenuItem("Request sorted by  alphabetically",
                new RequestListByAlphabetically(), rootMenu);

        Menu menuShowListRequest = new Menu("Show list of requests", Arrays.asList(showListRequestsByNumber,
                showListRequestsByAlphabetically));
        MenuItem showListRequest = new MenuItem("Show lists of requests", null, menuShowListRequest);
        //________________________________________________________________

        MenuItem orderBook = new MenuItem("Order a book", new CreateOrderBook(), rootMenu);
        orderBook.setTitle("Order a book");
        orderBook.setAction(null);
        orderBook.setNextMenu(rootMenu);
        // MenuItem orderRequest = new MenuItem();
        //     orderRequest.setTitle("Create request");
        //    orderRequest.setAction(null);
        //    orderRequest.setNextMenu(rootMenu);
        // MenuItem orderCancel = new MenuItem();
        //   orderCancel.setTitle("Cancel order");
        //   orderCancel.setAction(null);
        //    orderCancel.setNextMenu(rootMenu);

        //    Menu menuOrders = new Menu("Menu orders", Arrays.asList(orderBook, orderRequest, orderCancel));
        Menu menuOrders = new Menu("Menu orders", Arrays.asList(orderBook));
        MenuItem order = new MenuItem("Order1", null, menuOrders);
//        order.setTitle("Order");
//        order.setAction(null);
//        order.setNextMenu(menuOrders);m
        //________________________________________________________________

        rootMenu.setName("Root menu");
        rootMenu.setMenuItems(Arrays.asList(order, showListBooks, showListOrders, showListRequest));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}


