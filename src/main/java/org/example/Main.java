package org.example;

import org.example.entity.CarModelEntity;
import org.example.repository.CarModelRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
            try (Connection connection = DBConnection.getConnection()) {
                DatabaseInitializer.createTables();
                DatabaseInsertion.seedData();
                CarModelRepository carModelRepository = new CarModelRepository(connection);

                CarModelEntity newCarModel = new CarModelEntity(null, "Toyota", "Camry", "JP", "Japan");
                carModelRepository.create(newCarModel);
                System.out.println("Created car model with ID: " + newCarModel.getId());

                CarModelEntity carModel = carModelRepository.getById(newCarModel.getId());
                if (carModel != null) {
                    System.out.println("Retrieved car model: " + carModel);
                }

                carModel.setBrand("Honda");
                carModel.setModelName("Accord");
                carModelRepository.update(carModel);
                System.out.println("Updated car model: " + carModel);

                List<CarModelEntity> carModels = carModelRepository.getAll();
                System.out.println("All car models:");
                for (CarModelEntity model : carModels) {
                    System.out.println(model);
                }

                carModelRepository.delete(newCarModel.getId());
                System.out.println("Deleted car model with ID: " + newCarModel.getId());

            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
            }
    }
}

