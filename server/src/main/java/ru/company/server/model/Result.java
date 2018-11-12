package ru.company.server.model;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "successful")
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
