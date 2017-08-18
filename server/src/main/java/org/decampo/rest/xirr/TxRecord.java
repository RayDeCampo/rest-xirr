package org.decampo.rest.xirr;

import org.decampo.xirr.Transaction;

/**
 * A mutable org.decampo.xirr.Transaction wrapper, because the REST
 * deserialization process does not like immutable classes.
 */
public class TxRecord {
    private double amount;
    private String when;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    Transaction toTransaction() {
        return new Transaction(amount, when);
    }
}
