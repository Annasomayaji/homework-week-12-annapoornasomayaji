package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        //* Mouse Hover on Gear Menu
        WebElement gear=getElement(By.id("ui-id-6"));
        moveMouseToElement(gear);

        // * Click on Bags
        moveMouseAndClickOnElement(getElement(By.id("ui-id-25")));

        //* Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//strong[@class='product name product-item-name'][normalize-space()='Overnight Duffle']"));

        //* Verify the text ‘Overnight Duffle’
        validateResponse("The product name is incorrect.","Overnight Duffle",getTextFromElement(By.xpath("//h1[@class='page-title']")));

        //* Change Qty 3
        getElement(By.id("qty")).clear();
        sendTextToElement(By.id("qty"),"3");

        // * Click on ‘Add to Cart’ Button.
        clickOnElement(By.id("product-addtocart-button"));

        // * Verify the text ‘You added Overnight Duffle to your shopping cart.’
        validateResponse("The success message 'You added Overnight Duffle to your shopping cart' is not displayed.","You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@class='message-success success message']")));

        //* Click on ‘shopping cart’ Link into message
        clickOnElement(By.linkText("shopping cart"));

        //* Verify the product name ‘Overnight Duffle’
        validateResponse("The product name is incorrect.","Overnight Duffle", getTextFromElement(By.linkText("Overnight Duffle")));

        //* Verify the Qty is ‘3’
        validateResponse("Incorrect quantity in the Shopping Cart.","3", getElement(By.xpath("//span[@class='label'][text()='Qty']/following-sibling::input")).getAttribute("value"));

        //* Verify the product price ‘$135.00’
        validateResponse("Incorrect total price.", "$135.00", getTextFromElement(By.xpath("(//span[@class='cart-price']/span[@class='price'])[2]")));

        //* Change Qty to ‘5’
        getElement(By.xpath("//span[@class='label'][text()='Qty']/following-sibling::input")).clear();
        sendTextToElement(By.xpath("//span[@class='label'][text()='Qty']/following-sibling::input"),"5");

        //* Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//button[normalize-space()='Update Shopping Cart']"));

        Thread.sleep(2000);
        //* Verify the product price ‘$225.00'
        validateResponse("Incorrect total price.", "$225.00", getTextFromElement(By.xpath("(//span[@class='cart-price']/span[@class='price'])[2]")));



    }


    @After
    public void tearDown() {
       // closeBrowser();
    }

}
