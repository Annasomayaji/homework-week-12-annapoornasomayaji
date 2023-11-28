package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.Arrays;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    //call openBrowser method
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }



    //1.1 create method with name "selectMenu" it has one parameter name "menu" of type string
    // and another parameter of int -->I added this to keep count of which element to click
    public void selectMenu(String menu, int i) {

        //calling the utility class method to click on the element
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[normalize-space()='" + menu + "']"));
        String actualText = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));

        //store expected top menu titles in an array
        String[] expectedText = {"Computers", "Electronics", "Apparel", "Digital downloads", "Books", "Jewelry", "Gift Cards"};
        Assert.assertEquals("The user is not navigated to the correct page in " + expectedText[i] + ". ", expectedText[i], actualText);

    }

    //    1.3. create the @Test
    //    method name verifyPageNavigation. Use selectMenu method to
    //    select the Menu and click on it and verify the page navigation.
    @Test
    public void verifyPageNavigation() {

        //declare an array to store top menu elements
        String[] topMenuArray = new String[7];
        int i = 0;

        List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/li"));

        //store each element in the array using for each loop
        for (WebElement topMenuItem : topMenu) {
            //   System.out.println(topMenuItem.getText()); //for debug purpose
            //   selectMenu(topMenuItem.getText());  //gives stale element error for second element onwards!
            topMenuArray[i] = topMenuItem.getText();
            i++;
        }

        System.out.println(Arrays.toString(topMenuArray)); //for debug purpose

        //call the selectMenu method to validate each menu element click and navigation
        for (i = 0; i < 7; i++) {
            selectMenu(topMenuArray[i], i);
        }
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
