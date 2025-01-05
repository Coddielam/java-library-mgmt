Context You are working on a project for a library that wants to manage its books and patrons. 

Requirements Your task is to design and implement a system using Domain Driven Design principles that can: 
1. Create and manage books. 
2. Manage Patrons and their borrowing history. 
3. Track the status of each book (e.g. available, borrowed, lost). 
4. Allow users to borrow and return books. 
5. Generate reports on books and client activity. 

Business Rules:
The system must ensure that books and patrons are not created with invalid or missing information and that lending rules are enforced 
(e.g. maximum number of books borrowed, due dates). 

Implementation 
You should implement the system using the language of your choice (Python, C#). 

Here are some hints about entities and architecture: 
Entities 
- Book: This entity represents a book in the library. 
It should have attributes like title, author, ISBN and status.

- Patron: This entity represents a patron in the library. 
It must have attributes such as name, address and borrowing history. 

- Borrowing: This entity represents a loan transaction between a patron and a book. 
It should have attributes such as loan date, due date and return date. 

Architecture 

Domain Layer: 
- This layer should contain the entities and the business logic of the system 
(e.g. rules for lending books).

Application Layer: 
- This layer should contain the system's use cases 
(for example, borrow a book, return a book). 
- It should use the domain layer to implement the use cases.

Infrastructure Layer: 
- This layer should contain the persistence logic of the system 
- (for example, storing books and patrons in a database). 
- It should use the domain layer to access the data. 

You may include unit tests and integration tests to ensure that the system works as expected.