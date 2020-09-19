package pages;

import org.apache.commons.io.FileUtils;
import base.WebDriverSingleton;
import models.Sin;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;

import static base.TestBase.BASE_URL;

public class SpartaPage {
    private WebDriver driver;
    private static final String PAGE_URL = "/sparta.php";

    @FindBy(xpath="//article[@class='sin']")
    private WebElement sinSection;

    public SpartaPage(){
        driver = WebDriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);

    }

    public void openPage(){
        driver.get(BASE_URL+PAGE_URL);
    }

    public void forgiveSin(Sin spiderSin) throws IOException {
        WebElement btn = sinSection.findElement(By.xpath(".//button"));
        String TitleText = sinSection.findElement(By.xpath("./header/h4")).getText();
        if (TitleText.contains(spiderSin.getTitle())){
            btn.click();
            new WebDriverWait(driver,7).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='confirm']")));
            driver.findElement(By.xpath("//button[@id='confirm']")).click();

            //!!!
            spiderSin.setForgiven(true);


        }
    }

}
