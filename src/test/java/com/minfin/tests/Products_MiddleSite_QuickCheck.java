package com.minfin.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

/**
 * Created by User on 11.07.2018.
 */
public class Products_MiddleSite_QuickCheck extends TestBase {
    private String mainWindow;
    private String newWindow;
    @Test
    public void clickOnCategories(){

        driver.get("https://minfin.com.ua/");

        List<WebElement> categories = driver.findElements(By.cssSelector(".wgt-view--body>a:not([style*=display])"));

        for (int i=0; i<categories.size(); i++) {
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
