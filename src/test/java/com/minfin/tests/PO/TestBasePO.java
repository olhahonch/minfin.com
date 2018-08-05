package com.minfin.tests.PO;

import com.minfin.app.Application;
import org.junit.Before;

/**
 * Created by User on 11.07.2018.
 */
public class TestBasePO {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}
