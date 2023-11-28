package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    /**
     * This method will get element
     */
    public WebElement getElement(By by){
        return driver.findElement(by);
    }

    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * This element will get text from an element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    /**
     * This method will send text to element
     */

    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    /**
     * This method will find list of elements
     */

    public List<WebElement> getElements(By by){
        return  driver.findElements(by);

    }

    // ************************************Select methods************************************

    /**
     * This method will select element from drop-down using value
     */
    public void selectElementByValue(WebElement e, String val){
        Select select=new Select(e);
        select.selectByValue(val);
    }

    /**
     * This method will select element from drop-down using visible text
     */
    public void selectElementByVisibleText(WebElement e, String str){
        Select select=new Select(e);
        select.selectByVisibleText(str);
    }

    /**
     * This method will select element from drop-down using index
     */
    public void selectElementByValue(WebElement e, int index) {
        Select select = new Select(e);
        select.selectByIndex(index);
    }

    //****************************************Actions class methods*******************************
    public void moveMouseToElement(WebElement e){
        Actions action=new Actions(driver);
        action.moveToElement(e).build().perform();
    }

    public void moveMouseAndClickOnElement(WebElement e){
        Actions action= new Actions(driver);
        action.moveToElement(e).click().build().perform();
    }



    //*********************************** user defined methods***********************

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//a[text()='" + menu + "']"));

    }

    public void validateResponse(String msg, String expectedText, String actualText){
        Assert.assertEquals(msg,expectedText,actualText);
    }

}
