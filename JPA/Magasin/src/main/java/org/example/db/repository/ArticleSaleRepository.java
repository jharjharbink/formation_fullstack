package org.example.db.repository;

import org.example.db.model.ArticleSale;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ArticleSaleRepository extends BaseRepository<ArticleSale> {
    public ArticleSaleRepository() {
        super();
    }

    public List<ArticleSale> getArticleSalesByArticleId(Long articleId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<ArticleSale> articleSales = null;

        try {
            transaction = session.beginTransaction();
            String hql = "FROM ArticleSale WHERE article.id = :articleId";
            Query<ArticleSale> query = session.createQuery(hql, ArticleSale.class);
            query.setParameter("articleId", articleId);
            articleSales = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return articleSales;
    }

    public List<ArticleSale> getArticleSalesByClientId(long clientId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<ArticleSale> articleSales = null;

        try {
            transaction = session.beginTransaction();
            String sql = "FROM ArticleSale WHERE article.id = :articleId";
            Query<ArticleSale> query = session.createQuery(sql, ArticleSale.class);
            query.setParameter("articleId", articleId);// todo non c'est pas pareil qu'à côté, faut récupérer les sample, puis les clients.
            articleSales = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return articleSales;
    }
}
