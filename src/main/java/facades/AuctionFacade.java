package facades;

import dtos.AuctionDTO;
import entities.Auction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuctionFacade {

    public AuctionFacade() {
    }

    private static AuctionFacade instance;
    private static EntityManagerFactory emf;

    public List<AuctionDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Auction> query = em.createQuery("SELECT p FROM Auction p", Auction.class);
        List<Auction> rms = query.getResultList();
        return AuctionDTO.getDtos(rms);
    }

    public static AuctionFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AuctionFacade();
        }
        return instance;
    }

}