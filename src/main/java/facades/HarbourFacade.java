package facades;

import dtos.HarbourDTO;
import entities.Auction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HarbourFacade {

    public HarbourFacade() {
    }

    private static HarbourFacade instance;
    private static EntityManagerFactory emf;

    public List<HarbourDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Auction> query = em.createQuery("SELECT p FROM Auction p", Auction.class);
        List<Auction> rms = query.getResultList();
        return HarbourDTO.getDtos(rms);
    }

    public static HarbourFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

}