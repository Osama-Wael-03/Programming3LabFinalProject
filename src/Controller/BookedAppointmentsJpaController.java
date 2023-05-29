/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Appointments;
import Model.BookedAppointments;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author msi1
 */
public class BookedAppointmentsJpaController implements Serializable {

    public BookedAppointmentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BookedAppointments bookedAppointments) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointments appointmentId = bookedAppointments.getAppointmentId();
            if (appointmentId != null) {
                appointmentId = em.getReference(appointmentId.getClass(), appointmentId.getId());
                bookedAppointments.setAppointmentId(appointmentId);
            }
            em.persist(bookedAppointments);
            if (appointmentId != null) {
                appointmentId.getBookedAppointmentsCollection().add(bookedAppointments);
                appointmentId = em.merge(appointmentId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BookedAppointments bookedAppointments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BookedAppointments persistentBookedAppointments = em.find(BookedAppointments.class, bookedAppointments.getId());
            Appointments appointmentIdOld = persistentBookedAppointments.getAppointmentId();
            Appointments appointmentIdNew = bookedAppointments.getAppointmentId();
            if (appointmentIdNew != null) {
                appointmentIdNew = em.getReference(appointmentIdNew.getClass(), appointmentIdNew.getId());
                bookedAppointments.setAppointmentId(appointmentIdNew);
            }
            bookedAppointments = em.merge(bookedAppointments);
            if (appointmentIdOld != null && !appointmentIdOld.equals(appointmentIdNew)) {
                appointmentIdOld.getBookedAppointmentsCollection().remove(bookedAppointments);
                appointmentIdOld = em.merge(appointmentIdOld);
            }
            if (appointmentIdNew != null && !appointmentIdNew.equals(appointmentIdOld)) {
                appointmentIdNew.getBookedAppointmentsCollection().add(bookedAppointments);
                appointmentIdNew = em.merge(appointmentIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bookedAppointments.getId();
                if (findBookedAppointments(id) == null) {
                    throw new NonexistentEntityException("The bookedAppointments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BookedAppointments bookedAppointments;
            try {
                bookedAppointments = em.getReference(BookedAppointments.class, id);
                bookedAppointments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookedAppointments with id " + id + " no longer exists.", enfe);
            }
            Appointments appointmentId = bookedAppointments.getAppointmentId();
            if (appointmentId != null) {
                appointmentId.getBookedAppointmentsCollection().remove(bookedAppointments);
                appointmentId = em.merge(appointmentId);
            }
            em.remove(bookedAppointments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BookedAppointments> findBookedAppointmentsEntities() {
        return findBookedAppointmentsEntities(true, -1, -1);
    }

    public List<BookedAppointments> findBookedAppointmentsEntities(int maxResults, int firstResult) {
        return findBookedAppointmentsEntities(false, maxResults, firstResult);
    }

    private List<BookedAppointments> findBookedAppointmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookedAppointments.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BookedAppointments findBookedAppointments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookedAppointments.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookedAppointmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookedAppointments> rt = cq.from(BookedAppointments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
