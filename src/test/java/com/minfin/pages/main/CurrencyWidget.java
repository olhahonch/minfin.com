package com.minfin.pages.main;

import com.minfin.pages.Page;
import com.minfin.pages.staticPart.MainMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.07.2018.
 */
public class CurrencyWidget extends MainPage {
    public CurrencyWidget(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
