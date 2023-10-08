package com.ruslan.ui;


import com.ruslan.DI.annotation.Inject;
import com.ruslan.ui.action.ExportAllDataToJSON;
import com.ruslan.ui.action.ImportAllDataToJSON;
import com.ruslan.ui.action.book.*;
import com.ruslan.ui.action.order.*;
import com.ruslan.ui.action.request.*;

import java.util.Arrays;

public class Builder {

    @Inject
    private Menu rootMenu;
    @Inject
    private BookListByDatePublication bookListByDatePublication;
    @Inject
    private BookListByStatus bookListByStatus;
    @Inject
    private BookListByPrice bookListByPrice;
    @Inject
    private BookListByAlphabetically bookListByAlphabetically;
    @Inject
    private BookListStaleByDate bookListStaleByDate;
    @Inject
    private BookListStaleByPrice bookListStaleByPrice;
    @Inject
    private BookDescription bookDescription;
    @Inject
    private AddBookToStockAndCloseRequests addBookToStockAndCloseRequests;
    @Inject
    private WriteBookToFile writeBookToFile;
    @Inject
    private ReadBookFromFile readBookFromFile;
    @Inject
    private OrderListByDateExecution orderListByDateExecution;
    @Inject
    private OrderListByPrice orderListByPrice;
    @Inject
    private OrderListByStatus orderListByStatus;
    @Inject
    private OrderListCompletedForPeriodByStatus orderListCompletedForPeriodByStatus;
    @Inject
    private OrderListCompletedForPeriodByDate orderListCompletedForPeriodByDate;
    @Inject
    private OrderNumbersCompletedForPeriod orderNumbersCompletedForPeriod;
    @Inject
    private OrderDetails orderDetails;
    @Inject
    private CreateOrder createOrder;
    @Inject
    private CancelOrder cancelOrder;
    @Inject
    private ChangeOrderStatus changeOrderStatus;
    @Inject
    private WriteOrderToFile writeOrderToFile;
    @Inject
    private ReadOrderFromFile readOrderFromFile;
    @Inject
    private RequestListByNumber requestListByNumber;
    @Inject
    private RequestListByAlphabetically requestListByAlphabetically;
    @Inject
    private CreateRequest createRequest;
    @Inject
    private WriteRequestToFile writeRequestToFile;
    @Inject
    private ReadRequestFromFile readRequestFromFile;
    @Inject
    private ExportAllDataToJSON exportAllDataToJSON;
    @Inject
    private ImportAllDataToJSON importAllDataToJSON;
    @Inject
    private EarnedMoney earnedMoney;

