package org.example.exo.billeterie.db.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class BaseRepository<T> {
    protected EntityManager em;

    public boolean create(T item){
        try{
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
            return false;
        }
    }

    public T selectById(Class<T> classe, long id){
        return em.find(classe,id);
    }

    public List<T> selectAll(Class<T> classe){
        String className = classe.getSimpleName();
        String queryString = "select a from " + className + " a";
        TypedQuery query = em.createQuery(queryString, classe);
        return query.getResultList();
    }

    public boolean update(T item){
        try{
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean delete(T item){
        try{
            em.getTransaction().begin();
            em.remove(item);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
            return false;
        }
    }

}

