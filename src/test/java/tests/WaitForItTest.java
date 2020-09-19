package tests;

import base.TestBase;
import categories.SmokeTest;
import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//@RunWith(ConcurrentTestRunner.class)
//ak chcem oznacit vsetky testy v triede ako Smoke
//@Category(SmokeTest.class)
public class WaitForItTest extends TestBase {

    @Rule public ExpectedException expectedException = ExpectedException.none();
    @Rule public RepeatingRule repeatedly = new RepeatingRule();
    @Rule public ConcurrentRule concurrently = new ConcurrentRule();

    @Test
    @Category(SmokeTest.class)
//    @Concurrent(count = 3)
    public void checkTitle() {
        //Assert.assertEquals("WAIT FOR IT !", getDriver().findElement(By.xpath("//h1")).getText());
        getDriver().get(BASE_URL + "/waitforit.php");
        Assert.assertEquals("WAIT FOR IT !", getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Test
    //@Ignore
    //@Concurrent(count = 2)
//    @Repeating(repetition = 3)
    public void checkLegendary(){
        //expectedException.expect(ComparisonFailure.class);
        getDriver().get(BASE_URL + "/waitforit.php");
        getDriver().findElement(By.id("startWaitForText")).click();
        //Assert.assertEquals("dary !", getDriver().findElement(By.id("waitForTextInput")).getAttribute("value"));
        Assert.assertEquals("", getDriver().findElement(By.id("waitForTextInput")).getAttribute("value"));
    }
}
