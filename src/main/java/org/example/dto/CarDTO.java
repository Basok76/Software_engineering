package org.example.dto;

import java.math.BigDecimal;

public class CarDTO {

    private Integer id;
    private CarModelDTO carModel;
    private DealerCenterDTO dealerCenterDTO;
    private CarState state;
    private String configuration;
    private String color;
    private BigDecimal price;

    public CarDTO(Integer id, CarModelDTO carModel, DealerCenterDTO dealerCenterDTO, CarState state,
                  String configuration, String color, BigDecimal price) {
        this.id = id;
        this.carModel = carModel;
        this.dealerCenterDTO = dealerCenterDTO;
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

    public CarModelDTO getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDTO carModel) {
        this.carModel = carModel;
    }

    public DealerCenterDTO getDealerCenter() {
        return dealerCenterDTO;
    }

    public void setDealerCenter(DealerCenterDTO dealerCenterDTO) {
        this.dealerCenterDTO = dealerCenterDTO;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
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

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", carModel=" + carModel.getModelName() +
                ", dealerCenter=" + dealerCenterDTO.getName() +
                ", state=" + state +
                ", configuration='" + configuration + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
