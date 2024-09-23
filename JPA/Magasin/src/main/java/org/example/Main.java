package org.example;

import org.example.db.ClotheCategory;
import org.example.db.SaleState;
import org.example.db.Size;
import org.example.db.model.*;
import org.example.db.model.article.Clothe;
import org.example.db.model.article.Electronic;
import org.example.db.model.article.Food;
import org.example.ihm.Ihm;
import org.example.ihm.enums.navigation.MenuType;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.example.util.DatesDealer.getNextWeekDate;
import static org.example.util.DatesDealer.getTodaysDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Ihm.start(MenuType.MAIN_MENU);
    }

    private void initDbWithValues(){
        SessionFactory sessionFactory = SessionfactorySingleton.getSessionFactory();
        Session session = sessionFactory.openSession();


        // init db
        Food carottes = new Food("carotte", 1.1, 100, getNextWeekDate());
        Electronic computers = new Electronic("computer", 1000, 100, 4);
        Clothe jeansMale = new Clothe("jean H L", 89.99, 100, ClotheCategory.MALE, Size.L);
        Clothe jeansWoman = new Clothe("jean F L", 89.99, 100, ClotheCategory.FEMALE, Size.L);

        Client gerard = new Client("Gerard", "gerardbgdu62@gerard.com");

        // init sale
        Sale gerardSale = new Sale(SaleState.IN_PROGRESS, getTodaysDate(), gerard);

        // set sale content
        ArticleSale gerardBuyCarottes = new ArticleSale(3, carottes, gerardSale);
        gerardSale.addArticleSale(gerardBuyCarottes);

        // init db
        session.beginTransaction();
        session.save(carottes);
        session.save(computers);
        session.save(jeansMale);
        session.save(jeansWoman);
        session.save(gerard);

        session.getTransaction().commit();


        session.beginTransaction();

        session.save(gerardSale);

        session.getTransaction().commit();
        session.beginTransaction();

        session.save(gerardBuyCarottes);

        // clothe sale
        gerardSale.setState(SaleState.FINALISED);

        session.getTransaction().commit();

        session.close();
    }
}