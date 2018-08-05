package com.minfin.pages.main;

import com.minfin.pages.Page;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

/**
 * Created by User on 11.07.2018.
 */
public class ProductsMiddleSite extends MainPage {
    private String mainWindow;
    private String newWindow;
    private String href;
    private int getResp;
    private AssertionError error;
    public ProductsMiddleSite(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnCategories(){

        List<WebElement> categories = driver.findElements(By.cssSelector(".wgt-view--body>a:not([style*=display])"));

        for (int i=0; i<categories.size(); i++) {
            href = categories.get(i).getAttribute("href");
            try {
                getResp= sendGet(href);
                if (getResp!=200) {
                    System.out.println(href+ " Response to the GET request is not 200OK" +error.getMessage());
                    Assert.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mainWindow = driver.getWindowHandle();
            categories.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();
            for (String w: allWindows) {
                if (!w.equals(mainWindow)) {
                    newWindow = w;
                }
            }
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
            categories = driver.findElements(By.cssSelector(".wgt-view--body>a:not([style*=display])"));
        }
    }
}