    public void buildMenu() {

        MenuItem showListBookByAlphabet = new MenuItem("Books sorted by Alphabet",
                bookListByAlphabetically, rootMenu);
        MenuItem showListBookByDate = new MenuItem("Books sorted by Date Publication",
                bookListByDatePublication, rootMenu);
        MenuItem showListBookByStatus = new MenuItem("Books sorted by Status",
                bookListByStatus, rootMenu);
        MenuItem showListBookByPrice = new MenuItem("Books sorted by Price",
                bookListByPrice, rootMenu);
        MenuItem showListBookListStaleByDate = new MenuItem("Stale books sorted by date",
                bookListStaleByDate, rootMenu);
        MenuItem showListBookListStaleByPrice = new MenuItem("Stale books sorted by price",
                bookListStaleByPrice, rootMenu);
        MenuItem showBookDescription = new MenuItem("Description of book",
                bookDescription, rootMenu);
        MenuItem AddBookToStock = new MenuItem("Add a book to stock:",
                addBookToStockAndCloseRequests, rootMenu);
        MenuItem saveBookToFile = new MenuItem("Save book to file",
                writeBookToFile, rootMenu);
        MenuItem loadBookFromFile = new MenuItem("Read book from file",
                readBookFromFile, rootMenu);


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
                loadBookFromFile

        ));

        MenuItem showBookMenu = new MenuItem("Book menu", null, menuShowBookMenu);
        //________________________________________________________________

        MenuItem showListOrderByDateExecutionMI = new MenuItem("Orders sorted by Date Execution",
                orderListByDateExecution, rootMenu);
        MenuItem showListOrderByPriceMI = new MenuItem("Orders sorted by Price",
                orderListByPrice, rootMenu);
        MenuItem showListOrderByStatusMI = new MenuItem("Orders sorted by Status",
                orderListByStatus, rootMenu);
        MenuItem showListOrderCompletedForPeriodByStatusMI = new MenuItem("Completed orders for period sorted by Status",
                orderListCompletedForPeriodByStatus, rootMenu);
        MenuItem showListOrderCompletedForPeriodByDateMI = new MenuItem("Completed orders for period sorted by Date",
                orderListCompletedForPeriodByDate, rootMenu);
        MenuItem showNumbersCompletedOrdersMI = new MenuItem("Show numbers of completed orders",
                orderNumbersCompletedForPeriod, rootMenu);
        MenuItem showOrderDetailsMI = new MenuItem("Show order details",
                orderDetails, rootMenu);
        MenuItem createOrderMI = new MenuItem("Create order",
                createOrder, rootMenu);
        MenuItem cancelOrderMI = new MenuItem("Cancel order",
                cancelOrder, rootMenu);
        MenuItem changeOrderStatusMI = new MenuItem("Change order status",
                changeOrderStatus, rootMenu);
        MenuItem writeOrderToFileMI = new MenuItem("Save order to file",
                writeOrderToFile, rootMenu);
        MenuItem readOrderFromFileMI = new MenuItem("Read order to file",
                readOrderFromFile, rootMenu);

        Menu menuShowOrderMenu = new Menu("Order menu", Arrays.asList(
                createOrderMI,
                cancelOrderMI,
                changeOrderStatusMI,
                showOrderDetailsMI,
                showListOrderByDateExecutionMI,
                showListOrderByPriceMI,
                showListOrderByStatusMI,
                showListOrderCompletedForPeriodByStatusMI,
                showListOrderCompletedForPeriodByDateMI,
                showNumbersCompletedOrdersMI,
                writeOrderToFileMI,
                readOrderFromFileMI));

        MenuItem showOrderMenu = new MenuItem("Order menu", null, menuShowOrderMenu);
        //________________________________________________________________


        MenuItem showListRequestsByNumberMI = new MenuItem("Request sorted by number",
                requestListByNumber, rootMenu);
        MenuItem showListRequestsByAlphabeticallyMI = new MenuItem("Request sorted by  alphabetically",
                requestListByAlphabetically, rootMenu);
        MenuItem createRequestMI = new MenuItem("Create request",
                createRequest, rootMenu);
        MenuItem writeRequestToFileMI = new MenuItem("Save request to file",
                writeRequestToFile, rootMenu);
        MenuItem readRequestFromFileMI = new MenuItem("Read request from file",
                readRequestFromFile, rootMenu);


        Menu menuShowRequestMenu = new Menu("Request menu",
                Arrays.asList(
                        showListRequestsByNumberMI,
                        showListRequestsByAlphabeticallyMI,
                        writeRequestToFileMI,
                        readRequestFromFileMI,
                        createRequestMI));
        MenuItem showRequestMenu = new MenuItem("Request menu", null, menuShowRequestMenu);
        //________________________________________________________________

        MenuItem showEarnedMoney = new MenuItem("Show earned money for period", earnedMoney, rootMenu);


        //________________________________________________________________
        MenuItem saveAllData = new MenuItem("Save All Data To JSON", exportAllDataToJSON, rootMenu);
        MenuItem loadAllData = new MenuItem("Load All Data From JSON", importAllDataToJSON, rootMenu);

        //________________________________________________________________
        rootMenu.setName("Root menu");
        rootMenu.setMenuItems(Arrays.asList(
                showBookMenu,
                showOrderMenu,
                showRequestMenu,
                showEarnedMoney,
                loadAllData,
                saveAllData));
    }


    public Menu getRootMenu() {
        return rootMenu;
    }

}


