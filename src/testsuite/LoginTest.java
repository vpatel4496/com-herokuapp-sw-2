package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldLoginSuccessfully() {
        // Input valid Username
        WebElement usernamefield = driver.findElement(By.id("username"));
        usernamefield.sendKeys("tomsmith");

        // Input valid Password
        WebElement passwordfield = driver.findElement(By.name("password"));
        passwordfield.sendKeys("SuperSecretPassword!");

        // Click on Login Button
        WebElement loginfield = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginfield.click();

        // Verify the text
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/h2[1]")).getText();

        // Validate actual and expected text
        Assert.assertEquals("Secure Area", actualText,expectedText);
    }

    @Test
    public void verifyUserErrorMessage() {
        // Input Invalid Username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith1");

        // Input Valid Password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // Click on Login Button
        WebElement login = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        login.click();

        // Verify the message
        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String actual1Msg=actualMessage.substring(0,25);



        // Validate actual and expected message
        Assert.assertEquals("Your username is invalid!",expectedMessage, actual1Msg);


    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // Input Invalid Username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // Input Valid Password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword");

        // Click on Login Button
        WebElement login = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        login.click();

        // Verify the message
        String expectedMessage = "Your password is invalid!";
        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String actual1Msg=actualMessage.substring(0,25);
        // Validate actual and expected message
        Assert.assertEquals("Your Password is invalid",expectedMessage, actual1Msg);

    }
    @After
    public void tearDown() {
        closeBrowser();
}

}