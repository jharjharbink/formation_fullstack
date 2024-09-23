package org.example.ihm.menu.article.update;

import org.example.ihm.enums.update.article.ArticleUpdateChoice;

public interface ArticleUpdateMenus {
    default ArticleUpdateChoice menuOptions(int userMenuChoice) {return null;}
}
