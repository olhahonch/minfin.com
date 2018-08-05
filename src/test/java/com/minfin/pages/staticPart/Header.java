package com.minfin.pages.staticPart;

import com.minfin.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.07.2018.
 */
public class Header extends Page {
    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
