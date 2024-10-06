package daos;

import config.HibernateConfig;
import entities.Hotel;
import entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class DAO<T> implements IDAO<T> {

    private final Class<T> entityClass;


    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");

    EntityManager em = emf.createEntityManager();

    protected DAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEmf(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }

    @Override
    public List<T> getAll(){
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }



     @Override
     public T getById(int id) {
        return em.find(entityClass, id);
}

    @Override
    public void create(T entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }



    @Override
    public void update(T entity) {
        em.merge(entity);

    }

    @Override
    public void delete(int id) {
        em.remove(em.find(entityClass, id));

    }

}
