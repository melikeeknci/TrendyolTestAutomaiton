package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
    public void click(By locator) {
        find(locator).click();

    }
    public void type(By locator, String text) {
        find(locator).sendKeys(text);
    }
    public Boolean isDisplayed(By locator) {
        try{
            return find(locator).isDisplayed();
        }catch(NoSuchElementException e){
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
    public Double cleanPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9,]", "").replace(",", "."));

    }
    public String translateTurkish(String turkisText) {

        return turkisText
                .replace("ç", "c")
                .replace("Ç", "C")
                .replace("ğ", "g")
                .replace("Ğ", "G")
                .replace("ı", "i")
                .replace("İ", "I")
                .replace("ö", "o")
                .replace("Ö", "O")
                .replace("ş", "s")
                .replace("Ş", "S")
                .replace("ü", "u")
                .replace("Ü", "U");


    }
    public Double getPrice(By locator){
        String priceText= getText(locator);
        return cleanPrice(priceText);
    }


}
