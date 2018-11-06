package ru.company.shared;

public class Result {

    private Integer id;
    private Integer quantity;
    private Integer successful;

    public Result() {
    }

    public Result(Integer id) {
        this.id = id;
        this.quantity = 0;
        this.successful = 0;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void addQuantity() {
        this.quantity += 1;
    }

    public Integer getSuccessful() {
        return successful;
    }

    public void addSuccessful() {
        this.successful += 1;
        this.quantity += 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSuccessful(Integer successful) {
        this.successful = successful;
    }
}
