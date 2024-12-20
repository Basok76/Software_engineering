package org.example.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;
import java.util.ArrayList;

public class DealerCenter {
    private Integer id;
    private String name;
    private List<CarDTO> cars;
    private List<CarDTO> showroomCars;
    private int showroomCarCount;

    public DealerCenter(Integer id, String name, List<CarDTO> cars, List<CarDTO> showroomCars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
        this.showroomCars = showroomCars != null ? showroomCars : new ArrayList<>();
        this.showroomCarCount = showroomCars != null ? showroomCars.size() : 0;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public List<CarDTO> getShowroomCars() {
        return showroomCars;
    }

    public int getShowroomCarCount() {
        return showroomCarCount;
    }

    private synchronized void addToShowroom(CarDTO car) {
        showroomCars.add(car);
        showroomCarCount++;  // Обновляем количество автомобилей в шоурум
    }

    private boolean shouldAddToShowroom(CarDTO car) {
        return car.getColor().equalsIgnoreCase("Red") || car.getConfiguration().equalsIgnoreCase("Luxury");
    }

    public void processCar(CarDTO car) {
        try {
            System.out.println("Processing car with ID: " + car.getId());
            Thread.sleep(1000);
            BigDecimal adjustedPrice = car.getPrice().multiply(BigDecimal.valueOf(1.1));
            car.setPrice(adjustedPrice);
            System.out.println("Processed car with ID: " + car.getId() + ", new price: " + adjustedPrice);
            if (shouldAddToShowroom(car)) {
                addToShowroom(car);
                System.out.println("Car added to showroom: " + car.getId());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Processing was interrupted.");
        }
    }

    public void processCarsInThreads(List<CarDTO> carsToProcess) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (CarDTO car : carsToProcess) {
            executorService.submit(() -> processCar(car));
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) {
        CarModelDTO model = new CarModelDTO(1, "Toyota", "Camry", "US", "Japan");
        CarDTO car1 = new CarDTO(1, model, null, CarState.В_НАЛИЧИИ, "Standard", "Red", new BigDecimal(20000));
        CarDTO car2 = new CarDTO(2, model, null, CarState.В_НАЛИЧИИ, "Luxury", "Blue", new BigDecimal(25000));
        CarDTO car3 = new CarDTO(3, model, null, CarState.В_НАЛИЧИИ, "Luxury", "Red", new BigDecimal(30000));
        List<CarDTO> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        List<CarDTO> showroomCars = new ArrayList<>();
        DealerCenter dealerCenter = new DealerCenter(1, "Best Cars", carList, showroomCars);
        dealerCenter.processCarsInThreads(dealerCenter.getCars());
        System.out.println("Cars in showroom: ");
        for (CarDTO car : dealerCenter.getShowroomCars()) {
            System.out.println(car);
        }
        System.out.println("Total cars in showroom: " + dealerCenter.getShowroomCarCount());
    }
}
