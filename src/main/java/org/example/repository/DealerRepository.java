package org.example.repository;
import org.example.entity.DealerEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerRepository {

    private static final String INSERT_DEALER = "INSERT INTO dealer (name, address) VALUES (?, ?)";
    private static final String SELECT_ALL_DEALERS = "SELECT * FROM dealer";
    private static final String SELECT_DEALER_BY_ID = "SELECT * FROM dealer WHERE id = ?";
    private static final String UPDATE_DEALER = "UPDATE dealer SET name = ?, address = ? WHERE id = ?";
    private static final String DELETE_DEALER = "DELETE FROM dealer WHERE id = ?";

    private final Connection connection;

    public DealerRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(DealerEntity dealer) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_DEALER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dealer.getName());
            stmt.setString(2, dealer.getAddress());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                dealer.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating dealer", e);
        }
    }

    public DealerEntity getById(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_DEALER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new DealerEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting dealer by ID", e);
        }
        return null;
    }

    public List<DealerEntity> getAll() {
        List<DealerEntity> dealers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_DEALERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dealers.add(new DealerEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        new ArrayList<>()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all dealers", e);
        }
        return dealers;
    }

    public void update(DealerEntity dealer) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_DEALER)) {
            stmt.setString(1, dealer.getName());
            stmt.setString(2, dealer.getAddress());
            stmt.setInt(3, dealer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating dealer", e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_DEALER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dealer", e);
        }
    }
}
