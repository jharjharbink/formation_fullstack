package org.example.db.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;


@Getter
@AllArgsConstructor
public abstract class BaseRepository<T> {

    protected SessionFactory sessionFactory;
    protected Session session;

    public BaseRepository(){
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public boolean create (T item) {
        try {
            openSession();
            beginTransaction();
            session.save(item);
            commit();
        } catch (Exception e) {
            rollback();
            return false;
        } finally {
            closeSession();
        }
        return true;
    }

    public T selectById(Class<T> classe, long id){
        openSession();
        T item = session.get(classe, id);
        closeSession();
        return item;
    }

    public List<T> selectAll(Class<T> classe){
        openSession();
        Query<T> selectAllItemQuery = session.createQuery("from " + classe.getSimpleName(), classe);
        List<T> customersList = selectAllItemQuery.list();
        closeSession();
        return customersList;
    }

    public boolean update(T item){
        try {
            openSession();
            beginTransaction();
            session.update(item);
            commit();
        } catch (Exception e) {
            rollback();
            return false;
        } finally {
            closeSession();
        }
        return true;
    }

    public boolean delete(T item){
        try {
            openSession();
            beginTransaction();
            session.delete(item);
            commit();
        } catch (Exception e) {
            rollback();
            return false;
        } finally {
            closeSession();
        }
        return true;
    }

    private void openSession(){
        session = sessionFactory.openSession();
    }

    private void closeSession(){
        session.close();
    }

    private void beginTransaction(){
        session.beginTransaction();
    }

    private void rollback(){
        session.getTransaction().rollback();
    }

    private void commit(){
        session.getTransaction().commit();
    }

}

