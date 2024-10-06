package routes;

import Controller.HotelController;
import Controller.RoomController; // Ensure you import the RoomController
import daos.HotelDAO;
import daos.RoomDAO;
import jakarta.persistence.EntityManagerFactory;
import security.controllers.SecurityController;
import security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomRoutes {
    private final RoomController roomController;
    private final SecurityController securityController;

    // Constructor accepting EntityManagerFactory
    public RoomRoutes(EntityManagerFactory emf) {
        RoomDAO roomDAO = new RoomDAO(emf);
        HotelDAO hotelDAO = new HotelDAO(emf);


        // Pass the RoomDAO to the RoomController
        this.roomController = new RoomController(roomDAO,hotelDAO);
        this.securityController = SecurityController.getInstance(); // Singleton instance of SecurityController
    }
    public EndpointGroup getRoutes() {
        return () -> {
            // Define routes for room management
            get("/{id}", roomController::getRoomById, Role.ANYONE); // Fetch room by ID
            before(securityController.authenticate()); // Check for valid token in the header
            post("/", roomController::createRoom, Role.ADMIN); // Create a new room
            //delete("/{id}", roomController::delete, Role.ADMIN); // Delete a room by ID
            //put("/{id}", roomController::update, Role.ADMIN); // Update room details
        };
    }
}
