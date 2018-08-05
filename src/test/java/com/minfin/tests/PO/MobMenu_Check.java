package com.minfin.tests.PO;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 12.07.2018.
 */
@RunWith(DataProviderRunner.class)

public class MobMenu_Check extends TestBasePO {

    @DataProvider
    public static String[] mobMenuBlockNames() {
        return new String[]{".js-mobile-inactive", "#mobMenu-currency>li>a", "#mobMenu-banks>li>a", "#mobMenu-insurance>li>a","#mobMenu-realty>li>a",
                "#mobMenu-telecom>li>a","#mobMenu-business>li>a","#mobMenu-index>li>a", "#mobMenu-bonus>li>a", "#mobMenu-online-pay>li>a",
                "#mobMenu-spec>li>a"};
    }
    @Test
    @UseDataProvider("mobMenuBlockNames")
    public void mobMenuCheck(String CSSSelector) {
       app.mobMenuCheck(CSSSelector);
    }
}
