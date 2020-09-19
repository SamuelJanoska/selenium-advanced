package base;

import enumerators.AvailableBrowserType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    public WebDriver getDriver() {
        return driver;
    }
    //private static WebDriver driver;
    private WebDriver driver;
    private AvailableBrowserType availableBrowserType;

    private static WebDriverSingleton instance;

//    private static void initialize() {
    void initialize() {
//        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        driver = new ChromeDriver();
        availableBrowserType = getAvailableBrowserType();
        initializeSeleniumServer();
    }

    public static WebDriverSingleton getInstance()  {
        if(instance == null){
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    private void initializeFF() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "C:\\tmp");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
        profile.setPreference("general.useragent.override",
                "Mozilla/5.0 (Android 4.4; Mobile; rv:41.0) Gecko/41.0 Firefox/41.0");

        //nezabudnut vlozit nas objekt!!!
        driver = new FirefoxDriver();
    }

    private void initializePhantomJS() {
        System.setProperty("phantomjs.binary.path", "src/test/resources/drivers/phantomjs.exe");
        driver = new PhantomJSDriver();
    }

    //prepisana na public pri vytvarani SinCityPage, inak to nefungovalo
//    public static WebDriver getWebdriverInstance() {
//        if (driver == null) {
//            initialize();
//        }
//        return driver;
//    }

    private void initializeSeleniumServer() {

        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        driver = new RemoteWebDriver(url, firefoxOptions);
        //driver = new RemoteWebDriver(url, chromeOptions);

        driver = new RemoteWebDriver(url, getDesiredCapabilities());

    }

    private DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        switch (availableBrowserType){
            case CHROME -> {
                desiredCapabilities.setBrowserName(String.valueOf(availableBrowserType));
                desiredCapabilities = DesiredCapabilities.chrome();
                break;}
            case FIREFOX -> {
                desiredCapabilities.merge(desiredCapabilities);
                //nasledovny riadok znova pridany po konci kurzu, lebo firefox lokalne blbne a v Jenkinse byva failnuty
                desiredCapabilities.setPlatform(Platform.ANY);
                desiredCapabilities.setBrowserName(String.valueOf(availableBrowserType));
                desiredCapabilities = DesiredCapabilities.firefox();
                break;}
        }
        return desiredCapabilities;
    }

    private AvailableBrowserType getAvailableBrowserType(){
        String browserType = "firefox";
        if (System.getProperty("browserType") != null){
            browserType = System.getProperty("browserType");
        }
        AvailableBrowserType availableBrowserType;
        try{
            availableBrowserType = AvailableBrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException i) {
            throw new IllegalArgumentException("Browser "+ browserType + " not supported.");
        }
        return availableBrowserType;
    }

}