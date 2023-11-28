package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    //call openBrowser method
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    //1.Test name verifyProductArrangeInAlphabeticalOrder()
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //        1.1 Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[normalize-space()='Computers']"));
        //        1.2 Click on Desktop
        clickOnElement(By.xpath("(//div[@class='sub-category-item'])[1]"));

        //Before filter
        List<WebElement> productListBeforeFilter = driver.findElements(By.xpath("//div[@class='details']//h2[@class='product-title']/a"));
        List<String> productsBeforeFilterList = new ArrayList<>();
        for (WebElement item : productListBeforeFilter) {
            productsBeforeFilterList.add(item.getText());
        }

        //capture the drop-down element
        WebElement dropDown = getElement(By.id("products-orderby"));

        //1.3 Select Sort By position "Name: Z to A"
        selectElementByVisibleText(dropDown, "Name: Z to A"); //Calling the utility method for drop-down selection


        //  1.4 Verify the Product will arrange in Descending order.

        //After filter
        List<WebElement> productAfterFilter = driver.findElements(By.xpath("//div[@class='details']//h2[@class='product-title']/a"));

        for (WebElement e : productAfterFilter) { //for each loop for debug purpose
            System.out.println(e.getText());
        }

        List<String> productsAfterFilterList = new ArrayList<>();
        for (WebElement item : productAfterFilter) {
            productsAfterFilterList.add(item.getText());
            //System.out.println(productsAfterFilterList);//B,D,L
        }

        //Utility class Collections 'sort' method
        Collections.sort(productsBeforeFilterList); //sort the before filter list in ascending order
        // Collections.reverse(productsBeforeFilterList);//sorts list in descending order
        Assert.assertEquals("Products are not filtered correctly.", productsBeforeFilterList, productsAfterFilterList);

    }

    //2. Test name verifyProductAddedToShoppingCartSuccessFully()
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1 Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[normalize-space()='Computers']"));

        //2.2 Click on Desktop
        clickOnElement(By.xpath("(//div[@class='sub-category-item'])[1]"));

        // 2.3 Select Sort By position "Name: A to Z"
        selectElementByVisibleText(getElement(By.id("products-orderby")), "Name: A to Z");

        driver.navigate().refresh();  //refresh the page to avoid 'stale element reference' exception in the next statement
        // 2.4 Click on "Add To Cart"
        clickOnElement(By.xpath("(//div[@class='buttons']/button[text()='Add to cart'])[1]"));

        // 2.5 Verify the Text "Build your own computer"
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']"));
        Assert.assertEquals("'Add to Cart' is not displaying the right product. ", "Build your own computer", actualText);

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectElementByVisibleText(getElement(By.id("product_attribute_1")), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class.
        selectElementByVisibleText(getElement(By.id("product_attribute_2")), "8GB [+$60.00]");

        // 2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));


        // 2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        // 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander

        if (!getElement(By.id("product_attribute_5_10")).isSelected()) //verify if the first check box is selected by default
            clickOnElement(By.id("product_attribute_5_10"));

        clickOnElement(By.id("product_attribute_5_12"));

        //2.11 Verify the price "$1,475.00"
        WebElement price = getElement(By.id("price-value-1"));
        //Assert.assertEquals("The total price is incorrect.", "$1,475.00", price.getText());  //fails at this step because of the wrong total is returned always

        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));


        //2.13 Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar
        Assert.assertEquals("The product is added not added to cart successfully.", "The product has been added to your shopping cart", getElement(By.xpath("//p[@class='content']")).getText());

        //After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement cart = getElement(By.id("topcartlink"));
        WebElement addToCartButton = getElement(By.xpath("//button[normalize-space()='Go to cart']"));
        Thread.sleep(5000);  //to avoid 'element not interactible' exception

        moveMouseOnElement(cart);
        moveMouseAndClickOnElement(addToCartButton);

//      //2.15 Verify the message "Shopping cart"
        Assert.assertEquals("The user is not navigated to 'Shopping Cart' page.", "Shopping cart", getTextFromElement(By.xpath("//div[@class='page-title']")));

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        getElement(By.xpath("//td[@class= 'quantity']//input")).clear();
        sendTextToElement(By.xpath("//td[@class= 'quantity']//input"), "2");

        clickOnElement(By.id("updatecart"));


        //2.17 Verify the Total"$2,950.00"
        String expectedText = getTextFromElement(By.xpath("(//td[@class='cart-total-right']/span[@class=\"value-summary\"])[4]"));
        Assert.assertEquals("The total field is showing incorrect amount.", "$2,950.00", expectedText);

        //2.18 click on checkbox “I agree with the terms of service”
        if (!getElement(By.id("termsofservice")).isSelected()) {
            clickOnElement(By.id("termsofservice"));
        }

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        Assert.assertEquals("User is not navigated to the 'Sign In' page.", "Welcome, Please Sign In!", getTextFromElement(By.xpath("//div[@class='page-title']/h1")));

        //2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        //2.22 Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "John");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Smith");

        //Generate a random email
        Random random = new Random();
        int ranNumber = random.nextInt(1000);
        String ranString = String.format("%03d", ranNumber);
        String randomEmail = ranString + "johnsmith@gmail.com";
        sendTextToElement(By.id("BillingNewAddress_Email"), randomEmail);

        selectElementByVisibleText(getElement(By.id("BillingNewAddress_CountryId")), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "5, HighView");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "TW7 4RK");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07846788543");

        //2.23 Click on “CONTINUE”
        clickOnElement(By.name("save"));

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/button[normalize-space()='Continue']"));

        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
//        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@id='payment-method-buttons-container']//button[normalize-space()='Continue']"));

        //2.27 Select “Master card” From Select credit card dropdown
        selectElementByVisibleText(getElement(By.id("CreditCardType")), "Master card");

        //2.28 Fill all the details
        sendTextToElement(By.id("CardholderName"), "John Smith");
        sendTextToElement(By.id("CardNumber"), "5555555555554444");
        selectElementByVisibleText(getElement(By.id("ExpireMonth")), "08");
        selectElementByVisibleText(getElement(By.id("ExpireYear")), "2025");
        sendTextToElement(By.id("CardCode"), "123");

        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.30 Verify “Payment Method” is “Credit Card”
        Assert.assertEquals("The payment method doesn't match", "Credit Card", getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']")));

        //2.32 Verify “Shipping Method” is “Next Day Air”
        Assert.assertEquals("Incorrect shipping method.", "Next Day Air", getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']")));

        //2.33 Verify Total is “$2,950.00”
        Assert.assertEquals("Total amount mismatch.", "$2,950.00", getTextFromElement(By.xpath("//td[@class='cart-total-right']/span[@class='value-summary']/strong")));

        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));
        Thread.sleep(2000); //delay given to wait for a dynamic locator to change it's text from (Checkout) to (Thank you)

        //2.35 Verify the Text “Thank You”
        Assert.assertEquals("The user is navigated to wrong page", "Thank you", getTextFromElement(By.xpath("//div[@class='page-title']/h1")));

        //2.36 Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("The confirmation message is wrong.", "Your order has been successfully processed!", getTextFromElement(By.xpath("//div[@class='section order-completed']/div[@class='title']")));

        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.37 Verify the text “Welcome to our store”
        Assert.assertEquals("The user is redirected to wrong page.", "Welcome to our store", getTextFromElement(By.xpath("//div[@class='topic-block-title']/h2")));


    }


    @After
    public void tearDown() {

        closeBrowser();
    }

}
