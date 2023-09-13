package com.ruslan.ui;


import com.ruslan.ui.action.ExportAllDataToJSON;
import com.ruslan.ui.action.ImportAllDataToJSON;
import com.ruslan.ui.action.book.*;
import com.ruslan.ui.action.order.*;
import com.ruslan.ui.action.request.*;

import java.util.Arrays;

public class Builder {

    private final Menu rootMenu = new Menu();

    public void buildMenu() {

        MenuItem showListBookByAlphabet = new MenuItem("Books sorted by Alphabet",
                new BookListByAlphabetically(), rootMenu);
        MenuItem showListBookByDate = new MenuItem("Books sorted by Date Publication",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByStatus = new MenuItem("Books sorted by Status",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookByPrice = new MenuItem("Books sorted by Price",
                new BookListByDatePublication(), rootMenu);
        MenuItem showListBookListStaleByDate = new MenuItem("Stale books sorted by date",
                new BookListStaleByDate(), rootMenu);
        MenuItem showListBookListStaleByPrice = new MenuItem("Stale books sorted by price",
                new BookListStaleByPrice(), rootMenu);
        MenuItem showBookDescription = new MenuItem("Description of book",
                new BookDescription(), rootMenu);
        MenuItem AddBookToStock = new MenuItem("Add a book to stock:",
                new AddBookToStockAndCloseRequests(), rootMenu);
        MenuItem saveBookToFile = new MenuItem("Save book to file",
                new WriteBookToFile(), rootMenu);
        MenuItem readBookFromFile = new MenuItem("Read book from file",
                new ReadBookFromFile(), rootMenu);
//        MenuItem setNumberOfMonthsForStaleBooks = new MenuItem("Change number of months for stale books",
//                new SetNumberOfMonthsForStaleBooks(), rootMenu);
//        MenuItem setAutoClosedRequestIfBookAddToStock = new MenuItem("Set auto closed request, book added to warehouse",
//                new SetAutoClosedRequestIfBookAddToStock(), rootMenu);

        Menu menuShowBookMenu = new Menu("Book menu", Arrays.asList(
                showListBookByAlphabet,
                showListBookByDate,
                showListBookByStatus,
                showListBookByPrice,
                showListBookListStaleByDate,
                showListBookListStaleByPrice,
                showBookDescription,
                AddBookToStock,
                saveBookToFile,
                readBookFromFile

        ));

        MenuItem showBookMenu = new MenuItem("Book menu", null, menuShowBookMenu);
        //________________________________________________________________

        MenuItem showListOrderByDateExecution = new MenuItem("Orders sorted by Date Execution",
                new OrderListByDateExecution(), rootMenu);
        MenuItem showListOrderByPrice = new MenuItem("Orders sorted by Price",
                new OrderListByPrice(), rootMenu);
        MenuItem showListOrderByStatus = new MenuItem("Orders sorted by Status",
                new OrderListByStatus(), rootMenu);
        MenuItem showListOrderCompletedForPeriodByStatus = new MenuItem("Completed orders for period sorted by Status",
                new OrderListCompletedForPeriodByStatus(), rootMenu);
        MenuItem showListOrderCompletedForPeriodByDate = new MenuItem("Completed orders for period sorted by Date",
                new OrderListCompletedForPeriodByDate(), rootMenu);
        MenuItem showNumbersCompletedOrders = new MenuItem("Show numbers of completed orders",
                new OrderNumbersCompletedForPeriod(), rootMenu);
        MenuItem showOrderDetails = new MenuItem("Show order details",
                new OrderDetails(), rootMenu);
        MenuItem createOrder = new MenuItem("Create order",
                new CreateOrder(), rootMenu);
        MenuItem cancelOrder = new MenuItem("Cancel order",
                new CancelOrder(), rootMenu);
        MenuItem changeOrderStatus = new MenuItem("Change order status",
                new ChangeOrderStatus(), rootMenu);
        MenuItem saveOrderToFile = new MenuItem("Save order to file",
                new WriteOrderToFile(), rootMenu);
        MenuItem readOrderFromFile = new MenuItem("Read order to file",
                new ReadOrderFromFile(), rootMenu);

        Menu menuShowOrderMenu = new Menu("Order menu", Arrays.asList(
                createOrder,
                cancelOrder,
                changeOrderStatus,
                showOrderDetails,
                showListOrderByDateExecution,
                showListOrderByPrice,
                showListOrderByStatus,
                showListOrderCompletedForPeriodByStatus,
                showListOrderCompletedForPeriodByDate,
                showNumbersCompletedOrders,
                saveOrderToFile,
                readOrderFromFile));

        MenuItem showOrderMenu = new MenuItem("Order menu", null, menuShowOrderMenu);
        //________________________________________________________________


        MenuItem showListRequestsByNumber = new MenuItem("Request sorted by number",
                new RequestListByNumber(), rootMenu);
        MenuItem showListRequestsByAlphabetically = new MenuItem("Request sorted by  alphabetically",
                new RequestListByAlphabetically(), rootMenu);
        MenuItem createRequest = new MenuItem("Create request",
                new CreateRequest(), rootMenu);
        MenuItem saveRequest = new MenuItem("Save request to file",
                new WriteRequestToFile(), rootMenu);
        MenuItem readRequest = new MenuItem("Read request from file",
                new ReadRequestFromFile(), rootMenu);



        Menu menuShowRequestMenu = new Menu("Request menu",
                Arrays.asList(createRequest,
                        showListRequestsByNumber,
                        showListRequestsByAlphabetically,
                        saveRequest,
                        readRequest));
        MenuItem showRequestMenu = new MenuItem("Request menu", null, menuShowRequestMenu);
        //________________________________________________________________

        MenuItem showEarnedMoney = new MenuItem("Show earned money for period", new EarnedMoney(), rootMenu);


        //________________________________________________________________
        MenuItem SaveAllData = new MenuItem("Save All Data To JSON", new ExportAllDataToJSON(), rootMenu);
        MenuItem LoadAllData = new MenuItem("Load All Data To JSON", new ImportAllDataToJSON(), rootMenu);
        //________________________________________________________________
        rootMenu.setName("Root menu");
        rootMenu.setMenuItems(Arrays.asList(
                showBookMenu,
                showOrderMenu,
                showRequestMenu,
                showEarnedMoney,
                LoadAllData,
                SaveAllData));
    }


    public Menu getRootMenu() {
        return rootMenu;
    }

}


