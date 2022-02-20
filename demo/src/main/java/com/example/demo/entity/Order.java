package com.example.demo.entity;

import com.example.validation.IsoCurrency;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "ORDER_TRX", uniqueConstraints = {@UniqueConstraint(columnNames = {"ORDER_ID"})})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_ID", unique = true)
    @NotNull
    private String orderId;

    @Column(name = "FROM_CURRENCY", length = 3)
    @NotBlank(message = "Please enter fromCurrency")
    @IsoCurrency
    private String fromCurrency;

    @Column(name = "TO_CURRENCY", length = 3)
    @NotBlank(message = "Please enter toCurrency")
    @IsoCurrency
    private String toCurrency;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Column(name = "AMOUNT")
    @NotNull(message = "Please enter amount")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }


    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }


    public String getToCurrency() {
        return toCurrency;
    }


    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }


    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }


    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Order [fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + ", transactionDate="
                + transactionDate + ", amount=" + amount + "]";
    }


}
