package org.example.db.repository;

import org.example.db.model.article.Article;
import org.hibernate.query.Query;

import java.util.List;

public class ArticleRepository extends BaseRepository<Article>{
    public ArticleRepository() {
        super();
    }

    public List<Article> selectArticleByDescription(String articleDescription){
        List<Article> articleList;
        Query<Article> sellQuery;

        session = sessionFactory.openSession();
        sellQuery = session.createQuery("from Article where description = :articleDescription", Article.class);
        sellQuery.setParameter("articleDescription", articleDescription);

        articleList = sellQuery.list();
        session.close();
        return articleList;
    }
}
