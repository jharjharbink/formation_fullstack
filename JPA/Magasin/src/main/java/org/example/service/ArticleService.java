package org.example.service;

import org.example.db.ClotheCategory;
import org.example.db.Size;
import org.example.db.model.article.Article;
import org.example.db.model.article.Clothe;
import org.example.db.model.article.Electronic;
import org.example.db.model.article.Food;
import org.example.db.repository.ArticleRepository;
import org.example.exceptions.NotFoundException;
import org.example.ihm.enums.navigation.StockAvailability;
import org.example.ihm.enums.update.article.ArticleUpdateChoice;
import org.example.ihm.enums.update.article.ClotheUpdateChoices;
import org.example.ihm.enums.update.article.ElectronicUpdateChoice;
import org.example.ihm.enums.update.article.FoodUpdateChoice;

import java.util.Date;
import java.util.List;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

    public boolean createElectronic(String description,double price, int stockQuantity,int  batteryMaxCapacity) {
        Electronic electronic = Electronic.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .batteryMaxCapacity(batteryMaxCapacity)
                .build();

        return articleRepository.create(electronic);
    }

    public boolean createClothe(String description, double price, int stockQuantity, ClotheCategory category, Size size) {
        Clothe clothe = Clothe.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .category(category)
                .size(size)
                .build();

        return articleRepository.create(clothe);
    }

    public boolean createFood(String description, double price, int stockQuantity, Date expirationDate) {
        Food food = Food.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .expirationDate(expirationDate)
                .build();

        return articleRepository.create(food);
    }

    public <T> boolean updateClothe(long articleId, ArticleUpdateChoice itemToChange, T valueToChange){
        Article article =  articleRepository.selectById(Article.class, articleId);
        if (article instanceof Clothe clothe){
            switch(itemToChange){
                case ClotheUpdateChoices.DESCRIPTION -> clothe.setDescription((String) valueToChange);
                case ClotheUpdateChoices.PRICE -> clothe.setPrice((double) valueToChange);
                case ClotheUpdateChoices.STOCK_QUANTITY -> clothe.setStockQuantity((int) valueToChange);
                case ClotheUpdateChoices.CATEGORY -> clothe.setCategory((ClotheCategory) valueToChange);
                case ClotheUpdateChoices.SIZE -> clothe.setSize((Size) valueToChange);
                default -> throw new IllegalStateException("Unexpected value: " + itemToChange);
            }
            return articleRepository.update(clothe);
        }
        return false;
    }

    public <T> boolean updateElectronic(long articleId, ArticleUpdateChoice itemToChange, T valueToChange){
        Article article = articleRepository.selectById(Article.class, articleId);

        if (article instanceof Electronic electronic) {
            switch (itemToChange) {
                case ElectronicUpdateChoice.DESCRIPTION -> electronic.setDescription((String) valueToChange);
                case ElectronicUpdateChoice.PRICE -> electronic.setPrice((double) valueToChange);
                case ElectronicUpdateChoice.STOCK_QUANTITY -> electronic.setStockQuantity((int) valueToChange);
                case ElectronicUpdateChoice.BATTERY_MAX_CAPACITY -> electronic.setBatteryMaxCapacity((int) valueToChange);
                default -> throw new IllegalStateException("Unexpected value: " + itemToChange);
            }
            return articleRepository.update(electronic);
        }
        return false; //TODO make exception and catch it in UserInput
    }

    public <T> boolean updateFood(long articleId, ArticleUpdateChoice itemToChange, T valueToChange){
        Article article = articleRepository.selectById(Article.class, articleId);

        if (article instanceof Food food) {
            switch (itemToChange) {
                case FoodUpdateChoice.DESCRIPTION -> food.setDescription((String) valueToChange);
                case FoodUpdateChoice.PRICE -> food.setPrice((double) valueToChange);
                case FoodUpdateChoice.STOCK_QUANTITY -> food.setStockQuantity((int) valueToChange);
                case FoodUpdateChoice.EXPIRATION_DATE -> food.setExpirationDate((Date) valueToChange);
                default -> throw new IllegalStateException("Unexpected value: " + itemToChange);
            }
            return articleRepository.update(food);
        }
        return false; //TODO make exception and catch it in UserInput
    }

    public boolean updateStock(long articleId, int restockQuantity){
        Article article = articleRepository.selectById(Article.class, articleId);
        int newStockQuantity = article.getStockQuantity() + restockQuantity;
        article.setStockQuantity(newStockQuantity);
        return articleRepository.update(article);
    }

    public boolean delete (long id) throws NotFoundException {
        Article article = articleRepository.selectById(Article.class, id);
        if(article != null){
            return articleRepository.delete(article);
        }
        throw new NotFoundException("can't find client with id: " + id);
    }


    public Article selectById (long id) throws NotFoundException {
        Article article = articleRepository.selectById(Article.class, id);
        if(article != null){
            return article;
        }
        throw new NotFoundException("can't find client with id: " + id);
    }

    public List<Article> selectAll (){
        return articleRepository.selectAll(Article.class);
    }

    public List<Article> selectByDescription(String name){
        return articleRepository.selectArticleByDescription(name);
    }

    public StockAvailability getStockAvailability(){
        List<Article> allArticles = selectAll();
        return setStockAvailability(allArticles);
    }

    private StockAvailability setStockAvailability(List<Article> articleList){
        if (articleList == null)
            return StockAvailability.NULL_STOCK;

        if (articleList.isEmpty())
            return StockAvailability.EMPTY_STOCK;

        if (allArticlesZeroStock(articleList))
            return StockAvailability.ZERO_QUANTITY_STOCK;

        return StockAvailability.AVAILABLE_STOCK;
    }

    private boolean allArticlesZeroStock(List<Article> articles) {
        int totalStock = 0;
        for (Article article: articles)
            totalStock += article.getStockQuantity();
        return totalStock > 0;
    }
}
