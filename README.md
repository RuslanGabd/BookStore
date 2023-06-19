# TASK 3.4

## BookStore

 An electronic bookstore.
    The programme should provide an opportunity to:
    -Write off the book from stock (set to "out of stock" status);
    -Create an order;
    -Cancel an order;
    -Change order status (new, fulfilled, cancelled);
    -Add a book to stock (closes all book requests and changes its status into "in stock");
    -Leave a request for a book.

Addendum:
All available books are predefined. They may be in "in stock" or "not available" statuses. "Leave a request for a book" refers to the creation of a request for a book that is currently "out of stock".
When you create an order with a book that is out of stock, a request for that book is automatically created. The order cannot be completed until the book request is fulfilled.