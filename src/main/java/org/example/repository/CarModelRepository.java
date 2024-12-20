package org.example.repository;

import org.example.entity.CarModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository {

    private static final String INSERT_CAR_MODEL = "INSERT INTO car_model (brand, model_name, country_code, country_origin) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_CAR_MODELS = "SELECT * FROM car_model";
    private static final String SELECT_CAR_MODEL_BY_ID = "SELECT * FROM car_model WHERE id = ?";
    private static final String UPDATE_CAR_MODEL = "UPDATE car_model SET brand = ?, model_name = ?, country_code = ?, country_origin = ? WHERE id = ?";
    private static final String DELETE_CAR_MODEL = "DELETE FROM car_model WHERE id = ?";

    private final Connection connection;

    public CarModelRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(CarModelEntity carModel) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CAR_MODEL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModelName());
            stmt.setString(3, carModel.getCountryCode());
            stmt.setString(4, carModel.getCountryOrigin());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                carModel.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating car model", e);
        }
    }

    public CarModelEntity getById(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_CAR_MODEL_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model_name"),
                        rs.getString("country_code"),
                        rs.getString("country_origin")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting car model by ID", e);
        }
        return null;
    }

    public List<CarModelEntity> getAll() {
        List<CarModelEntity> carModels = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_CAR_MODELS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                carModels.add(new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model_name"),
                        rs.getString("country_code"),
                        rs.getString("country_origin")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all car models", e);
        }
        return carModels;
    }

    public void update(CarModelEntity carModel) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_CAR_MODEL)) {
            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModelName());
            stmt.setString(3, carModel.getCountryCode());
            stmt.setString(4, carModel.getCountryOrigin());
            stmt.setInt(5, carModel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car model", e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_CAR_MODEL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car model", e);
        }
    }
}