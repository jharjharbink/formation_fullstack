package org.example.service;

import org.example.db.model.ArticleSale;
import org.example.db.model.Client;
import org.example.db.model.article.Article;
import org.example.db.repository.ArticleSaleRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ArticleSaleService {

    ArticleSaleRepository articleSaleRepository;

    public ArticleSaleService() {
        this.articleSaleRepository = new ArticleSaleRepository();
    }

    public List<ArticleSale> selectAll (){
        return articleSaleRepository.selectAll(ArticleSale.class);
    }

    // public List<ArticleSale> selectByClient (Client client){return;}

    public List<ArticleSale> selectByArticle (long articleId){
        return articleSaleRepository.getArticleSalesByArticleId(articleId);
    }


    public List<ArticleSale> selectByClient(long clientId) {
        return articleSaleRepository.getArticleSalesByClientId(clientId);
    }
}
