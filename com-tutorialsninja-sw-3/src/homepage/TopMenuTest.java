package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilitites.Utility;


public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //1.1 create method with name "selectMenu" it has one parameter name "menu" of type string
    //1.2 This method should click on the menu whatever name is passed as parameter

    //The selectMenu method is in the Utility class


    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {

        WebElement desktops = getElement(By.linkText("Desktops"));

        // 1.1 Mouse hover on “Desktops” Tab and click
        moveMouseAndClickOnElement(desktops);

        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu(getTextFromElement(By.xpath("//a[@class='see-all'][text()='Show AllDesktops']")));

        //1.3 Verify the text ‘Desktops’
        Assert.assertEquals("Navigated to wrong page.", "Desktops", getTextFromElement(By.xpath("//div[@id='content']/h2")));

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        WebElement laptopsAndNoteBooks= getElement(By.linkText("Laptops & Notebooks"));
        moveMouseAndClickOnElement(laptopsAndNoteBooks);

        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu(getTextFromElement(By.xpath("//a[@class='see-all'][contains(text(),'Notebooks')]")));

        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals("Navigated to wrong page.","Laptops & Notebooks",getTextFromElement(By.xpath("//div[@id='content']/h2")));

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        //3.1 Mouse hover on “Components” Tab and click
        WebElement components=getElement(By.linkText("Components"));
        moveMouseAndClickOnElement(components);

        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu(getTextFromElement(By.xpath("//a[@class='see-all'][contains(text(),'AllComponents')]")));

        //3.3 Verify the text ‘Components
        Assert.assertEquals("Navigated to the wrong page.","Components",getTextFromElement(By.xpath("//div[@id='content']/h2")));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
