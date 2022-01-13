package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
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

    public OwnerDTO updateOwner(OwnerDTO pn) {
        EntityManager em = emf.createEntityManager();
        Owner o = (em.find(Owner.class, pn.getId()));
        try {
            o.setName(pn.getName()); o.setPhone(pn.getPhone()); o.setEmail(pn.getEmail());
            em.getTransaction().begin();
            o = em.merge(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new OwnerDTO(o);

    }

    public static OwnerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

}
