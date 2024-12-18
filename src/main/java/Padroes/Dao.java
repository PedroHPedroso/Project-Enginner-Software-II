package Padroes;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.Livro;
import model.ItemEmprestimo;
import javafx.scene.control.Alert;

public class Dao<T> {

    private final Class<T> classe;

    public Dao(Class<T> classe) {
        this.classe = classe;
    }

    public T alterar(T objeto) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            objeto = manager.merge(objeto);
            tx.commit();
            return objeto;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    public T buscarPorCampo(String campo, Object valor) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        T resultado = null;

        try {
            String jpql = "SELECT e FROM " + classe.getSimpleName() + " e WHERE e." + campo + " = :valor";
            Query query = manager.createQuery(jpql, classe);
            query.setParameter("valor", valor);

            resultado = (T) query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return resultado;
    }
    
    public List<T> buscarPorCampos(String campo, Object valor) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        List<T> resultados = null;

        try {
            String jpql = "SELECT e FROM " + classe.getSimpleName() + " e WHERE e." + campo + " = :valor";
            Query query = manager.createQuery(jpql, classe);
            query.setParameter("valor", valor);

            resultados = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return resultados;
    }

    public void inserir(T objeto) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            manager.persist(objeto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public Livro buscarPorISBN(String isbn) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        Livro resultado = null;

        try {
            String jpql = "SELECT l FROM Livro l JOIN l.titulo t WHERE t.isbn = :isbn";
            Query query = manager.createQuery(jpql, Livro.class);
            query.setParameter("isbn", isbn);
            resultado = (Livro) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return resultado;
    }

    public void atualizar(T entidade) {
        EntityManager manager = Singleton.getInstance().getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.merge(entidade);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar a entidade: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    public List<T> listarTodos() {
        EntityManager manager = Singleton.getInstance().getEntityManager();

        try {
            CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            return manager.createQuery(query).getResultList();
        } finally {
            manager.close();
        }
    }
}
