package com.minfin.pages.specPages;

import com.minfin.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.07.2018.
 */
public class IndexesPage extends Page {
    public IndexesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void open() {
        driver.get("https://index.minfin.com.ua/");
    }

}
