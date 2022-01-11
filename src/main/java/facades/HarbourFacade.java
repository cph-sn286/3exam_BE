package facades;

import dtos.HarbourDTO;
import dtos.OwnerDTO;
import entities.Harbour;
import entities.Owner;

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
        TypedQuery<Harbour> query = em.createQuery("SELECT p FROM Harbour p", Harbour.class);
        List<Harbour> rms = query.getResultList();
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