package org.example.ihm.input.gestions;

import org.example.db.ArticleTypes;
import org.example.db.ClotheCategory;
import org.example.db.Size;
import org.example.db.model.article.Article;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.enums.update.article.ArticleUpdateChoice;
import org.example.ihm.Ihm;
import org.example.ihm.input.actions.article.*;
import org.example.ihm.menu.article.update.ArticleUpdateMenus;
import org.example.ihm.menu.article.update.ClotheUpdateMenu;
import org.example.ihm.menu.article.update.ElectronicUpdateMenu;
import org.example.ihm.menu.article.update.FoodUpdateMenu;
import org.example.ihm.enums.update.article.ClotheUpdateChoices;
import org.example.ihm.enums.update.article.ElectronicUpdateChoice;
import org.example.ihm.enums.update.article.FoodUpdateChoice;
import org.example.service.ArticleService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.MenuType.*;

public class ArticleUserInput extends BaseUserInput {

    ArticleService articleService;

    public ArticleUserInput(Scanner scanner) {
        super(scanner);
        this.articleService = new ArticleService();

    }

    public MenuType askElectronicCreation(){

        String description = askString("Quel est sa description ?");
        Double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        int batteryMaxCapacity = askInt("Quel est sa capacité en batterie ? (en Ampère heure)");

        boolean isCreated = articleService.createElectronic(description, price,stockQuantity, batteryMaxCapacity);

        if (isCreated)
            System.out.println("Article d'électronique Créer");
        else
            System.out.println("Problème à la création");
        return ARTICLE_CREATION_MENU;

    }

    public MenuType askClotheCreation(){
        String description = askString("Quel est sa description ?");
        double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        ClotheCategory clotheCategory = askClotheCategory();
        Size clotheSize = askSize();

        boolean isCreated = articleService.createClothe(description, price, stockQuantity, clotheCategory, clotheSize);

        if (isCreated)
            System.out.println("Vêtement Créer");
        else
            System.out.println("Problème à la création");
        return ARTICLE_CREATION_MENU;
    }

    public MenuType askFoodCreation(){
        String description = askString("Quel est sa description ?");
        Double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        Date expirationDate = askDate("Quel est sa date de péremption ?");

        boolean isCreated = articleService.createFood(description, price, stockQuantity, expirationDate);

        if (isCreated)
            System.out.println("Vêtement Créer");
        else
            System.out.println("Problème à la création");
        return ARTICLE_CREATION_MENU;

    }

    public MenuType askUpdateArticle(ArticleTypes articleType){

        long articleId = askLong("Quel est l'id du vêtement à mettre à jour");
        ArticleUpdateChoice itemToChange = getColumnToChange(articleType);
        Object valueToChange = askValueToChange(itemToChange);

        boolean isUpdated;
        switch (articleType){
            case CLOTHE -> isUpdated = articleService.updateClothe(articleId, itemToChange, valueToChange);
            case ELECTRONIC -> isUpdated = articleService.updateElectronic(articleId, itemToChange, valueToChange);
            case FOOD -> isUpdated = articleService.updateFood(articleId, itemToChange, valueToChange);
            default -> throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
        return MenuType.ARTICLE_UPDATE_MENU;
    }

    private ArticleUpdateChoice getColumnToChange(ArticleTypes articleType){
        MenuType updateMenuType;
        ArticleUpdateMenus menu;
        switch(articleType){
            case ArticleTypes.CLOTHE:
                updateMenuType = CLOTHE_UPDATE_MENU;
                menu = new ClotheUpdateMenu();
                break;
            case ArticleTypes.ELECTRONIC:
                updateMenuType = ELECTRONIC_UPDATE_MENU;
                menu = new ElectronicUpdateMenu();
                break;
            case ArticleTypes.FOOD:
                updateMenuType = FOOD_UPDATE_MENU;
                menu = new FoodUpdateMenu();
                break;
            default:
                throw new WrongSearchChoiceException("problem in askUpdateArticle");
        }

        int userChoice = Ihm.askUserMenuChoice(updateMenuType);
        return menu.menuOptions(userChoice);
    }

    private Object askValueToChange(ArticleUpdateChoice itemToChange){
        UserInput<?> updateChoice;
        String message;

        switch (itemToChange) {
            case ClotheUpdateChoices.DESCRIPTION:
                updateChoice = new StringUserInput(scanner);
                message = "Quel est sa description ?";
                break;
            case ClotheUpdateChoices.PRICE:
                updateChoice = new DoubleUserInput(scanner);
                message = "Quel est son prix ?";
                break;
            case ClotheUpdateChoices.STOCK_QUANTITY:
                updateChoice = new IntUserInput(scanner);
                message = "Quel est sa quantité en stock ?";
                break;
            case ClotheUpdateChoices.CATEGORY:
                updateChoice = new ClotheCategoryUserInput(scanner);
                message = "Quel est sa catégorie ?";
                break;
            case ClotheUpdateChoices.SIZE:
                updateChoice = new ClotheSizeUserInput(scanner);
                message = "Quel est sa taille ?";
                break;
            case ElectronicUpdateChoice.BATTERY_MAX_CAPACITY:
                updateChoice = new IntUserInput(scanner);
                message = "Quel est sa capacité de batterie ?";
                break;
            case FoodUpdateChoice.EXPIRATION_DATE:
                updateChoice = new DateUserInput(scanner);
                message = "Quel est sa date d'expiration ? (format 13/01/2001)";
                break;
            default:
                throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }
        return updateChoice.askValue(message);
    }

    public MenuType askDeleteArticle() throws NotFoundException {
        long articleId = askLong("Quel est l'id de l'article que vous voulez supprimer ?");
        boolean isDeleted = articleService.delete(articleId);

        if (isDeleted)
            System.out.println("Article Supprimer");
        else
            System.out.println("Problème à la suppression");
        return ARTICLE_GESTION_MENU;

    }

    public MenuType askArticleById() throws NotFoundException {
        long articleId = askLong("Quel est l'id de l'article que vous voulez consulter ?");
        Article article = articleService.selectById(articleId);

        System.out.println(article);
        return ARTICLE_CONSULT_MENU;
    }

    public MenuType askAllArticle(){
        List<Article> articles = articleService.selectAll();

        for (Article article : articles)
            System.out.println(article);
        return ARTICLE_CONSULT_MENU;
    }

    public MenuType askArticleByDescription(){
        String articleDescription = askString("Quel est la Description de l'article que vous voulez consulter ?");

        List<Article> articles = articleService.selectByDescription(articleDescription);

        for (Article article : articles)
            System.out.println(article);
        return ARTICLE_CONSULT_MENU;
    }

    public MenuType askRestock(){
        long articleId = askLong("Quel est l'id de l'article que vous voulez resotcker ?");
        int addedQuantity = askInt("De combien d'élement allez vous restocker ?");
        boolean isRestock = articleService.updateStock(articleId, addedQuantity);

        if (isRestock)
            System.out.println("Article restocker");
        else
            System.out.println("Problème à la restockation");
        return ARTICLE_GESTION_MENU;
    }
}
