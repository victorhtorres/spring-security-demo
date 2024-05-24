package com.example.cashcard.payload;

/**
 * A request object for POSTing a cash card
 *
 */
public class CashCardRequest {
    private Double amount;

    public CashCardRequest() {
    }

    public CashCardRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
