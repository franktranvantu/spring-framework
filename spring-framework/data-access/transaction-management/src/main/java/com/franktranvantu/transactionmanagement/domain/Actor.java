package com.franktranvantu.transactionmanagement.domain;

public record Actor(Long id, String firstName, String lastName) {
    public Actor(String firstName, String lastName) {
        this(null, firstName, lastName);
    }
}
