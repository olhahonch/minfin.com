package com.minfin.pages.main;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Created by User on 11.07.2018.
 */
public class MainSearch extends MainPage {
 //   private AssertionError error;
    public MainSearch(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void searchSmthWithClick(String data)  {

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("form.mfz-search-wrap input[name=q]"))));
        driver.findElement(By.cssSelector("form.mfz-search-wrap input[name=q]")).sendKeys(data);
        WebElement button = driver.findElement(By.cssSelector("form.mfz-search-wrap button"));
        button.click();
        WebElement search = driver.findElement(By.cssSelector("#gsc-i-id1 "));
        if(!search.getAttribute("value").equals(data)) {
            System.out.println("failed with click()");
            Assert.fail();
        }
    }
    public void searchSmthWithEnter(String data)  {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form.mfz-search-wrap input[name=q]")));
        WebElement input = driver.findElement(By.cssSelector("form.mfz-search-wrap input[name=q]"));
        input.sendKeys(data);
        input.sendKeys(Keys.ENTER);
        WebElement search = driver.findElement(By.cssSelector("#gsc-i-id1 "));
        if(!search.getAttribute("value").equals(data))
        {System.out.println("failed with Enter");
        Assert.fail();
        }
    }
}
