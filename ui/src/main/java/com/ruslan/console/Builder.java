package com.ruslan.console;


import com.ruslan.console.action.book.*;
import com.ruslan.console.action.order.*;
import com.ruslan.console.action.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Builder {

    @Autowired
    private Menu rootMenu;
    @Autowired
    private BookListByDatePublication bookListByDatePublication;
    @Autowired
    private BookListByStatus bookListByStatus;
    @Autowired
    private BookListByPrice bookListByPrice;
    @Autowired
    private BookListByAlphabetically bookListByAlphabetically;
    @Autowired
    private BookListStaleByDate bookListStaleByDate;
    @Autowired
    private BookListStaleByPrice bookListStaleByPrice;
    @Autowired
    private BookDescription bookDescription;
    @Autowired
    private AddBookToStockAndCloseRequests addBookToStockAndCloseRequests;
    @Autowired
    private WriteBookToFile writeBookToFile;
    @Autowired
    private ReadBookFromFile readBookFromFile;
    @Autowired
    private OrderListByDateExecution orderListByDateExecution;
    @Autowired
    private OrderListByPrice orderListByPrice;
    @Autowired
    private OrderListByStatus orderListByStatus;
    @Autowired
    private OrderListCompletedForPeriodByPrice orderListCompletedForPeriodByPrice;
    @Autowired
    private OrderListCompletedForPeriodByDate orderListCompletedForPeriodByDate;
    @Autowired
    private OrderNumbersCompletedForPeriod orderNumbersCompletedForPeriod;
    @Autowired
    private OrderDetails orderDetails;
    @Autowired
    private CreateOrder createOrder;
    @Autowired
    private CancelOrder cancelOrder;
    @Autowired
    private ChangeOrderStatus changeOrderStatus;
    @Autowired
    private WriteOrderToFile writeOrderToFile;
    @Autowired
    private ReadOrderFromFile readOrderFromFile;
    @Autowired
    private RequestListByNumber requestListByNumber;
    @Autowired
    private RequestListByAlphabetically requestListByAlphabetically;
    @Autowired
    private CreateRequest createRequest;
    @Autowired
    private WriteRequestToFile writeRequestToFile;
    @Autowired
    private ReadRequestFromFile readRequestFromFile;

    @Autowired
    private EarnedMoney earnedMoney;
    @Autowired
    private ImportBooksFromJsonToDataBase importBooksFromJsonToDataBase;
    @Autowired
    private ImportOrdersFromJsonToDataBase importOrdersFromJsonToDataBase;
    @Autowired
    private ExportBooksToJson exportBooksToJson;
    @Autowired
    private ImportRequestsFromJsonToDataBase importRequestsFromJsonToDataBase;
    @Autowired
    private ExportOrdersToJson exportOrdersToJson;
    @Autowired
    private ExportRequestsToJson exportRequestsToJson;


    public void buildMenu() {
        MenuItem importBooksToDataBase = new MenuItem("Load Books to DB",
                importBooksFromJsonToDataBase, rootMenu);
        MenuItem exportBooksToJsonFile = new MenuItem("Export books to JSON",
                exportBooksToJson, rootMenu);
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
                importBooksToDataBase,
                exportBooksToJsonFile,
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
        MenuItem importOrdersToDataBase = new MenuItem("Load Orders to DB",
                importOrdersFromJsonToDataBase, rootMenu);
        MenuItem showListOrderByDateExecutionMI = new MenuItem("Orders sorted by Date Execution",
                orderListByDateExecution, rootMenu);
        MenuItem showListOrderByPriceMI = new MenuItem("Orders sorted by Price",
                orderListByPrice, rootMenu);
        MenuItem showListOrderByStatusMI = new MenuItem("Orders sorted by Status",
                orderListByStatus, rootMenu);
        MenuItem showListOrderCompletedForPeriodByStatusMI = new MenuItem("Completed orders for period sorted by Status",
                orderListCompletedForPeriodByPrice, rootMenu);
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
        MenuItem exportOrdersToJsonFile = new MenuItem("Export orders to JSON",
                exportOrdersToJson, rootMenu);

        Menu menuShowOrderMenu = new Menu("Order menu", Arrays.asList(
                importOrdersToDataBase,
                exportOrdersToJsonFile,
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


        MenuItem exportRequestsToJsonFile = new MenuItem("Export Requests to JSON",
                exportRequestsToJson, rootMenu);
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
        MenuItem importRequestsFromJsonToDB = new MenuItem("Read request from file",
                importRequestsFromJsonToDataBase, rootMenu);

        Menu menuShowRequestMenu = new Menu("Request menu",
                Arrays.asList(
                        importRequestsFromJsonToDB,
                        exportRequestsToJsonFile,
                        showListRequestsByNumberMI,
                        showListRequestsByAlphabeticallyMI,
                        writeRequestToFileMI,
                        readRequestFromFileMI,
                        createRequestMI));
        MenuItem showRequestMenu = new MenuItem("Request menu", null, menuShowRequestMenu);
        //________________________________________________________________

        MenuItem showEarnedMoney = new MenuItem("Show earned money for period", earnedMoney, rootMenu);

        //________________________________________________________________
        rootMenu.setName("Root menu");
        rootMenu.setMenuItems(Arrays.asList(
                showBookMenu,
                showOrderMenu,
                showRequestMenu,
                showEarnedMoney
        ));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}


