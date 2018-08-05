package com.minfin.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by User on 10.07.2018.
 */
@RunWith(DataProviderRunner.class)
public class MainMenuCheck extends TestBase{
    private String firstStateURL;
    private List<WebElement> menuItems;
    private List<WebElement> subItems;
    private AssertionError error = new AssertionError();
    private Actions moveTo;
    @Before
    public void findMainMenu() {
        driver.get("https://minfin.com.ua/");
    }
    @DataProvider
    public static String[] mainMenuMainItems() {
        return new String[]{"#mainMenu>li>a"};
    }
    @DataProvider
    public static Object[][] mainMenuSubItems() {
        return new Object[][]{
                {"https://minfin.com.ua/currency/","#currency>li>a"},
                {"https://minfin.com.ua/banks/","#banks>li>a"},
                {"https://minfin.com.ua/insurance/", "#insurance>li>a"},
                {"https://minfin.com.ua/realty/","#realty>li>a"},
                {"https://minfin.com.ua/realty/","#telecom>li>a"} ,
                {"https://minfin.com.ua/business/","#business>li>a"},
                {"https://index.minfin.com.ua/","#index>li>a"}
        };
    }

    @Test
    @UseDataProvider("mainMenuSubItems")
    public void mainMenuSubtemsCheck(String href, String CSSSelectorSubItem) {
        int getResp;
        moveTo= new Actions(driver);
        menuItems = driver.findElements(By.cssSelector("#mainMenu>li>a"));
        WebElement item = driver.findElement(By.cssSelector("#mainMenu>li>a"));;
        for (WebElement element: menuItems) {
            if (element.getAttribute("href").equals(href)) {
                item = element;
            }
        }
        moveTo.moveToElement(item).perform();
        subItems = driver.findElements(By.cssSelector(CSSSelectorSubItem));

            for (int j=0; j<subItems.size(); j++){
                moveTo.moveToElement(subItems.get(j)).build().perform();
                try {
                    getResp= sendGet(subItems.get(j).getAttribute("href"));
                    if (getResp!=200) {
                        System.out.println(subItems.get(j).getAttribute("href")+ " Response to the GET request is not 200OK" +error.getMessage());
                        Assert.fail();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    @Test
    @UseDataProvider("mainMenuMainItems")
    public void mainMenuMainItemsCheck(String CSSSelector) {
        menuItems = driver.findElements(By.cssSelector(CSSSelector));
        for (int i=0; i<menuItems.size(); i++) {
            firstStateURL = driver.getCurrentUrl();
            wait.until(ExpectedConditions.visibilityOf( menuItems.get(i)));
            menuItems.get(i).click();

            if (driver.getCurrentUrl().equals(firstStateURL)) {
                System.out.println("Page does not change" +error.getMessage());
                Assert.fail();
            }
            System.out.println("Title of the page " + driver.getCurrentUrl() + " is: "
                    + driver.getTitle().toString());
            driver.navigate().back();
            menuItems = driver.findElements(By.cssSelector(CSSSelector));
        }
    }
}
