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

    public AuctionDTO createAuction(AuctionDTO pn) {
        Auction auction = new Auction(pn.getName(), pn.getDate(), pn.getTime(),pn.getLocation());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(auction);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AuctionDTO(auction);
    }

    public AuctionDTO updateAuction(AuctionDTO pn) {
        EntityManager em = emf.createEntityManager();
        Auction a = (em.find(Auction.class, pn.getId()));
        try {
            a.setName(pn.getName()); a.setDate(pn.getDate()); a.setTime(pn.getTime());a.setLocation(pn.getLocation());
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AuctionDTO(a);

    }

    public void deleteAuction(int pn) {
        EntityManager em = emf.createEntityManager();
        Auction a = (em.find(Auction.class, (long) pn));
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public static AuctionFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AuctionFacade();
        }
        return instance;
    }

}