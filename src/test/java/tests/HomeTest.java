package tests;

import Pages.HomePage;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTest extends BaseTest {
    HomePage homePage;


    @Test(priority = 1)
    @Description("çanta aratma ve filtreleme")

    public void Filter_Bag() throws InterruptedException {


        homePage = new HomePage(getDriver());
        Thread.sleep(1000);
        homePage.search("çanta");
        Thread.sleep(2000);
        homePage.clickIcon();
        Thread.sleep(2000);
        Assert.assertTrue(homePage.isOnProductPageBag(), "çanta araması için 100.000+ sonuç listeleniyor");
        Thread.sleep(700);
        String oldShoesCheck = getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div")).getText();
        homePage.allcategories("el");
        Thread.sleep(3000);
        homePage.selectCategory(0);
        Thread.sleep(4000);
        homePage.brand("vak");
        homePage.clickBrand();
        Thread.sleep(2000);
        homePage.selectcolor(0);
        Thread.sleep(1000);
        homePage.clickPrice();
        Thread.sleep(1000);
        homePage.priceText("5000", "10000");
        Thread.sleep(2000);
        homePage.clickpriceButton();
        Thread.sleep(2000);
        String newShoesCheck = getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div")).getText();
        if(!oldShoesCheck.equals(newShoesCheck)){
            System.out.println("changed color");
        }else {
            System.out.println("not change");
        }
        Thread.sleep(3000);
        homePage.search(Keys.CONTROL + "a");
        homePage.search(String.valueOf(Keys.DELETE));

    }
    @Test(priority = 2)
    @Description("ruj aratma ve filtreleme")

    public void Filter_Lipstick() throws InterruptedException {
        homePage = new HomePage(getDriver());
        Thread.sleep(1000);
        homePage.search("ruj");
        Thread.sleep(700);
        homePage.clickIcon();
        Thread.sleep(700);
        Assert.assertTrue(homePage.isOnProductPageLipstick(), "ruj araması gerçekleştirilemedi");
        String oldLipstickCheck = getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div")).getText();
        Thread.sleep(700);
        homePage.brand("dior");
        homePage.clickBrand();
        Thread.sleep(3000);
        homePage.colorClick();
        homePage.selectcolor(9);
        Thread.sleep(1000);
        homePage.formClick();
        homePage.selectDataForm(0);
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,300)");

        if(homePage.isColorAvaliable()){
            Actions actions = new Actions(getDriver());
            actions.moveToElement(getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div"))).click().build().perform();



        }

        homePage.priceClick();
        homePage.priceText("700","1500");
        homePage.clickpriceButton();
        Thread.sleep(2000);
        String newLipstickCheck = getDriver().findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div")).getText();
        if(!oldLipstickCheck.equals(newLipstickCheck)){
            System.out.println("changed color");
        }else {
            System.out.println("not change");
        }



    }


}




















