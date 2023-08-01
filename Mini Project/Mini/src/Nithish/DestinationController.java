package Nithish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinationController {
   
    public void addDestination(Destination destination) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO destinations (name, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, destination.getName());
            statement.setString(2, destination.getDescription());
            statement.executeUpdate();
            System.out.println("Destination added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding destination: " + e.getMessage());
        }
    }

    public List<Destination> getAllDestinations() {
        List<Destination> destinations = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM destinations";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Destination destination = new Destination(id, name, description);
                destinations.add(destination);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving destinations: " + e.getMessage());
        }

        return destinations;
    }
}
