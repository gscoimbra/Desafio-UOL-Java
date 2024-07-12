package dao;
//Como se fosse o controller(nao estamos usando pq nao estamos usando o spring)
//Basicamente faz as operações do CRUD, interage com as entidades e faz as operações

import config.JPAUtil;
import entity.Abrigo;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbrigoDAO {

    public void save(Abrigo abrigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(abrigo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Abrigo find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Abrigo.class, id);
        } finally {
            em.close();
        }
    }

    public List<Abrigo> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Abrigo", Abrigo.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Abrigo abrigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(abrigo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Abrigo abrigo = em.find(Abrigo.class, id);
            if (abrigo != null) {
                em.remove(abrigo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Abrigo findWithDoacoes(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Abrigo> query = em.createQuery(
                    "SELECT a FROM Abrigo a LEFT JOIN FETCH a.doacoes WHERE a.id = :id", Abrigo.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getQuantidadeAtualDeItensPorTipo(int abrigoId, String tipoItem) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COALESCE(SUM(d.quantidade), 0) FROM Doacao d WHERE d.abrigo.id = :abrigoId AND d.tipoItem = :tipoItem",
                    Long.class
            );
            query.setParameter("abrigoId", abrigoId);
            query.setParameter("tipoItem", tipoItem);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}