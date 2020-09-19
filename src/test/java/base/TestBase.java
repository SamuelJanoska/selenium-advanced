package base;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public static final String BASE_URL="http://localhost:8082";

    @Before
    public void setUp(){
        WebDriverSingleton.getInstance().initialize();
    }

    @After
    public void tearDown() throws InterruptedException {
//        WebDriverSingleton.getInstance().getDriver().close();
        //WebDriverSingleton.getInstance().getDriver().close();
        Thread.sleep(5000);
//        new WebDriverWait(getDriver(),10).until(ExpectedConditions.)
        WebDriverSingleton.getInstance().getDriver().quit();
    }

//    public WebDriver getDriver() {
//        return driver;
//    }

    public WebDriver getDriver() {
        return WebDriverSingleton.getInstance().getDriver();
    }

}
