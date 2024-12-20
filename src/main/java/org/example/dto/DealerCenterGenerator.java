package org.example.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DealerCenterGenerator {

    public DealerCenter createDealerCenter(Integer centerId, String centerName, List<CarDTO> cars) {
        List<CarDTO> showroomCars = new ArrayList<>();
        DealerCenter dealerCenter = new DealerCenter(centerId, centerName, cars, showroomCars);
        return dealerCenter;
    }

    public static void main(String[] args) {
        CarModelDTO model = new CarModelDTO(1, "Toyota", "Camry", "US", "Japan");
        CarDTO car1 = new CarDTO(1, model, null, CarState.В_НАЛИЧИИ, "Standard", "Red", new BigDecimal(20000));
        CarDTO car2 = new CarDTO(2, model, null, CarState.В_НАЛИЧИИ, "Luxury", "Blue", new BigDecimal(25000));
        List<CarDTO> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        DealerCenterGenerator generator = new DealerCenterGenerator();
        DealerCenter dealerCenter = generator.createDealerCenter(1, "Best Cars", cars);
        System.out.println("Dealer Center ID: " + dealerCenter.getId());
        System.out.println("Dealer Center Name: " + dealerCenter.getName());
        System.out.println("Total Cars: " + dealerCenter.getCars().size());
        System.out.println("Showroom Cars: " + dealerCenter.getShowroomCars().size());
    }
}