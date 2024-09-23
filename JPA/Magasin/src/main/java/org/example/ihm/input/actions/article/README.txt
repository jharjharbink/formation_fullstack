this package have been created in order to simplify ArticleUserInput's method, askUpdateArticle().
This is a complete disaster, it doesn't simplify at all.

askUpdateArticle() was used to be 3 different methods for Food, Electronic and Clothes.

So I started making one, but it got more and more complicated.
To be able to cast correctly result from user inputs, I had to create an interface "UserInput" and implement it in all
the UserInput's class of this package.




