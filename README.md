# TASK 4.1

## BookStore
Electronic bookstore.

The program should allow you to show:

List of books (sort alphabetically, by date of publication, by price, by stock availability);

List of orders (sort by date of execution, by price, by status);

List of book requests (sort by number of requests, alphabetically);

List of completed orders for a period of time (sort by date, by price);

The amount of money earned over a period of time;

The number of completed orders over a period of time;

List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);

Order details (any customer data + books);

Description of the book.

The program should provide the opportunity to: (already done)

Change status to "out of stock" if book is not present in warehouse;

Create an order;

Cancel the order;

Change the order status (new, completed, canceled);

Add a book to the warehouse (closes all book requests and changes its status to “in stock”);

Leave a request for a book.

__Addition__:

All available books are pre-defined. They must be “in stock” or “out of stock". Under “Leave a request for a book” means creating a request for a book that is currently “out of stock".

When creating an order with a book that is not available, a request for this book is automatically created. The order cannot be completed until the book request is completed.