package com.minfin.tests.PO;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 11.07.2018.
 */
public class MainMenu_QuickCheck  extends TestBasePO {

    @Test
    public void mainMenuGetResponsesOK() {
        app.mainMenuGetResponsesCheck();
    }
    @Test
    public void mainMenuMainItemsCheck() {
        app.mainMenuMainItemsCheck();
    }
}
