package dao;

import config.JPAUtil;
import entity.CentroDeDistribuicao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CentroDeDistribuicaoDAO {


    public void save(CentroDeDistribuicao centroDeDistribuicao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(centroDeDistribuicao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public CentroDeDistribuicao find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(CentroDeDistribuicao.class, id);
        } finally {
            em.close();
        }
    }

    public List<CentroDeDistribuicao> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM CentroDeDistribuicao", CentroDeDistribuicao.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(CentroDeDistribuicao centroDeDistribuicao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(centroDeDistribuicao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CentroDeDistribuicao centroDeDistribuicao = em.find(CentroDeDistribuicao.class, id);

            if (centroDeDistribuicao != null) {
                em.remove(centroDeDistribuicao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public CentroDeDistribuicao findCentroDeDistribuicaoById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(CentroDeDistribuicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuantidadeAtualDeItensPorTipo(int centroId, String tipoItem) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COALESCE(SUM(d.quantidade), 0) FROM Doacao d WHERE d.centroDeDistribuicao.id = :centroId AND d.tipoItem = :tipoItem",
                    Long.class
            );
            query.setParameter("centroId", centroId);
            query.setParameter("tipoItem", tipoItem);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public boolean isNomeUnico(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM CentroDeDistribuicao c WHERE c.nome = :nome", Long.class);
            query.setParameter("nome", nome);
            return query.getSingleResult() == 0;
        } finally {
            em.close();
        }
    }
}
