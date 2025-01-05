package org.example.entity;

import org.example.valueobject.Address;

import java.util.Collection;
import java.util.Objects;

public class Patron {

    private String id;
    private String name;
    private Address address;
    private Collection<String> borrowingHistoryIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<String> getBorrowingHistoryIds() {
        return borrowingHistoryIds;
    }

    public void setBorrowingHistoryIds(Collection<String> borrowingHistoryIds) {
        this.borrowingHistoryIds = borrowingHistoryIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(id, patron.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
