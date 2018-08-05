package com.minfin.tests.PO;

import com.minfin.pages.Page;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 16.07.2018.
 */
@RunWith(DataProviderRunner.class)
public class MainSearchButtonCheck extends TestBasePO {
    @DataProvider
    public static String[] dataForSearch() {
        return new String[]{ "bank980987987G",
        "&" };
    }

    @Test
    @UseDataProvider("dataForSearch")
    public void testMainSearchClick(String data) {
    app.mainSearchWithClick(data);
    }

//    @Test
//    @UseDataProvider("dataForSearch")
//    public void testMainSearchEnter(String data) {
//        Assert.assertTrue(app.mainSearchWithClick(data));
//    }
}
