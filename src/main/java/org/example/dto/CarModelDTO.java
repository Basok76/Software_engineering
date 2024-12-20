package org.example.dto;

import java.util.Objects;

public class CarModelDTO {
    private Integer id;
    private String brand;
    private String modelName;
    private String country_origin;
    private String country_code;

    public CarModelDTO(Integer id,String brand, String modelName, String country_code, String country_origin) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.country_origin = country_origin;
        this.country_code = country_code;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry_origin() {
        return country_origin;
    }

    public String getModelName() {
        return modelName;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountry_origin(String country_origin) {
        this.country_origin = country_origin;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CarModelDTO{" +
                "id=" + id +
                ", brand=" + brand +
                ", modelName=" + modelName +
                ", countryOrigin=" + country_origin +
                ", countryCode=" + country_code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModelDTO that = (CarModelDTO) o;
        return Objects.equals(brand, that.brand) && Objects.equals(modelName, that.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand);
    }
}