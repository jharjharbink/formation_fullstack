package org.example.service;

import org.example.db.SaleState;
import org.example.db.model.ArticleSale;
import org.example.db.model.Client;
import org.example.db.model.Sale;
import org.example.db.model.article.Article;
import org.example.db.repository.SaleRepository;

import java.util.Date;
import java.util.Map;

import static org.example.util.DatesDealer.getTodaysDate;

public class SaleService {

    private SaleRepository saleRepository;

    public SaleService() {
        this.saleRepository = new SaleRepository();
    }

    public Sale beginSale(Client client) {
        SaleState saleState = SaleState.IN_PROGRESS;
        Date todayDate = getTodaysDate();
        return new Sale(saleState, todayDate, client);
    }

    public int getdeliveredQuantity(int currentStockQuantity, int askedQuantity) {
        if (currentStockQuantity > askedQuantity)
            return askedQuantity;
        return currentStockQuantity;
    }

    public Sale addArticleSale(Sale sale, Article chosenArticle, int deliveredQuantity) {
        sale.addArticleSale(new ArticleSale(deliveredQuantity, chosenArticle, sale));
        return sale;
    }

    public boolean create(Sale sale, SaleState saleState){
        sale.setState(saleState);
        return saleRepository.create(sale);
    }

    public void updateStockAfterSale(Map<Long, Integer> articleIdAndDeliveredQuantity) {
        ArticleService articleService = new ArticleService();

        for (Map.Entry<Long, Integer> entry : articleIdAndDeliveredQuantity.entrySet()) {
            long boughtArticle = entry.getKey();
            int boughtQuantity = entry.getValue();
            articleService.updateStock(boughtArticle, -boughtQuantity);
        }
    }
}
