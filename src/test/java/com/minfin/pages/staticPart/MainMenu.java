package com.minfin.pages.staticPart;

import com.minfin.pages.Page;
import org.junit.Assert;
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

    public void subtemsGETResponsesCheck(String href, String CSSSelectorSubItem) {
        moveTo= new Actions(driver);
        menuItems = driver.findElements(By.cssSelector("#mainMenu>li>a"));
        WebElement item = driver.findElement(By.cssSelector("#mainMenu>li>a"));;
        for (WebElement element: menuItems) {
            if (element.getAttribute("href").equals(href)) {
                item = element;
            }
        }
        moveTo.moveToElement(item).build().perform();
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

    public void mainMenuMainItemsCheck() {
        menuItems = driver.findElements(By.cssSelector("#mainMenu>li>a"));
        for (int i=0; i<menuItems.size(); i++) {
            firstStateURL = driver.getCurrentUrl();
            wait.until(ExpectedConditions.visibilityOf( menuItems.get(i)));

            String href = menuItems.get(i).getAttribute("href");
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
            menuItems = driver.findElements(By.cssSelector("#mainMenu>li>a"));
        }
    }
}
