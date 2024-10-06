package daos;

import entities.Hotel;
import entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HotelDAO extends DAO<Hotel> {

    private final EntityManagerFactory entityManagerFactory;

    // Constructor accepting EntityManagerFactory
    public HotelDAO(EntityManagerFactory emf) {
        super(Hotel.class);
        this.entityManagerFactory = emf;  // Initialize the EntityManagerFactory
    }

    // Method to return the rooms of a given hotel
    public List<Room> returnRooms(Hotel hotel) {
        EntityManager em = entityManagerFactory.createEntityManager();  // Create EntityManager

        try {
            TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r WHERE r.hotel = :hotel", Room.class);
            query.setParameter("hotel", hotel);
            return query.getResultList();
        } finally {
            em.close();  // Ensure the EntityManager is closed after use
        }
    }
}



