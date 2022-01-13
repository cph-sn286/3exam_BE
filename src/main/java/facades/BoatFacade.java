package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

import static jdk.internal.org.jline.utils.Colors.h;

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




    public List<BoatDTO> getBoatByOwner(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT  p FROM Boat p INNER JOIN p.ownerList h WHERE h.name = :name", Boat.class);
        query.setParameter("name", name);
        List<Boat> rms = query.getResultList();
        return BoatDTO.getDtos(rms);
    }

    public BoatDTO create(BoatDTO pn) {
        Boat boat =
                new Boat(pn.getBrand(), pn.getMake(), pn.getName(),pn.getYear());

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

    public BoatDTO updateBoat(BoatDTO pn) {
        Boat b = new Boat(pn.getId(), pn.getName(), pn.getBrand(), pn.getMake(),pn.getYear());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            b = em.merge(b);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(b);

    }
}
