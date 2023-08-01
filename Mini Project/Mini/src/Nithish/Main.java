package Nithish;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main extends Validator
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        DestinationController destinationController = new DestinationController();
        ItineraryController itineraryController = new ItineraryController();

        int currentUserID = -1; 

        while (true) {
            if (currentUserID == -1) {
                
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
            } else {
                
                System.out.println("1. Add Destination");
                System.out.println("2. View Destinations");
                System.out.println("3. Create Itinerary");
                System.out.println("4. View Itineraries");
                System.out.println("5. Delete Itinerary");
                System.out.println("0. Logout");
            }

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (currentUserID == -1) {
                        
                        System.out.print("Enter username: ");
                        String regUsername = scanner.next();
                        if(iscorrect(regUsername,regUsername.length()))
                        {
                        	System.out.println("Enter the valid username");
                        	System.exit(0);
                        }
                        System.out.print("Enter password: ");
                        String regPassword = scanner.next();
                        if(!iscorrect(regPassword))
                        {
                        	System.out.println("password must contains 6 character 3digit 1 special character");
                        	System.exit(0);
                        }
                        
                        User newUser = new User(choice, regUsername, regPassword);
                        userController.registerUser(newUser);
                    } else {
                        
                        System.out.print("Enter destination name: ");
                        String name = scanner.next();
                        System.out.print("Enter destination description: ");
                        String description = scanner.next();

                        Destination newDestination = new Destination(choice, name, description);
                        destinationController.addDestination(newDestination);
                    }
                    break;

                case 2:
                    if (currentUserID == -1) {
                        
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.next();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.next();

                        User loggedInUser = userController.loginUser(loginUsername, loginPassword);
                        if (loggedInUser != null) {
                            System.out.println("Logged in as " + loggedInUser.getUsername());
                            currentUserID = loggedInUser.getId();
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                    } else {
                       
                        List<Destination> destinations = destinationController.getAllDestinations();
                        for (Destination destination : destinations) {
                            System.out.println(destination.getName() + " - " + destination.getDescription());
                        }
                    }
                    break;

                case 3:
                    if (currentUserID != -1) {
                        
                        System.out.print("Enter destination name: ");
                        String destinationName = scanner.next();
                        int destinationId = -1;

                        
                        List<Destination> destinations = destinationController.getAllDestinations();
                        for (Destination destination : destinations) {
                            if (destination.getName().equalsIgnoreCase(destinationName)) {
                                destinationId = destination.getId();
                                break;
                            }
                        }

                        if (destinationId == -1) {
                            System.out.println("Destination does not exist. Please add the destination first.");
                            break;
                        }

                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDateString = scanner.next();
                        Date startDate = parseDate(startDateString);

                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDateString = scanner.next();
                        Date endDate = parseDate(endDateString);

                        if (startDate == null || endDate == null) {
                            System.out.println("Invalid date format. Itinerary not created.");
                            break;
                        }

                        Itinerary newItinerary = new Itinerary(currentUserID, destinationId, destinationId, startDate, endDate);
                        itineraryController.createItinerary(newItinerary);
                    }
                    break;

                case 4:
                    if (currentUserID != -1) {
                        
                        List<Itinerary> itineraries = itineraryController.getItinerariesByUser(currentUserID);
                        for (Itinerary itinerary : itineraries) {
                            System.out.println("Itinerary ID: " + itinerary.getId());
                            System.out.println("Destination ID: " + itinerary.getDestinationId());
                            System.out.println("Start Date: " + itinerary.getStartDate());
                            System.out.println("End Date: " + itinerary.getEndDate());
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    if (currentUserID != -1) {
                       
                        System.out.print("Enter itinerary ID to delete: ");
                        int itineraryIdToDelete = scanner.nextInt();
                        itineraryController.deleteItinerary(itineraryIdToDelete);
                    }
                    break;

                case 0:
                    if (currentUserID != -1) {
                        System.out.println("Logged out.");
                        currentUserID = -1;
                    } else {
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(dateFormat.parse(dateString).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
