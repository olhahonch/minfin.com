package com.minfin.tests.PO;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 11.07.2018.
 */
@RunWith(DataProviderRunner.class)
public class MainMenu_QuickCheck  extends TestBasePO {
    @DataProvider
    public static Object[][] mainMenuSubItems() {
        return new Object[][]{
                {"https://minfin.com.ua/currency/", "#currency>li>a"},
                {"https://minfin.com.ua/banks/", "#banks>li>a"},
                {"https://minfin.com.ua/insurance/", "#insurance>li>a"},
                {"https://minfin.com.ua/realty/", "#realty>li>a"},
                {"https://minfin.com.ua/realty/", "#telecom>li>a"},
                {"https://minfin.com.ua/business/", "#business>li>a"},
                {"https://index.minfin.com.ua/", "#index>li>a"}
        };
    }

    @Test
    @UseDataProvider("mainMenuSubItems")
    public void mainMenuGetResponsesOK(String href, String CSSSelectorSubItem) {
        app.mainMenuGetResponsesCheck(href, CSSSelectorSubItem);
    }
    @Test
    public void mainMenuMainItemsCheck() {
        app.mainMenuMainItemsCheck();
    }
}
