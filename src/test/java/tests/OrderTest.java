package tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.OrderProcessingPage;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class OrderTest extends BaseTest {
    HomePage homePage;
    OrderProcessingPage orderProcessingPage;
    LoginPage login;

    @Test(priority = 1)
    @Description("ürün arama")

    public void search_shoes() throws InterruptedException {
        homePage = new HomePage(getDriver());
        orderProcessingPage = new OrderProcessingPage(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Thread.sleep(2000);
        homePage.search("ayakkabı");
        Thread.sleep(2000);
        homePage.clickIcon();
        Thread.sleep(2000);
        Assert.assertTrue(homePage.isOnProductPageShoes(), "ayakkabı araması için 100.000+ sonuç listeleniyor");
        Thread.sleep(2000);
        boolean productSelected = false;
        for (int i =0; i < homePage.getAllProduct().size(); i++) {
            homePage.selectProduct(i);
            String originalWindow = getDriver().getWindowHandle();
            for (String winHandle : getDriver().getWindowHandles()) {
                getDriver().switchTo().window(winHandle);
            }
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,300)");
            if(orderProcessingPage.isAccept()){
                orderProcessingPage.accept();
            }
            Thread.sleep(2000);
            if (orderProcessingPage.isColorAvailable()) {
                orderProcessingPage.colorChangeCheck();
                Thread.sleep(2000);
                orderProcessingPage.selectSize(0);
                Thread.sleep(2000);
                Assert.assertTrue(orderProcessingPage.sizeCheck(), "yanlış beden ");
                productSelected = true;
                break;
            } else {
                System.out.println("Bu üründe renk seçeneği yok, başka bir ürün seçiliyor...");
                getDriver().switchTo().window(originalWindow);
                Thread.sleep(2000);
            }
        }

        if (!productSelected) {
            throw new NoSuchElementException("Renk seçeneği olan bir ürün bulunamadı.");
        }

        orderProcessingPage.clickAddToBasket();
        Thread.sleep(2000);
        Assert.assertTrue(orderProcessingPage.isProductAddedToBasket(), "ürün sepete eklenmedi");
        orderProcessingPage.basket();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    @Description("adres pop-up alanı hata mesajları kontrolü")

    public void order_processing_error() throws InterruptedException {
        homePage = new HomePage(getDriver());
        orderProcessingPage = new OrderProcessingPage(getDriver());
        orderProcessingPage.confirmAdressError();
        Thread.sleep(2000);
        //e mail
        orderProcessingPage.falseEmail(configProp.getProperty("falseUsername"));
        orderProcessingPage.save();
        Thread.sleep(2000);
        Assert.assertTrue(orderProcessingPage.falseMailText().contains(errorProp.getProperty("orderFalseMailText")),"Expected warning message is not displayed");
        orderProcessingPage.falseEmail(Keys.CONTROL + "a");
        orderProcessingPage.falseEmail(String.valueOf(Keys.DELETE));
        orderProcessingPage.falseEmail(configProp.getProperty("falseUsername1"));
        orderProcessingPage.save();
        Thread.sleep(2000);
        Assert.assertTrue(orderProcessingPage.falseMailText().contains(errorProp.getProperty("orderFalseMailText")),"Expected warning message is not displayed");
        Thread.sleep(700);
        orderProcessingPage.falseEmail(Keys.CONTROL + "a");
        orderProcessingPage.falseEmail(String.valueOf(Keys.DELETE));

//name
        orderProcessingPage.falseName(errorProp.getProperty("orderFalseName"));
        orderProcessingPage.save();
        Thread.sleep(2000);
        Assert.assertTrue(orderProcessingPage.falseNameText().contains(errorProp.getProperty("orderFalseNameText")),"Expected warning message is not displayed");
        orderProcessingPage.falseName(Keys.CONTROL + "a");
        orderProcessingPage.falseName(String.valueOf(Keys.DELETE));
        orderProcessingPage.falseName(errorProp.getProperty("orderFalseName1"));
        orderProcessingPage.save();
        Thread.sleep(2000);
        Assert.assertTrue(orderProcessingPage.falseNameText().contains(errorProp.getProperty("orderFalseNameText")),"Expected warning message is not displayed");
        orderProcessingPage.falseName(Keys.CONTROL + "a");
        orderProcessingPage.falseName(String.valueOf(Keys.DELETE));

//surname

        orderProcessingPage.falseSurname(errorProp.getProperty("orderFalseName"));
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseSurnameText().contains(errorProp.getProperty("orderFalseSurnameText")),"Expected warning message is not displayed");
        orderProcessingPage.falseSurname(Keys.CONTROL + "a");
        orderProcessingPage.falseSurname(String.valueOf(Keys.DELETE));
        Thread.sleep(1000);
        orderProcessingPage.falseSurname(errorProp.getProperty("orderFalseName1"));
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseSurnameText().contains(errorProp.getProperty("orderFalseSurnameText")),"Expected warning message is not displayed");
        orderProcessingPage.falseSurname(Keys.CONTROL + "a");
        orderProcessingPage.falseSurname(String.valueOf(Keys.DELETE));


//adress

        orderProcessingPage.falseadress(errorProp.getProperty("orderFalseAdress"));
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseAdressText().contains(errorProp.getProperty("orderFalseAdressText")),"Expected warning message is not displayed");
        orderProcessingPage.falseadress(Keys.CONTROL + "a");
        orderProcessingPage.falseadress(String.valueOf(Keys.DELETE));
        Thread.sleep(1000);
        orderProcessingPage.falseadress(errorProp.getProperty("orderFalseAdress1"));
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseAdressText().contains(errorProp.getProperty("orderFalseAdressText")),"Expected warning message is not displayed");
        orderProcessingPage.falseadress(Keys.CONTROL + "a");
        orderProcessingPage.falseadress(String.valueOf(Keys.DELETE));

