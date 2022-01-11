package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class BoatFacade {
    public BoatFacade() {
    }

    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    public static BoatFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }


    public List<BoatDTO> getBoatByHarbour(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT  p FROM Boat p INNER JOIN p.harbour h WHERE h.name = :name", Boat.class);
        query.setParameter("name", name);
        List<Boat> rms = query.getResultList();
        return BoatDTO.getDtos(rms);
    }

    public List<OwnerDTO> getOwnersByBoat(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT  p FROM Owner p INNER JOIN p.boatList h WHERE h.name = :name", Owner.class);
        query.setParameter("name", name);
        List<Owner> rms = query.getResultList();
        return OwnerDTO.getDtos(rms);
    }

    public BoatDTO create(BoatDTO pn) {
        Boat boat =
                new Boat(pn.getBrand(), pn.getMake(), pn.getName());

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }
}
