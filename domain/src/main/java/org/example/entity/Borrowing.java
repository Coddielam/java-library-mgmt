package org.example.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Borrowing {

    private String id;
    private LocalDateTime borrowedDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String bookId;
    private String patronId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(borrowedDate, borrowing.borrowedDate) && Objects.equals(dueDate, borrowing.dueDate) && Objects.equals(returnDate, borrowing.returnDate) && Objects.equals(bookId, borrowing.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowedDate, dueDate, returnDate, bookId);
    }
}
