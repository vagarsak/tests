package ru.company.shared;

public class Result {

    private Integer quantity;
    private Integer successful;

    public Result() {
        this.quantity = 0;
        this.successful = 0;
    }

    public Integer getQuantity() {
        return quantity;
    }

    protected void addQuantity() {
        this.quantity += 1;
    }

    public Integer getSuccessful() {
        return successful;
    }

    protected void addSuccessful() {
        this.successful += 1;
        this.quantity += 1;
    }
}
