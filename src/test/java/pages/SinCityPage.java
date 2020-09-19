package pages;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static base.TestBase.BASE_URL;

public class SinCityPage {

    private WebDriver driver;
    private static final String PAGE_URL = "/sincity.php";

    @FindBy (name="title")
    private WebElement titleInput;

    @FindBy (name="author")
    private WebElement author;

    @FindBy (name="message")
    private WebElement message;

    @FindBy (xpath= "//button[@type='submit']")
    private WebElement confessButton;

    @FindBy (css = "div.sinsListArea")
    private WebElement sinListSection;
//    ani private, ani public WebElement sinListSection = driver.findElement(By.cssSelector("div.sinsListArea"));

    @FindBy (xpath = "//article")
    private WebElement sinDetailSection;

    public SinCityPage(){
        driver = WebDriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public void openPage(){
        driver.get(BASE_URL+PAGE_URL);
    }

    //pri budovani SinCityPage sme toto zmenili na public
    public void fillSinInformation(Sin sin) {
        titleInput.sendKeys(sin.getTitle());
        author.sendKeys(sin.getAuthor());
        message.sendKeys(sin.getMessage());
    }

    public void markTag(List<SinType> tags){
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='"+ tag.getXpathvalue() + "']")).click();
        }
    }

    public void confessSin() {
        confessButton.click();
    }

    public void openSinDetail(Sin spiderSin) {
        getSinFromListElement(spiderSin);
    }

    public void checkStatus(Sin spiderSin) {
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'" + spiderSin.getTitle() + "')]/div/p[@class='pending']")).getText().contains("pending"));
    }

    public void checkStatusForgiven(Sin spiderSin) throws IOException {
         Assert.assertTrue(spiderSin.getForgiven());
        //neni forgive Sin ale nevadi
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        FileUtils.copyFile(file, new File("C://tmp//screenshot.png"));

    }

    public void openSin(Sin spiderSin) {

        getSinFromListElement(spiderSin).click();
    }

     public void checkDetail(Sin spiderSin) {
        WebElement vrazednyToolInDetSec = sinDetailSection.findElement(By.xpath("./p"));
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.textToBePresentInElement(
                        vrazednyToolInDetSec,
                        spiderSin.getMessage()));

        WebElement titleInDetSec = sinDetailSection.findElement(By.xpath("./h4"));
         System.out.println(titleInDetSec.getText().contains(spiderSin.getTitle()));
         Assert.assertTrue(titleInDetSec.getText().contains(spiderSin.getTitle()));

        WebElement authorInDetSec = titleInDetSec;
        Assert.assertTrue(authorInDetSec.getText().contains(spiderSin.getAuthor()));


        // :(
//        WebElement tagsInDetSec = titleInDetSec;
//        for ( tag : spiderSin.getTags()) {
//            Assert.assertTrue(tag = tagsInDetSec.findElement(By.xpath("./div/ul[]")).getText());
//        }


    }

    private WebElement getSinFromListElement(Sin spiderSin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        return listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle() + "')]"));
    }
}
