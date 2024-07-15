// O DAO(Data Access Object) é um padrão de arquitetura ou design que fornece uma interface para realizar operações de CRUD em um banco de dados.
// Ao invés de interagir diretamente com o banco de dados, uso o DAO para encapsular essa interações.

package dao;

import config.JPAUtil;
import entity.Abrigo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbrigoDAO {

    // Adiciono o objeto Abrigo no banco
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

    // Busco o objeto abrigo
    public Abrigo find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Abrigo.class, id);
        } finally {
            em.close();
        }
    }

    // Listo todos os objetos abrigo
    public List<Abrigo> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Abrigo", Abrigo.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Procuro todas as doações associadas aos abrigos
    public List<Abrigo> findAllWithDoacoes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Abrigo a LEFT JOIN FETCH a.doacoes", Abrigo.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Atualizo o abrigo
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

    // Deleto um abrigo
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

    // Esse método uso para encontrar as doações associadas aos abrigos, ou seja, as doações que foram para os abrigos
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

    // Verifico quanto de cada item tem no abrigo
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

    // Validação para ver se já existe o nome
    public boolean isNomeUnico(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(a) FROM Abrigo a WHERE a.nome = :nome", Long.class);
            query.setParameter("nome", nome);
            return query.getSingleResult() == 0;
        } finally {
            em.close();
        }
    }
}
