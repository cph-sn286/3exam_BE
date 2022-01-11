package facades;

import dtos.OwnerDTO;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OwnerFacade {

    public OwnerFacade() {
    }

    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    public List<OwnerDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT p FROM Owner p", Owner.class);
        List<Owner> rms = query.getResultList();
        return OwnerDTO.getDtos(rms);
    }

    public static OwnerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

}
