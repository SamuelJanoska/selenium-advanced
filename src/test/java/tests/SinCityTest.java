package tests;

import base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.Test;
import org.openqa.selenium.*;
import pages.SinCityPage;
import pages.SpartaPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SinCityTest extends TestBase {

    @Test
    public void testNewSin() throws IOException {

        getDriver().manage().window().setSize(new Dimension(300,900));

        SinCityPage sinCityPage = new SinCityPage();
        Sin spiderSin = new Sin("Zabil pavuka","Furby","Lopatou",false);

//        List <String> spiderSinTags = new ArrayList<String>();
//        spiderSinTags.add("murder");
//        spiderSinTags.add("robbery");
//        spiderSinTags.add("car_accident");
        List<SinType> spiderSinTags = new ArrayList<SinType>();
        spiderSinTags.add(SinType.MURDER);
        spiderSinTags.add(SinType.ROBBERY);
        spiderSinTags.add(SinType.CAR_ACCIDENT);

        spiderSin.setTags(spiderSinTags);

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(spiderSin);
        sinCityPage.markTag(spiderSin.getTags());

        sinCityPage.confessSin();

        sinCityPage.openSinDetail(spiderSin);

//        easy uloha
        sinCityPage.checkStatus(spiderSin);

        //medium uloha
        sinCityPage.openSin(spiderSin);
        sinCityPage.checkDetail(spiderSin);

        //hard uloha
        //vytvorit novu stranku po vzore SinCityPage - StartaPage
        //najst pomocou article, title, button s triedou, kliknut forgive a odpustit
        //do metody odpustit hriet vlozit konkretny objekt hriechu - asi ten nas posledny teda
        //! SKONTROLOVAT aj, ze hriech je odpusteny na SinCity

        SpartaPage spartaPage = new SpartaPage();
        spartaPage.openPage();
        spartaPage.forgiveSin(spiderSin);
        sinCityPage.openPage();
        sinCityPage.checkStatusForgiven(spiderSin);


    }

//    private void fillSinInformation(String title, String author, String message) {
//        getDriver().findElement(By.name("title")).sendKeys(title);
//        getDriver().findElement(By.name("author")).sendKeys(author);
//        getDriver().findElement(By.name("message")).sendKeys(message);
//    }




}
