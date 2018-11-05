package com.minfin.pages.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.07.2018.
 */
public class PostsAndNews extends MainPage {
    public PostsAndNews(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
