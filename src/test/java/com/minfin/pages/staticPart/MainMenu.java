package com.minfin.pages.staticPart;

import com.minfin.pages.Page;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by User on 11.07.2018.
 */

public class MainMenu extends Page {
//    @DataProvider
//    public static Object[][] mainMenuSubItems() {
//        return new Object[][]{
//                {"https://minfin.com.ua/currency/", "#currency>li>a"},
//                {"https://minfin.com.ua/banks/", "#banks>li>a"},
//                {"https://minfin.com.ua/insurance/", "#insurance>li>a"},
//                {"https://minfin.com.ua/realty/", "#realty>li>a"},
//                {"https://minfin.com.ua/realty/", "#telecom>li>a"},
//                {"https://minfin.com.ua/business/", "#business>li>a"},
//                {"https://index.minfin.com.ua/", "#index>li>a"}
//        };
//    }
    private List<WebElement> menuItems;
    private List<WebElement> subItems;
    private AssertionError error = new AssertionError();
    private Actions moveTo;
    private String firstStateURL;
    private int getResp;

    public MainMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void findMainMenuItems() {
        menuItems = driver.findElements(By.cssSelector("#mainMenu>li"));
    }
    public void subtemsGETResponsesCheck() {
        moveTo= new Actions(driver);
        findMainMenuItems();
        WebElement item;
        for (int i=0; i<menuItems.size(); i++) {
            System.out.println("item = menuItems.get("+i+")");
            item = menuItems.get(i);
            moveTo.moveToElement(item).build().perform();
            subItems = item.findElements(By.cssSelector("ul>li>a"));
            System.out.println(subItems.size());

            for (int j=0; j<subItems.size(); j++){
                System.out.println("moveTo.moveToElement(subItems.get(j)).build().perform();");
                moveTo.moveToElement(subItems.get(j)).build().perform();
                try {
                    getResp= sendGet(subItems.get(j).getAttribute("href"));
                    if (getResp!=200) {
                        System.out.println(subItems.get(j).getAttribute("href")+ " Response to the GET request is not 200OK" +error.getMessage());
                        Assert.fail();
                    } else { System.out.println(subItems.get(j).getAttribute("href")+ " Response to the GET request is 200OK");}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            findMainMenuItems();
       }
    }
    public void mainMenuMainItemsCheck() {
        findMainMenuItems();

        for (int i=0; i<menuItems.size(); i++) {
            firstStateURL = driver.getCurrentUrl();
            wait.until(ExpectedConditions.visibilityOf( menuItems.get(i)));

            String href = menuItems.get(i).findElement(By.cssSelector("a")).getAttribute("href");
            try {
                getResp= sendGet(href);
                if (getResp!=200) {
                    System.out.println(href+ " Response to the GET request is not 200OK" +error.getMessage());
                    Assert.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            menuItems.get(i).click();

            if (driver.getCurrentUrl().equals(firstStateURL)) {
                System.out.println("Page does not change" +error.getMessage());
                Assert.fail();
            }
            System.out.println("Title of the page " + driver.getCurrentUrl() + " is: "
                    + driver.getTitle().toString());
            driver.navigate().back();
            findMainMenuItems();
        }
    }
}
