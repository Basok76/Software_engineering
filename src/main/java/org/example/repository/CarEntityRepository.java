package org.example.repository;


import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarEntityRepository {

    private static final String INSERT_CAR = "INSERT INTO car (model_id, dealer_id, state, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_CARS = "SELECT * FROM car";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String UPDATE_CAR = "UPDATE car SET state = ?, configuration = ?, color = ?, price = ? WHERE id = ?";
    private static final String DELETE_CAR = "DELETE FROM car WHERE id = ?";

    private final Connection connection;

    public CarEntityRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(CarEntity car) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, car.getModel().getId());
            stmt.setInt(2, car.getDealer().getId());
            stmt.setString(3, car.getState());
            stmt.setString(4, car.getConfiguration());
            stmt.setString(5, car.getColor());
            stmt.setBigDecimal(6, car.getPrice());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                car.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating car", e);
        }
    }

    public CarEntity getById(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_CAR_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CarModelEntity model = new CarModelRepository(connection).getById(rs.getInt("model_id"));
                DealerEntity dealer = new DealerRepository(connection).getById(rs.getInt("dealer_id"));

                return new CarEntity(
                        rs.getInt("id"),
                        model,
                        dealer,
                        rs.getString("state"),
                        rs.getString("configuration"),
                        rs.getString("color"),
                        rs.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting car by ID", e);
        }
        return null;
    }

    public List<CarEntity> getAll() {
        List<CarEntity> cars = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_CARS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CarModelEntity model = new CarModelRepository(connection).getById(rs.getInt("model_id"));
                DealerEntity dealer = new DealerRepository(connection).getById(rs.getInt("dealer_id"));

                cars.add(new CarEntity(
                        rs.getInt("id"),
                        model,
                        dealer,
                        rs.getString("state"),
                        rs.getString("configuration"),
                        rs.getString("color"),
                        rs.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all cars", e);
        }
        return cars;
    }

    public void update(CarEntity car) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_CAR)) {
            stmt.setString(1, car.getState());
            stmt.setString(2, car.getConfiguration());
            stmt.setString(3, car.getColor());
            stmt.setBigDecimal(4, car.getPrice());
            stmt.setInt(5, car.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car", e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_CAR)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car", e);
        }
    }
}