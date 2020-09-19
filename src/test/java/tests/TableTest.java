package tests;

import base.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TableTest extends TestBase {

    @Test
    public void testVendelinIsPresent() {
        getDriver().get(BASE_URL + "/tabulka.php");
        List<String> vendelinovia = new ArrayList<>();
        List<WebElement> rows = getDriver().findElements(By.xpath("//table/tbody/tr"));

        vendelinovia = rows.stream()
                .filter(element -> getName(element).equals("Vendelin"))
                .map(element -> getSurname(element))
                //.forEach(element -> System.out.println(getSurname(element)));
                .collect(Collectors.toList());

        System.out.println(vendelinovia);

//        for (WebElement row : rows) {
//            if ("Vendelin".equals(getName(row))){
//                //System.out.println(getSurname(row));
//                vendelinovia.add(getSurname(row));
//            }
//        }
    }

    private String getName(WebElement element){
        return element.findElement(By.xpath("./td[2]")).getText();
    }

    private String getSurname(WebElement element){
        return element.findElement(By.xpath("./td[3]")).getText();
    }

}
