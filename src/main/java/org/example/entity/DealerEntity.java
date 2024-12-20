package org.example.entity;

import java.util.List;

public class DealerEntity {

    private Integer id;
    private String name;
    private String address;
    private List<CarEntity> cars;

    public DealerEntity() {
    }

    public DealerEntity(Integer id, String name, String address, List<CarEntity> cars) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cars = cars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}