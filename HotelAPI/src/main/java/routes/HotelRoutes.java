package routes;

import Controller.HotelController; // Ensure you import the HotelController
import daos.HotelDAO;
import jakarta.persistence.EntityManagerFactory;
import security.controllers.SecurityController;
import security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HotelRoutes {

    private final HotelController hotelController;
    private final SecurityController securityController;

    // Constructor accepting EntityManagerFactory
    public HotelRoutes(EntityManagerFactory emf) {
        // Initialize the HotelDAO with the EntityManagerFactory
        HotelDAO hotelDao = new HotelDAO(emf);

        // Pass the HotelDAO to the HotelController
        this.hotelController = new HotelController(hotelDao);
        this.securityController = SecurityController.getInstance(); // Singleton instance of SecurityController
    }

    // Define your routes here
    public EndpointGroup getRoutes() {
        return () -> {
            // Define routes for hotel management
            get("/all",hotelController::getAllHotels, Role.ANYONE);
            get("/{id}", hotelController::getHotelById, Role.ANYONE); // Fetch hotel by ID
            before(securityController.authenticate()); // Check for valid token in the header
            post("/", hotelController::createHotel, Role.ADMIN); // Create a new hotel
            delete("/{id}", hotelController::delete, Role.ADMIN); // Delete a hotel by ID
            put("/{id}", hotelController::update, Role.ADMIN); // Update hotel details
        };
    }
}