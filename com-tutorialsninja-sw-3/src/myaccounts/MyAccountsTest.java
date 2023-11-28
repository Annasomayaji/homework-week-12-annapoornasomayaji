package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilitites.Utility;

import java.util.List;
import java.util.Random;

public class MyAccountsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    // 1.1 create method with name "selectMyAccountOptions" it has one parameter name
    //"option" of type string
    //1.2 This method should click on the options whatever name is passed as parameter.
    //  (Hint: Handle List of Element and Select options)
    public void selectMyAccountOptions(String option) {
        clickOnElement(By.xpath("(//a[@href='https://tutorialsninja.com/demo/index.php?route=account/" + option + "'])[1]"));
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        // 1.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        // 1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        List<WebElement> optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Register")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }
        // 1.3 Verify the text “Register Account”
        Assert.assertEquals("User is navigated to wrong page.", "Register Account", getTextFromElement(By.xpath("//div[@id='content']/h1")));

    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        // 2.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”“Login”
        List<WebElement> optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Login")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }
        // 2.3 Verify the text ““Returning Customer”
        Assert.assertEquals("User is navigated to wrong page.", "New Customer", getTextFromElement(By.xpath("//div[@class='well']/h2[text()='New Customer']")));

    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        // 3.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        // 3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        List<WebElement> optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Register")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }


        //3.11 Click on Continue button
        sendTextToElement(By.id("input-firstname"), "John");
        //3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Smith");
        //3.5 Enter Email
        Random random = new Random();
        int num = random.nextInt(1000);
        String ranString = String.format("%03d", num);
        String randomEmail = ranString + "johnsmith@gmail.com";

        sendTextToElement(By.id("input-email"), randomEmail);
        //3.6 Enter Telephone
        sendTextToElement(By.id("input-telephone"), "07712345689");
        //3.7 Enter Password
        sendTextToElement(By.id("input-password"), "Password1");
        //3.8 Enter Password Confirm
        sendTextToElement(By.id("input-confirm"), "Password1");
        //3.9 Select Subscribe Yes radio button
        List<WebElement> radioButton = getElements(By.xpath("//input[@name='newsletter']"));
        for (WebElement r : radioButton) {
            if (r.getText().equalsIgnoreCase("Yes")) {
                r.click();
                break;
            }
        }
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //3.3 Enter First Name
        clickOnElement(By.xpath("//input[@value='Continue']"));


        //3.12 Verify the message “Your Account Has Been Created!”
        String expectedText = "Your Account Has Been Created!";
        String actualText = getTextFromElement(By.xpath("//div[@id='content']/h1"));
        Assert.assertEquals("The account creation in not successful.", expectedText, actualText);

        //3.13 Click on Continue button
        clickOnElement(By.linkText("Continue"));

        //3.14 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Logout")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }

        //3.16 Verify the text “Account Logout”
        //Method from utility class
        validateResponse("The user is not directed to logout page", "Account Logout", getTextFromElement(By.xpath("//div[@id='content']/h1")));

        //3.17 Click on Continue button
        clickOnElement(By.linkText("Continue"));

    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        List<WebElement> optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Login")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }

        //4.3 Enter Email address
        sendTextToElement(By.id("input-email"), "annusom@gmail.com");

        //4.4 Enter Last Name

        //4.5 Enter Password
        sendTextToElement(By.id("input-password"), "Password1");

        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        //4.7 Verify text “My Account”
        validateResponse("User navigated to wrong page", "My Account", getTextFromElement(By.xpath("//div[@id='content']/h2[text()='My Account']")));

        // 4.8 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement e : optionsList) {
            if (e.getText().equalsIgnoreCase("Logout")) {
                selectMyAccountOptions(e.getText().toLowerCase());
                break;
            }
        }

        //4.10 Verify the text “Account Logout”
        validateResponse("The user is not directed to logout page", "Account Logout", getTextFromElement(By.xpath("//div[@id='content']/h1")));

        //4.11 Click on Continue button
        clickOnElement(By.linkText("Continue"));


    }

    @After
    public void tearDown() {
         closeBrowser();
    }

}
