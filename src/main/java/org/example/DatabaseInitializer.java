package org.example;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTables() {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String createCarModelTable = """
                    CREATE TABLE IF NOT EXISTS car_model (
                        id SERIAL PRIMARY KEY,
                        brand VARCHAR(50) NOT NULL,
                        model_name VARCHAR(50) NOT NULL,
                        country_code VARCHAR(10) NOT NULL,
                        country_origin VARCHAR(50) NOT NULL
                    );
                    """;
            statement.execute(createCarModelTable);

            String createDealerTable = """
                    CREATE TABLE IF NOT EXISTS dealer (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        address VARCHAR(200) NOT NULL
                    );
                    """;
            statement.execute(createDealerTable);

            String createCarTable = """
                    CREATE TABLE IF NOT EXISTS car (
                        id SERIAL PRIMARY KEY,
                        model_id INT NOT NULL REFERENCES car_model(id),
                        dealer_id INT REFERENCES dealer(id),
                        state VARCHAR(20) NOT NULL,
                        configuration VARCHAR(100),
                        color VARCHAR(30),
                        price DECIMAL(10, 2) NOT NULL
                    );
                    """;
            statement.execute(createCarTable);

            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}