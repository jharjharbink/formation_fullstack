package org.example.ihm.input.gestions;

import org.example.db.model.ArticleSale;
import org.example.ihm.enums.navigation.MenuType;
import org.example.service.ArticleSaleService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportUserInput extends BaseUserInput{
    public ReportUserInput(Scanner scanner) {
        super(scanner);
    }

    public MenuType askArticleSaleByArticleId() {
        ArticleSaleService articleSaleService = new ArticleSaleService();

        long articleId = askInt("Quel est l'Id de l'article dont vous voulez voir les ventes");

        List<ArticleSale> articleSales = articleSaleService.selectByArticle(articleId);

        if (articleSales != null && !articleSales.isEmpty()) {
            for (ArticleSale articleSale : articleSales) {
                System.out.println("ArticleSale trouvé: " + articleSale);
            }
        } else {
            System.out.println("Aucun ArticleSale trouvé pour l'article avec l'id " + articleId);
        }

        return MenuType.SALE_GESTION_MENU;
    }

    public MenuType askArticleSaleByClientId() {    // todo string builder to have the same
        ArticleSaleService articleSaleService = new ArticleSaleService();

        long clientId = askInt("Quel est l'Id du client dont vous voulez voir les ventes");

        List<ArticleSale> articleSales = articleSaleService.selectByClient(clientId);

        if (articleSales != null && !articleSales.isEmpty()) {
            for (ArticleSale articleSale : articleSales) {
                System.out.println("ArticleSale trouvé: " + articleSale);
            }
        } else {
            System.out.println("Aucun ArticleSale trouvé pour l'article avec l'id " + clientId);
        }

        return MenuType.SALE_GESTION_MENU;
    }


}
