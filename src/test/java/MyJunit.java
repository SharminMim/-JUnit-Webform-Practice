import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver= new ChromeDriver();//for making headed
        driver.manage().window().maximize();//window maximize in size
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName(" Get website title")
    @Test
    public void getTitle(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");//site visit
        String titleActual=driver.getTitle();
        System.out.println(titleActual);
        String titleExpected="Practice webform for learners | Digital Unite";
        Assertions.assertTrue(titleActual.contains(titleExpected));

    }

    @Test
    public void submitForm() throws InterruptedException {
        //visit the website
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        // Accept cookies
        WebElement cookie=driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookie.click();

        //Enter-->name
        WebElement name=driver.findElement(By.id("edit-name"));
        name.sendKeys("Mim");

        //Enter-->number
        WebElement number=driver.findElement(By.id("edit-number"));
        number.sendKeys("01723654789");

        //Enter-->Today's date
        WebElement date=driver.findElement(By.id("edit-date"));
        //date.click();
        date.sendKeys("08/29/2024");

        //Enter-->email
        WebElement email=driver.findElement(By.id("edit-email"));
        email.sendKeys("mim@test.com");

        //Enter-->about yourself
        WebElement about=driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
        about.sendKeys("I'm an automation testing learner.");

        ////upload document
        WebElement upload=driver.findElement(By.id("edit-uploadocument-upload"));
        upload.sendKeys(System.getProperty("user.dir")+"/src/test/resources/cat.jpg");
        Thread.sleep(1200);

        //checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement checkbox = driver.findElement(By.id("edit-age"));
        js.executeScript("arguments[0].click();", checkbox);

        //submit
        WebElement submit = driver.findElement(By.id("edit-submit"));
        Actions actions = new Actions(driver);
        actions.click(submit).perform();

        //success message
        WebElement resultElem = driver.findElement(By.tagName("h1"));
        String actualMessage = resultElem.getText();
        String messageExpected = "Thank you for your submission!";
        Assertions.assertTrue(actualMessage.contains(messageExpected));
    }

}
