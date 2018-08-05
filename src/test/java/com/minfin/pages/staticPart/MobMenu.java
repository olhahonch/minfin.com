package com.minfin.pages.staticPart;

import com.minfin.pages.Page;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by User on 11.07.2018.
 */
public class MobMenu extends Page {
    private String firstStateURL;
    private List<WebElement> menuItems;
    private AssertionError error = new AssertionError();
    public MobMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void check(String CSSSelector) {

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
