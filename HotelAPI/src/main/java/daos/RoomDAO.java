package daos;

import entities.Hotel;
import entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class RoomDAO extends DAO<Room> {

    private final EntityManagerFactory entityManagerFactory;

    public RoomDAO(EntityManagerFactory emf) {
        super(Room.class);
        this.entityManagerFactory = emf;
    }
}
