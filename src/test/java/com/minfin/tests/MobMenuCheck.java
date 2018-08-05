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
public class MobMenuCheck extends TestBase{
    private String firstStateURL;
    private List<WebElement> menuItems;
    private AssertionError error = new AssertionError();

    @Before
    public void findMenuIcon(){
        driver.get("https://minfin.com.ua/");

    }
    @DataProvider
    public static String[] mobMenuBlockNames() {
        return new String[]{".js-mobile-inactive", "#mobMenu-currency>li>a", "#mobMenu-banks>li>a", "#mobMenu-insurance>li>a","#mobMenu-realty>li>a",
                "#mobMenu-telecom>li>a","#mobMenu-business>li>a","#mobMenu-index>li>a", "#mobMenu-bonus>li>a", "#mobMenu-online-pay>li>a", "#mobMenu-spec>li>a"};
    }

@Test
@UseDataProvider("mobMenuBlockNames")
    public void mobMenuCheck(String CSSSelector) {
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mfz-mobile-main-menu.js-mfz-mobile-main-menu"))));
    driver.findElement(By.cssSelector(".mfz-mobile-main-menu.js-mfz-mobile-main-menu")).click();
        menuItems = driver.findElements(By.cssSelector(CSSSelector));
        for (int i=0; i<menuItems.size(); i++) {
            firstStateURL = driver.getCurrentUrl();
            wait.until(ExpectedConditions.visibilityOf( menuItems.get(i)));
            try {
                if (sendGet(menuItems.get(i).getAttribute("href"))!=200) {
                    System.out.println("Respons to the Get request "+driver.getCurrentUrl() + " is not 200OK " +error.getMessage());
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
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mfz-mobile-main-menu.js-mfz-mobile-main-menu"))));
            driver.findElement(By.cssSelector(".mfz-mobile-main-menu.js-mfz-mobile-main-menu")).click();
            menuItems = driver.findElements(By.cssSelector(CSSSelector));
        }
    }
}
