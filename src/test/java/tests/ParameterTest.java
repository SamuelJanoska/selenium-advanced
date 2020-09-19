package tests;

import base.TestBase;
import helpers.ExcelReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterTest extends TestBase {
    int number;
    boolean expectedPrime;

    @Parameterized.Parameters
    public static List<Object[]> getData(){
        return Arrays.asList( new Object[][]{{1, true}, {2, true}, {482, false}});
    }
    public ParameterTest(int number, boolean expectedPrime){
        this.number = number;
        this.expectedPrime = expectedPrime;
    }

    @Before
    public void setUp(){

    }

    @Test
    public void testOptimusUsingParamaters(){
        getDriver().get(BASE_URL + "/primenumber.php");
//        System.out.println(number);

        WebElement numberInput = getDriver().findElement(By.xpath("//input[@type='number']"));
        WebElement button = getDriver().findElement(By.xpath("//button"));

        numberInput.clear();
        numberInput.sendKeys(String.valueOf(number));
        button.click();

        checkResult(expectedPrime);
        }


        private void checkResult(boolean expectedPrime) {
            if(expectedPrime) {
                new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
            }else{
                new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
            }
        }
}
