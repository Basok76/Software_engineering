package org.example.entity;

public class CarModelEntity {

    private Integer id;
    private String brand;
    private String modelName;
    private String countryCode;
    private String countryOrigin;

    public CarModelEntity() {
    }

    public CarModelEntity(Integer id, String brand, String modelName, String countryCode, String countryOrigin) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.countryCode = countryCode;
        this.countryOrigin = countryOrigin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }
    @Override
    public String toString() {
        return "CarModelEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", modelName='" + modelName + '\'' +
                ", countryOrigin='" + countryOrigin + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}