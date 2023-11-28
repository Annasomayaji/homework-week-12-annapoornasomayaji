package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilitites.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        WebElement desktops = getElement(By.linkText("Desktops"));
        moveMouseAndClickOnElement(desktops);

        //1.2 Click on “Show All Desktops”
        selectMenu(getTextFromElement(By.xpath("//a[@class='see-all'][text()='Show AllDesktops']")));

        //Store all products in a list and sort them in descending order using Collections Before filter
        List<WebElement> productsBeforeFilter = getElements(By.xpath("//div[@class='row']//div[@class='product-thumb']")); //Utility method

        ArrayList<String> productsListBeforeFilter = new ArrayList<>();
        for (WebElement product : productsBeforeFilter) {
            productsListBeforeFilter.add(product.getText());
        }

        //Sort the list. Use CASE_INSENSITIVE_ORDER to sort products starting with lower_case (eg. iPhone)
        Collections.sort(productsListBeforeFilter, String.CASE_INSENSITIVE_ORDER);//sort alphabetically
        Collections.reverse(productsListBeforeFilter);//sort reverse alphabetically


        //1.3 Select Sort By position "Name: Z to A"
        WebElement sortPosition = getElement(By.id("input-sort"));
        selectElementByValue(sortPosition, "https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");

        //1.4 Verify the Product will arrange in Descending order.
        List<WebElement> productsAfterFilter = getElements(By.xpath("//div[@class='row']//div[@class='product-thumb']"));
        List<String> productsListAfterFilter = new ArrayList<>();
        for (WebElement product : productsAfterFilter) {
            productsListAfterFilter.add(product.getText());
        }

        Assert.assertEquals("Products are not filtered correctly.", productsListBeforeFilter, productsListAfterFilter);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //2.1 Mouse hover on Currency Dropdown and click
        moveMouseAndClickOnElement(getElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")));

        //2.2 Mouse hover on £Pound Sterling and click
        List<WebElement> currencyList = getElements(By.xpath("//ul[@class='dropdown-menu']/li/button"));
        for (WebElement element : currencyList) {
            if (element.getText().equalsIgnoreCase("£Pound Sterling")) {
                element.click();
                break;
            }
        }


        //2.3 Mouse hover on Desktops Tab.
        WebElement desktops = getElement(By.linkText("Desktops"));
        moveMouseToElement(desktops);

        //2.4 Click on “Show All Desktops”
        selectMenu(getTextFromElement(By.xpath("//a[@class='see-all'][text()='Show AllDesktops']")));

        //2.5 Select Sort By position "Name: A to Z"
        WebElement sortPosition = getElement(By.id("input-sort"));
        selectElementByVisibleText(sortPosition, "Name (A - Z)");


        //2.6 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[text()='HP LP3065']"));

        //2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("Displaying the wrong product result", "HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));

        //2.8 Select Delivery Date "2023-11-27"

        //Select month and year
        clickOnElement(By.xpath("//div[@class='input-group date']/span"));

        String mon="November";
        String yer="2023";
        String dt="27";

        while (true) {
            String monthYear = getTextFromElement(By.xpath("(//thead//th[@class='picker-switch'])[1]"));
            String[] monthYearArray= monthYear.split(" ");
            String month=monthYearArray[0];
            String year=monthYearArray[1];

            if(month.equalsIgnoreCase(mon) && year.equalsIgnoreCase(yer)){
                break;
            } else{
                clickOnElement(By.xpath("(//thead//th[@class='picker-switch'])[1]/following-sibling::th"));
            }

        }

        //select date
       List <WebElement> allDates=getElements(By.xpath("//div[@class='datepicker-days']//td"));
        for(WebElement e:allDates){
            if(e.getText().equalsIgnoreCase(dt)){
                e.click();
                break;
            }
        }


        //2.9.Enter Qty "1” using Select class.
        getElement(By.id("input-quantity")).clear();
        sendTextToElement(By.id("input-quantity"),"2");

        //2.10 Click on “Add to Cart” button
        clickOnElement(By.id("button-cart"));

        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        String actualText=getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String expectedText ="Success: You have added HP LP3065 to your shopping cart!";
        Assert.assertTrue("The success message is not displayed correctly.",actualText.contains(expectedText));


        //2.12 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //2.13 Verify the text "Shopping Cart"
        Assert.assertTrue("User navigated to wrong page.", getTextFromElement(By.xpath("//div[@id='content']/h1")).contains("Shopping Cart"));

        //2.14 Verify the Product name "HP LP3065"
        actualText= getTextFromElement(By.linkText("HP LP3065"));
        expectedText="HP LP3065";
        Assert.assertEquals("Incorrect product name displayed",expectedText,actualText);

        System.out.println(getTextFromElement(By.xpath("//tbody/tr/td[2]/small")));
        //2.15 Verify the Delivery Date "2023-11-27"

        WebElement actualDate=driver.findElement(RelativeLocator.with(By.xpath("//tbody/tr/td[2]/small")).below(By.linkText("HP LP3065")));
        Assert.assertEquals("Incorrect delivery date.","Delivery Date:2023-11-27",actualDate.getText());

       // Assert.assertEquals("Incorrect delivery date.","Delivery Date2023-11-27",getTextFromElement(By.xpath("//tbody/tr/td[2]/small")));

        //2.16 Verify the Model "Product21"
        WebElement below=driver.findElement(RelativeLocator.with(By.xpath("//td[@class='text-left']")).below(By.xpath("//td[@class='text-left'][text()='Model']")));
        Assert.assertEquals("Product21",below.getText());

        //2.17 Verify the Total "£74.73"
        String aT=getTextFromElement(By.xpath("//tbody/tr/td[6]"));
        String eT="£74.73";
        Assert.assertEquals("The product total is incorrect.",eT,aT);


    }

    @After
    public void tearDown() {
        // closeBrowser();
    }

}