//adress title
        orderProcessingPage.falseadressTitle("");
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseTitleText().contains(errorProp.getProperty("orderFalseTitleText")),"Expected warning message is not displayed");
        orderProcessingPage.falseadressTitle(Keys.CONTROL + "a");
        orderProcessingPage.falseadressTitle(String.valueOf(Keys.DELETE));
        Thread.sleep(1000);
        orderProcessingPage.falseadressTitle(errorProp.getProperty("orderFalseName1"));
        orderProcessingPage.save();
        Thread.sleep(1000);
        Assert.assertTrue(orderProcessingPage.falseTitleText().contains(errorProp.getProperty("orderFalseTitleText")),"Expected warning message is not displayed");
        orderProcessingPage.falsePhone();
        orderProcessingPage.falseadressTitle(Keys.CONTROL + "a");
        orderProcessingPage.falseadressTitle(String.valueOf(Keys.DELETE));
    }

    @Test(priority = 3)
    @Description("adress pop-up alanı")

    public void order_processing() throws InterruptedException {
        orderProcessingPage = new OrderProcessingPage(getDriver());
        homePage = new HomePage(getDriver());
        orderProcessingPage.confirmAdress();
        orderProcessingPage.ComboBox();
    }

    @Test(priority = 4)
    @Description("ürün favorile butonu aktifliği")

    public void favorities() throws InterruptedException {
        homePage = new HomePage(getDriver());
        login = new LoginPage(getDriver());

        orderProcessingPage = new OrderProcessingPage(getDriver());
        orderProcessingPage.productClick();
        Thread.sleep(2000);

        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        Thread.sleep(2000);
        orderProcessingPage.clickFavoritiesButton();
        Thread.sleep(1000);
        login.Login(configProp.getProperty("userName"), configProp.getProperty("password"));
        login.clickLoginButton();

        Set<String> allWindowHandles = getDriver().getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
        if (windowHandlesList.size() >= 2) {
            String secondWindowHandle = windowHandlesList.get(1);
            getDriver().switchTo().window(secondWindowHandle);
        } else {
            throw new RuntimeException("İkinci pencere bulunamadı.");
        }
    }
    @Test(priority = 5)
    @Description("sepetim sayasında ürün silme, artırama, eksiltme işlemleri")

    public void increase_reduce() throws InterruptedException {
        homePage = new HomePage(getDriver());
        orderProcessingPage = new OrderProcessingPage(getDriver());
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
        if (windowHandlesList.size() >= 2) {
            String secondWindowHandle = windowHandlesList.get(1); // İkinci pencere tanıtıcısı
            getDriver().switchTo().window(secondWindowHandle);
        } else {
            throw new RuntimeException("İkinci pencere bulunamadı.");
        }

        Thread.sleep(1000);
        orderProcessingPage.increaseIsProduct();
        Thread.sleep(2000);
        orderProcessingPage.Delete();
        Thread.sleep(2000);
        orderProcessingPage.deleteIsCheck();

    }


}
