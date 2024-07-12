package dao;

import config.JPAUtil;
import entity.CentroDeDistribuicao;
import entity.Doacao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DoacaoDAO {

    public void save(Doacao doacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Doacao find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Doacao.class, id);
        } finally {
            em.close();
        }
    }

    public List<Doacao> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Doacao", Doacao.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Doacao doacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(doacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, id);
            if (doacao != null) {
                em.remove(doacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Doacao> findByTipoItemAndQuantidade(String tipoItem, int quantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Doacao> query = em.createQuery(
                    "SELECT d FROM Doacao d WHERE d.tipoItem = :tipoItem ORDER BY ABS(d.quantidade - :quantidade) ASC",
                    Doacao.class);
            query.setParameter("tipoItem", tipoItem);
            query.setParameter("quantidade", quantidade);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> findDistinctTipoItem() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT d.tipoItem FROM Doacao d", String.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Doacao> findByTipoItem(String tipoItem) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Doacao d WHERE d.tipoItem = :tipoItem", Doacao.class)
                    .setParameter("tipoItem", tipoItem)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void updateCentroDeDistribuicao(int doacaoId, int novoCentroId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, doacaoId);
            CentroDeDistribuicao centroDeDistribuicao = em.find(CentroDeDistribuicao.class, novoCentroId);
            if (doacao != null) {
                doacao.setCentroDeDistribuicao(centroDeDistribuicao);
                em.merge(doacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}