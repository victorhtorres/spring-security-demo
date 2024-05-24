package com.example.cashcard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The cash card entity.
 *
 * <p>Note that when using Spring Data JDBC, it is not necessary to supply
 * the `id` when inserting a cash card record.</p>
 *
 */
@Entity
public class CashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private String owner;

    public CashCard() {
    }

    public CashCard(Double amount, String owner) {
        this.amount = amount;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getOwner() {
        return owner;
    }
}
