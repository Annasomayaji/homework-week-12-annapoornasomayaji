package testsuite;

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

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() {
        //* Mouse Hover on Women Menu
        WebElement women = getElement(By.id("ui-id-4"));
        moveMouseToElement(women);

        //* Mouse Hover on Tops
        WebElement tops = getElement(By.id("ui-id-9"));
        moveMouseToElement(tops);

        //* Click on Jackets
        WebElement jackets = getElement(By.id("ui-id-11"));
        moveMouseAndClickOnElement(jackets);



        //* Verify the products name display in alphabetical order
        //Before filter
        List<WebElement> beforeFilter = getElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> beforeFilterList = new ArrayList<>();
        for (WebElement e : beforeFilter) {
            beforeFilterList.add(e.getText());
        }
        Collections.sort(beforeFilterList);

        //* Select Sort By filter “Product Name”
        clickOnElement(By.id("sorter"));
        selectElementByValue(getElement(By.id("sorter")), "name");

        //After filter
        List<WebElement> afterFilter = getElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> afterFilterList = new ArrayList<>();
        for (WebElement e : afterFilter) {
           afterFilterList.add(e.getText());
        }

        Assert.assertEquals("The products are not displayed alphabetically.",beforeFilterList,afterFilterList);

    }

    @Test
    public void verifyTheSortByPriceFilter(){

        //* Mouse Hover on Women Menu
        WebElement women = getElement(By.id("ui-id-4"));
        moveMouseToElement(women);

        //* Mouse Hover on Tops
        WebElement tops = getElement(By.id("ui-id-9"));
        moveMouseToElement(tops);

        //* Click on Jackets
        WebElement jackets = getElement(By.id("ui-id-11"));
        moveMouseAndClickOnElement(jackets);

        //* Verify the products name display in alphabetical order
        //Before filter
        List<WebElement> beforeFilter = getElements(By.xpath("//span[@class='price-container price-final_price tax weee']//span[@class='price']"));
        List<Double> beforeFilterList = new ArrayList<>();
        for (WebElement e : beforeFilter) {
            System.out.println(e.getText());//for debug purpose
            beforeFilterList.add(Double.valueOf(e.getText().replace("$","")));


        }
        System.out.println(beforeFilterList);//for debug purpose
        Collections.sort(beforeFilterList);


        //* Select Sort By filter “Price”
        clickOnElement(By.id("sorter"));
        selectElementByValue(getElement(By.id("sorter")), "price");

        //After filter
        List<WebElement> afterFilter = getElements(By.xpath("//span[@class='price-container price-final_price tax weee']//span[@class='price']"));
        List<Double> afterFilterList = new ArrayList<>();
        for (WebElement e : afterFilter) {

           afterFilterList.add(Double.valueOf(e.getText().replace("$","")));
        }

       Assert.assertEquals("The products are not displayed from low to high price.",beforeFilterList,afterFilterList);
    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
