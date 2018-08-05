package com.minfin.pages.specPages;

import com.minfin.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.07.2018.
 */
public class BusinessPage extends Page {
    public BusinessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void open() {
        driver.get("https://minfin.com.ua/business/");
    }
}
