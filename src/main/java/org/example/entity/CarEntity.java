package org.example.entity;


import java.math.BigDecimal;

public class CarEntity {

    private Integer id;
    private CarModelEntity model;
    private DealerEntity dealer;
    private String state;
    private String configuration;
    private String color;
    private BigDecimal price;

    public CarEntity() {
    }

    public CarEntity(Integer id, CarModelEntity model, DealerEntity dealer, String state, String configuration, String color, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.dealer = dealer;
        this.state = state;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CarModelEntity getModel() {
        return model;
    }

    public void setModel(CarModelEntity model) {
        this.model = model;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}