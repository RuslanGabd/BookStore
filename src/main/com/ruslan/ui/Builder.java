package com.ruslan.ui;


import com.ruslan.ui.action.*;

import java.util.Arrays;

public class Builder {

    private final Menu rootMenu = new Menu();

    public void buildMenu() {

        MenuItem showListBookByAlphabet = new MenuItem("Books sorted by Alphabet",
                new BookListByAlphabetService(), rootMenu);
        MenuItem showListBookByDate = new MenuItem("Books sorted by Date Publication",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByStatus = new MenuItem("Books sorted by Status",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByPrice = new MenuItem("Books sorted by Price",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookListStaleForPeriodByDate = new MenuItem("Stale books sorted by date",
                new BookListStaleForPeriodByDate(), rootMenu);
        MenuItem showListBookListStaleForPeriodByPrice = new MenuItem("Stale books sorted by price",
                new BookListStaleForPeriodByPrice(), rootMenu);
        MenuItem showBookDescription = new MenuItem("Description of book",
                new BookDescription(), rootMenu);
        Menu menuShowListBooks = new Menu("Books", Arrays.asList(
                showListBookByAlphabet,
                showListBookByDate,
                showListBookByStatus,
                showListBookByPrice,
                showListBookListStaleForPeriodByDate,
                showListBookListStaleForPeriodByPrice,
                showBookDescription));

        MenuItem showListBooks = new MenuItem("Books", null, menuShowListBooks);
        //________________________________________________________________

        MenuItem showListOrderByDateExecution = new MenuItem("Orders sorted by Date Execution",
                new OrderListByDateExecution(), rootMenu);
        MenuItem showListOrderByPrice = new MenuItem("Orders sorted by Price",
                new OrderListByPrice(), rootMenu);
        MenuItem showListOrderByStatus = new MenuItem("Orders sorted by Status",
                new OrderListByStatus(), rootMenu);
        MenuItem showListOrderForPeriodByStatus = new MenuItem("Completed orders for period sorted by Status",
                new OrderListCompletedForPeriodByStatus(), rootMenu);
        MenuItem showListOrderForPeriodByDate = new MenuItem("Completed orders for period sorted by Date",
                new OrderListCompletedForPeriodByDate(), rootMenu);
        MenuItem showNumbersCompletedOrders = new MenuItem("Show numbers of completed orders",
                new OrderNumbersCompletedForPeriod(), rootMenu);
        MenuItem showOrderDetails = new MenuItem("Show order details",
                new OrderDetails(), rootMenu);

        Menu menuShowListOrders = new Menu("Show list of orders", Arrays.asList(
                showListOrderByDateExecution,
                showListOrderByPrice,
                showListOrderByStatus,
                showListOrderForPeriodByStatus,
                showListOrderForPeriodByDate,
                showNumbersCompletedOrders,
                showOrderDetails));

        MenuItem showListOrders = new MenuItem("Orders", null, menuShowListOrders);
        //________________________________________________________________


        MenuItem showListRequestsByNumber = new MenuItem("Request sorted by number",
                new RequestListByNumber(), rootMenu);
        MenuItem showListRequestsByAlphabetically = new MenuItem("Request sorted by  alphabetically",
                new RequestListByAlphabetically(), rootMenu);

        Menu menuShowListRequest = new Menu("Show list of requests", Arrays.asList(showListRequestsByNumber,
                showListRequestsByAlphabetically));
        MenuItem showListRequest = new MenuItem("Requests", null, menuShowListRequest);
        //________________________________________________________________

        MenuItem showEarnedMoney = new MenuItem("Show earned money for period", new EarnedMoney(), rootMenu);



        //________________________________________________________________

        rootMenu.setName("Root menu");
        rootMenu.setMenuItems(Arrays.asList(
                showListBooks,
                showListOrders,
                showListRequest,
                showEarnedMoney));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}


