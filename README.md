# TASK 7.1

## BookStore
Theoretical tasks

Interfaces Serializable and Externalizable

Keyword transient

Serialization libraries in XML and JSON: JAXB, Jackson

Class java.util.Properties (self-study)

Practical tasks

Task 1 (difficulty 3)

Add the ability to configure the program from the previous task using the property file:

Electronic bookstore:

- The number of months to mark a book as "stale";

- Enabling/disabling the ability to mark requests as completed when adding a book to the warehouse.

  Task 2 (difficulty 5)

Implement program state preservation by serializing its objects to a file. Restore the saved state at the start, and write the new state to a file at the end.

It implies the use of standard serialization mechanisms (Serializable or Externalizable), but it is permissible to use other ones (for example, Jackson as a serializer in JSON).
