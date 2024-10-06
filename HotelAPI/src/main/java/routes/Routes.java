package routes;


import config.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Routes {

    static EntityManagerFactory emf= HibernateConfig.getEntityManagerFactory("hotel");

    private static HotelRoutes hotelRoutes = new HotelRoutes(emf);
    private static RoomRoutes roomRoutes = new RoomRoutes(emf);


    public static EndpointGroup getRoutes() {
        return () -> {
            get("/", ctx -> ctx.result("Hello World"));
            path("/hotels", hotelRoutes.getRoutes());
            path("/rooms", roomRoutes.getRoutes());
        };
    }
}