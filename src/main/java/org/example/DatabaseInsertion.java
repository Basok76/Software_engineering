package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DatabaseInsertion {

    public static void seedData() {
        try (Connection connection = DBConnection.getConnection()) {
            // Заполнение таблицы car_model
            String insertCarModel = """
            INSERT INTO car_model (brand, model_name, country_code, country_origin) 
            VALUES (?, ?, ?, ?) 
            ON CONFLICT DO NOTHING
            RETURNING id;
            """;
            Map<String, Integer> carModelIds = new HashMap<>();

            try (PreparedStatement stmt = connection.prepareStatement(insertCarModel)) {
                stmt.setString(1, "Toyota");
                stmt.setString(2, "Corolla");
                stmt.setString(3, "JP");
                stmt.setString(4, "Japan");
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) carModelIds.put("Toyota Corolla", rs.getInt(1));
                }

                stmt.setString(1, "Honda");
                stmt.setString(2, "Accord");
                stmt.setString(3, "JP");
                stmt.setString(4, "Japan");
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) carModelIds.put("Honda Accord", rs.getInt(1));
                }

                stmt.setString(1, "Ford");
                stmt.setString(2, "Focus");
                stmt.setString(3, "US");
                stmt.setString(4, "USA");
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) carModelIds.put("Ford Focus", rs.getInt(1));
                }
            }

            // Заполнение таблицы dealer
            String insertDealer = """
            INSERT INTO dealer (name, address) 
            VALUES (?, ?) 
            ON CONFLICT DO NOTHING
            RETURNING id;
            """;
            Map<String, Integer> dealerIds = new HashMap<>();

            try (PreparedStatement stmt = connection.prepareStatement(insertDealer)) {
                stmt.setString(1, "DealerCenter-1");
                stmt.setString(2, "123 Main Street");
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) dealerIds.put("DealerCenter-1", rs.getInt(1));
                }

                stmt.setString(1, "DealerCenter-2");
                stmt.setString(2, "456 Elm Street");
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) dealerIds.put("DealerCenter-2", rs.getInt(1));
                }
            }

            // Заполнение таблицы car
            String insertCar = """
            INSERT INTO car (model_id, dealer_id, state, configuration, color, price) 
            VALUES (?, ?, ?, ?, ?, ?)
            ON CONFLICT DO NOTHING;
            """;
            try (PreparedStatement stmt = connection.prepareStatement(insertCar)) {
                stmt.setInt(1, carModelIds.get("Toyota Corolla"));
                stmt.setInt(2, dealerIds.get("DealerCenter-1"));
                stmt.setString(3, "AVAILABLE");
                stmt.setString(4, "Standard");
                stmt.setString(5, "Red");
                stmt.setDouble(6, 25000.00);
                stmt.executeUpdate();

                stmt.setInt(1, carModelIds.get("Honda Accord"));
                stmt.setInt(2, dealerIds.get("DealerCenter-1"));
                stmt.setString(3, "SOLD");
                stmt.setString(4, "Luxury");
                stmt.setString(5, "Blue");
                stmt.setDouble(6, 30000.00);
                stmt.executeUpdate();

                stmt.setInt(1, carModelIds.get("Ford Focus"));
                stmt.setInt(2, dealerIds.get("DealerCenter-2"));
                stmt.setString(3, "RESERVED");
                stmt.setString(4, "Sport");
                stmt.setString(5, "Black");
                stmt.setDouble(6, 35000.00);
                stmt.executeUpdate();
            }

            System.out.println("Data seeded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
