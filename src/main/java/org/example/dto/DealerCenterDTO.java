package org.example.dto;
import java.util.List;

public class DealerCenterDTO {

    private Integer id;
    private String name;
    private String address;
    private List<CarDTO> cars;
    private List<CarDTO> showroomCars;
    private int totalCars;
    private int showroomCarCount;

    public DealerCenterDTO(Integer id, String name, String address, List<CarDTO> cars, List<CarDTO> showroomCars) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cars = cars;
        this.showroomCars = showroomCars;
        this.totalCars = cars != null ? cars.size() : 0;
        this.showroomCarCount = showroomCars != null ? showroomCars.size() : 0;
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

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
        this.totalCars = cars != null ? cars.size() : 0;
    }

    public List<CarDTO> getShowroomCars() {
        return showroomCars;
    }

    public void setShowroomCars(List<CarDTO> showroomCars) {
        this.showroomCars = showroomCars;
        this.showroomCarCount = showroomCars != null ? showroomCars.size() : 0;
    }

    public int getTotalCars() {
        return totalCars;
    }

    public void setShowroomCarCount(int showroomCarCount) {
        this.showroomCarCount = showroomCarCount;
    }

    public int getShowroomCarCount() {
        return showroomCarCount;
    }

    @Override
    public String toString() {
        return "DealerCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalCars=" + totalCars +
                ", showroomCarCount=" + showroomCarCount +
                '}';
    }
}
