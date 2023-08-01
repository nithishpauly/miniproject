package Nithish;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItineraryController {
    // Create an itinerary and insert it into the database
    public void createItinerary(Itinerary itinerary) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO itineraries (user_id, destination_id, start_date, end_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itinerary.getUserId());
            statement.setInt(2, itinerary.getDestinationId());
            statement.setDate(3, new java.sql.Date(itinerary.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(itinerary.getEndDate().getTime()));
            statement.executeUpdate();
            System.out.println("Itinerary created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating itinerary: " + e.getMessage());
        }
    }

    // Retrieve itineraries for a specific user from the database
    public List<Itinerary> getItinerariesByUser(int userId) {
        List<Itinerary> itineraries = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM itineraries WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int destinationId = resultSet.getInt("destination_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");

                Itinerary itinerary = new Itinerary(id, userId, destinationId, startDate, endDate);
                itineraries.add(itinerary);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving itineraries: " + e.getMessage());
        }

        return itineraries;
    }

    // Delete an itinerary from the database
    public void deleteItinerary(int itineraryId) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM itineraries WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itineraryId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Itinerary deleted successfully!");
            } else {
                System.out.println("Itinerary not found!");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting itinerary: " + e.getMessage());
        }
    }
}
