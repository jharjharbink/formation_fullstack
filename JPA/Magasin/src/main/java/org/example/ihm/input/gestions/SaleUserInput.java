package org.example.ihm.input.gestions;

import org.example.db.SaleState;
import org.example.db.model.Client;
import org.example.db.model.Sale;
import org.example.db.model.article.Article;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.Ihm;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.enums.navigation.SaleGestion;
import org.example.ihm.enums.navigation.StockAvailability;
import org.example.ihm.menu.sale.SaleMenu;
import org.example.service.ArticleService;
import org.example.service.ClientService;
import org.example.service.SaleService;

import java.util.*;

public class SaleUserInput extends BaseUserInput{
    ArticleService articleService;
    SaleService saleService;

    public SaleUserInput(Scanner scanner) {
        super(scanner);
        articleService = new ArticleService();
        saleService = new SaleService();
    }

    public MenuType beginSale() {
        if (stockIsEmpty())
            return MenuType.SALE_GESTION_MENU;

        if (clientsIsEmpty())
            return MenuType.SALE_GESTION_MENU;

        Map<Long, Integer> articleIdAndDeliveredQuantity = new HashMap<>();  // In order to set stockQuantity once transaction finished

        Client client = getClient();
        Sale sale = saleService.beginSale(client);  // Sale created here to be able to use SaleState.IN_PROGRESS

        while(true){

            String articleDescription = askString("quel est la description de l'article que vous voulez acheter ?");
            List<Article> chosenArticleList = articleService.selectByDescription(articleDescription);

            if (articleListIsEmpty(chosenArticleList))
                continue;
            Article chosenArticle = getArticle(chosenArticleList);

            int askedQuantity = askInt("Combien voulez vous en acheter ?");
            int currentStockQuantity = chosenArticle.getStockQuantity();
            if (askedQuantity > currentStockQuantity)
                System.out.println("Nombre d'article insuffisant, vous en avez demandé " + askedQuantity + " mais vous n'en aurez que " + currentStockQuantity);

            int deliveredQuantity = saleService.getdeliveredQuantity(currentStockQuantity, askedQuantity);
            articleIdAndDeliveredQuantity.put(chosenArticle.getId(), deliveredQuantity);
            sale = saleService.addArticleSale(sale, chosenArticle, deliveredQuantity);

            int userChoice = Ihm.askUserMenuChoice(MenuType.SALE_MENU);
            SaleGestion saleGestion = SaleMenu.menuOptions(userChoice);

            if (saleGestion == SaleGestion.STOP)
                break;

            if (saleGestion == SaleGestion.ABANDON) {
                saleService.create(sale, SaleState.ABANDONED);
                return MenuType.SALE_GESTION_MENU;
            }
        }

        saleService.create(sale, SaleState.FINALISED);
        saleService.updateStockAfterSale(articleIdAndDeliveredQuantity);

        return MenuType.SALE_GESTION_MENU;
    }

    private boolean clientsIsEmpty() {
        ClientService clientService = new ClientService();
        List<Client> allClients = clientService.selectAll();
        if (allClients == null || allClients.isEmpty()) {
            System.out.println("Aucun client enreistré en BDD");
            return true;
        }
        return false;

    }

    private boolean stockIsEmpty(){
        StockAvailability stockAvailability = new ArticleService().getStockAvailability();
        switch(stockAvailability){
            case StockAvailability.AVAILABLE_STOCK:
                return false;
            case StockAvailability.NULL_STOCK, StockAvailability.EMPTY_STOCK:
                System.out.println("Aucun article disponible, veuillez rentrer des articles dans l'inventaire");
                return true;
            case StockAvailability.ZERO_QUANTITY_STOCK:
                System.out.println("Aucun article disponible, veuillez restocker des articles dans l'inventaire");
                return true;
            default: throw new WrongSearchChoiceException("Wrong search choice exception in stockAvailable: " + stockAvailability);
        }
    }

    private boolean articleListIsEmpty(List<Article> articleList) {

        if (articleList == null){
            System.out.println("No articles with the given description");
            return true;
        }
        if (articleList.isEmpty()){
            System.out.println("No articles with the given description");
            return true;
        }
        return false;
    }

    private Client getClient(){
        Client client;
        while(true){
            try{
                long clientId = askLong("quel est l'ID du client ?");
                client = new ClientService().selectById(clientId);
                break;
            } catch (NotFoundException e) {
                System.out.println("ce client n'existe pas");
            }
        }
        return client;
    }

    private Article getArticle(List<Article> articleList){
        if (articleList.size() == 1)
            return articleList.getFirst();
        else
            return getArticleFromArticles(articleList);
    }

    private Article getArticleFromArticles(List<Article> articleList){
        System.out.println("Voici les articles répondant à cette description");
        for (Article article: articleList )
            System.out.println(article);

        while (true){
            long chosenArticleId = askLong("Quel est l'id de l'article acheté ?");
            try{
                if (chosenArticleId > 0 || chosenArticleId < articleList.size() + 1){
                    return new ArticleService().selectById(chosenArticleId);
                }
            } catch (NotFoundException _) {}
            System.out.println("L'id de l'article n'existe pas");
        }
    }
}
