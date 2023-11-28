package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.Utility;

public class MenTest extends Utility {
    String baseUrl="https://magento.softwaretestingboard.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart(){

        //Mouse Hover on Men Menu
        WebElement men=getElement(By.id("ui-id-5"));
//        Actions action=new Actions(driver);  //for practice
//        action.moveToElement(men).build().perform();
        moveMouseToElement(men);

        //* Mouse Hover on Bottoms
        WebElement bottoms= getElement(By.id("ui-id-18"));
        moveMouseToElement(bottoms);

        //* Click on Pants
        WebElement pants= getElement(By.id("ui-id-23"));
        moveMouseAndClickOnElement(pants);

        //* Mouse Hover on product name ‘Cronus Yoga Pant’ and click on size 32.
        WebElement cronusYogaPants = getElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        moveMouseToElement(cronusYogaPants);
        moveMouseAndClickOnElement(getElement(By.id("option-label-size-143-item-175")));

        //* Mouse Hover on product name‘Cronus Yoga Pant’ and click on colour Black.
        moveMouseToElement(cronusYogaPants);
        moveMouseAndClickOnElement(getElement(By.id("option-label-color-93-item-49")));

        //* Mouse Hover on product name ‘Cronus Yoga Pant’ and click on ‘Add To Cart’ Button.
        moveMouseToElement(cronusYogaPants);
        driver.findElement(RelativeLocator.with(By.xpath("//button[@title='Add to Cart']")).below(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"))).click();

        //* Verify the text ‘You added Cronus Yoga Pant to your shopping cart.’
        String actualText=getTextFromElement(By.xpath("//div[@class='message-success success message']"));
        validateResponse("The add to cart is not successful.","You added Cronus Yoga Pant to your shopping cart.",actualText);

        //* Click on ‘shopping cart’ Link into message
        clickOnElement(By.linkText("shopping cart"));

        //* Verify the text ‘Shopping Cart.’
        validateResponse("The user is not navigated to shopping cart.","Shopping Cart",getTextFromElement(By.xpath("//div[@class='page-title-wrapper']/h1")));

        //* Verify the product name ‘Cronus Yoga Pant’
        validateResponse("The product detail is wrong","Cronus Yoga Pant",getTextFromElement(By.linkText("Cronus Yoga Pant")));

        //* Verify the product size ‘32’
        validateResponse("The product size is incorrect.","32",getTextFromElement(By.xpath("//dl[@class='item-options']//dd[1]")));

        //* Verify the product colour ‘Black’
        validateResponse("The product colour is incorrect.","Black",getTextFromElement(By.xpath("//dl[@class='item-options']//dd[2]")));


    }


    @After
    public void tearDown(){
       closeBrowser();
    }
}
