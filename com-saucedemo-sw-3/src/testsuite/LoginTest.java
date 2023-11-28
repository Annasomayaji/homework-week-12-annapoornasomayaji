package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "https://www.saucedemo.com/";

    //call open browser method from BaseTest
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        //* Enter “standard_user” username
        sendTextToElement(By.id("user-name"), "standard_user");

        // * Enter “secret_sauce” password
        sendTextToElement(By.id("password"), "secret_sauce");

        //* Click on ‘LOGIN’ button
        clickOnElement(By.id("login-button"));

//        Alert alert=driver.switchTo().alert();
//        alert.accept();


        //* Verify the text “PRODUCTS”
        validateResponse("THe user is navigated to incorrect page.", "Products", getTextFromElement(By.xpath("//span[normalize-space()='Products']")));
    }

    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {
        //* Enter “standard_user” username
        sendTextToElement(By.id("user-name"), "standard_user");

        // * Enter “secret_sauce” password
        sendTextToElement(By.id("password"), "secret_sauce");

        //* Click on ‘LOGIN’ button
        clickOnElement(By.id("login-button"));

        //* Verify that six products are displayed on page
        Assert.assertEquals(6, (getElements(By.xpath("//div[@class='inventory_item']")).size()));

    }


    //call close browser method from BaseTest
    @After
    public void tearDown() {
        closeBrowser();
    }

}
