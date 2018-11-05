package com.minfin.app;

import com.minfin.pages.main.*;
import com.minfin.pages.specPages.*;
import com.minfin.pages.staticPart.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by User on 11.07.2018.
 */
public class Application {
  // private ChromeOptions opt;
    private WebDriver driver;

    private MainPage mainPage;
    private CurrencyWidget currencyWidget;
    private MainSearch mainSearch;
    private PostsAndNews postsAndNews;
    private ProductsMiddleSite productsMiddleSite;

    private  AllArticlesPage allArticlesPage;
    private AllNewsPage allNewsPage;
    private BanksPage banksPage;
    private BusinessPage businessPage;
    private CurrencyPage currencyPage;
    private IndexesPage indexesPage;
    private InsurancePage insurancePage;
    private RealtyPage realtyPage;
    private TelecomPage telecomPage;

    private Footer footer;
    private Header header;
    private MainMenu mainMenu;
    private MobMenu mobMenu;

    public Application() {
//        opt = new ChromeOptions();
//        opt.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver();

        mainPage = new MainPage(driver);
        currencyWidget = new CurrencyWidget(driver);
        mainSearch = new MainSearch(driver);
        postsAndNews = new PostsAndNews(driver);
        productsMiddleSite = new ProductsMiddleSite(driver);

        allArticlesPage = new AllArticlesPage(driver);
        allNewsPage = new AllNewsPage(driver);
        banksPage = new BanksPage(driver);
        businessPage = new BusinessPage(driver);
        currencyPage = new CurrencyPage(driver);
        indexesPage = new IndexesPage(driver);
        insurancePage = new InsurancePage(driver);
        realtyPage = new RealtyPage(driver);
        telecomPage = new TelecomPage(driver);

        footer = new Footer(driver);
        header = new Header(driver);
        mainMenu = new MainMenu(driver);
        mobMenu = new MobMenu(driver);

    }
public void mainMenuGetResponsesCheck() {
        mainPage.open();
        mainMenu.subtemsGETResponsesCheck();
}

    public void mainMenuMainItemsCheck() {
        mainPage.open();
        mainMenu.mainMenuMainItemsCheck();
    }

    public void ProductsMiddleSiteBlockCheck() {
        mainPage.open();
        productsMiddleSite.clickOnCategories();
    }
    public void mobMenuCheck(String CSSSelector) {
        mainPage.open();
        mobMenu.check(CSSSelector);
    }
    public void mainSearchWithClick(String data) {
        mainPage.open();
        mainSearch.searchSmthWithClick(data);
    }
    public void mainSearchWithEnter(String data) {
    mainPage.open();
    mainSearch.searchSmthWithEnter(data);
    }
    public void quit() {
        driver.quit();
    }


}
