package com.ruslan;

import com.ruslan.book.Book;
import com.ruslan.book.BookStatus;
import com.ruslan.order.Order;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookStoreSorted extends BookStore {


    public void createTreeMapOrders() {
        //     System.out.println(orders.size());
        HashMap<Integer, Order> ordersWithBookPrice = new HashMap<>();
        for (Order order : orders) {
            //  System.out.println("order.getBook().getPrice()");
            //   System.out.println(order.getBook().getPrice());
            ordersWithBookPrice.put(order.getBook().getPrice(), order);
        }
        HashMap<Integer, Order>    sortedMap = ordersWithBookPrice.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        sortedMap.forEach((key, value) -> System.out.println(value.toString()));
    }



    public void printAllBooksSortedByPrice() {
        Collections.sort(stock, Comparator.comparingInt(Book::getPrice));
        System.out.println(Arrays.toString(stock.toArray()));

    }

    public void printAllBooksSortedByTitle() {
        Collections.sort(stock, (o1, o2) -> CharSequence.compare(o2.getTitle(), o1.getTitle()));
        System.out.println(Arrays.toString(stock.toArray()));
    }

    public void printAllBooksSortedByAuthor() {
        Collections.sort(stock, (o1, o2) -> CharSequence.compare(o2.getAuthor(), o1.getAuthor()));
        System.out.println(Arrays.toString(stock.toArray()));
    }

    public void printAllBooksSortedByDate() {
        Collections.sort(stock, Comparator.comparing(Book::getDatePublication));
        System.out.println("Books sorted by DatePublication:" + Arrays.toString(stock.toArray()));
    }

    public void printAllOrdersSortedByDateExecution() {
        Collections.sort(orders, Comparator.comparing(Order::getDateExecution));
        System.out.println("Orders sorted by DateExecution:" + Arrays.toString(orders.toArray()));
    }


    public void printAllBooksSortedByStatus() {
        Book temp;
        for (Book bk : stock) {
            for (int i = 0; i < stock.size() - 1; i++) {
                if (stock.get(i).getStatus() == BookStatus.NOT_AVAILABLE &&
                        stock.get(i + 1).getStatus() == BookStatus.IN_STOCK) {
                    temp = stock.get(i);
                    stock.set(i, stock.get(i + 1));
                    stock.set(i + 1, temp);
                }
                if (stock.get(i).getStatus() == BookStatus.NOT_AVAILABLE &&
                        stock.get(i + 1).getStatus() == BookStatus.OUT_OF_STOCK) {
                    temp = stock.get(i);
                    stock.set(i, stock.get(i + 1));
                    stock.set(i + 1, temp);
                }
                if (stock.get(i).getStatus() == BookStatus.OUT_OF_STOCK &&
                        stock.get(i + 1).getStatus() == BookStatus.IN_STOCK) {
                    temp = stock.get(i);
                    stock.set(i, stock.get(i + 1));
                    stock.set(i + 1, temp);
                }
            }
        }
        System.out.println("Books sorted by Status:" + Arrays.toString(stock.toArray()));
    }


    public void printAllOrdersSortedByExecution() {
        Collections.sort(orders, Comparator.comparing(Order::getDateExecution));
        System.out.println("Orders sorted by DateExecution:" + Arrays.toString(orders.toArray()));
    }

//    public void printAllOrdersSortedByPrice() {
//
//        Collections.sort(orders, Comparator.comparing(Book::getBook));
//        System.out.println("Orders sorted by DateExecution:" + Arrays.toString(orders.toArray()));
//    }

    public void printAllOrdersSortedByStatus() {
        Collections.sort(orders, Comparator.comparing(Order::getDateExecution));
        System.out.println("Orders sorted by DateExecution:" + Arrays.toString(orders.toArray()));
    }
}
