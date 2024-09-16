package tests;

import Pages.BasePage;
import Pages.LoginPage;
import jdk.jfr.Description;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.System.getProperty;


public class LoginTest extends BaseTest {


    LoginPage login;
    BasePage base;



    @Test(priority = 1)
    @Description("hatalı mail adresi")

    public void false_email() throws InterruptedException {

        login = new LoginPage(getDriver());
        Thread.sleep(2000);
        login.clickLogin();
        Thread.sleep(2000);
        login.Login(configProp.getProperty("falseUsername"), configProp.getProperty("password"));
        Thread.sleep(2000);
        login.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertTrue(login.falseMailText().contains(errorProp.getProperty("loginFalseMail1")),"Expected Warning message is not dispalyed");

    }

    @Test(priority = 2)
    @Description("Hatalı şifre girişi")

    public void false_password() throws InterruptedException {
        login = new LoginPage(getDriver());
        base = new BasePage(getDriver());
        Thread.sleep(2000);
        login.clickLogin();
        Thread.sleep(2000);
        login.Login(getProperty("userName"),getProperty("falsePassword"));
        Thread.sleep(2000);
        login.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertTrue(login.falsePassText().contains(errorProp.getProperty("loginFalsePassword")),"Expected Warning message is not dispalyed");


    }

    @Test(priority = 3)
    @Description("input alanlarını boş bırakma")
    public void empty() throws InterruptedException {
        login = new LoginPage(getDriver());
        login.clickLogin();
        Thread.sleep(2000);
        login.Login("testotomasyon1234@gmail.com", "");
        login.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertTrue(login.falsePassText().contains(errorProp.getProperty("LoginEmptyPassword")),"Expected Warning message is not dispalyed");


        login.enterEmail(Keys.CONTROL + "a");
        login.enterEmail(String.valueOf(Keys.DELETE));
        login.Login("", "Melike123.");
        login.clickLoginButton();
        Assert.assertTrue(login.falsePassText().contains(errorProp.getProperty("LoginEmptyMail")),"Expected Warning message is not dispalyed");
        Thread.sleep(2000);

    }

    @Test(priority = 4)
    @Description("hata mesajları kontrolü")

    public void error_message() throws InterruptedException {
        login = new LoginPage(getDriver());
        login.clickLogin();
        Thread.sleep(2000);
        login.forgotThePassword();
        Thread.sleep(2000);
        Assert.assertTrue(login.isPasswordDisplayed(), "Şifre Yenileme Ekanında Değiliz");
        Thread.sleep(2000);
        login.passwordInvalidemail(configProp.getProperty("falseUsername1"));
        Thread.sleep(2000);
        login.email2(Keys.CONTROL + "a");
        login.email2(String.valueOf(Keys.DELETE));
        Thread.sleep(2000);
        login.passwordInvalidemail(configProp.getProperty("falseUsername"));
        Thread.sleep(2000);
        login.returnback();
        Thread.sleep(2000);
        Assert.assertTrue(login.isMainPage(), "giriş yap sayfasında değilsiniz");
        login.forgotThePassword();
        login.RenewEmail(configProp.getProperty("userName"));
        Thread.sleep(2000);
        Assert.assertTrue(login.goToPaswordCheck(), "şifreniz gönderilemedi");
        Thread.sleep(2000);
        login.returnback2();
        Thread.sleep(2000);
        Assert.assertTrue(login.isPasswordDisplayed(), "Şifre Yenileme Ekanında Değiliz");
        login.RenewEmail(configProp.getProperty("userName"));
        Thread.sleep(2000);
        login.againlogin();
        Thread.sleep(4000);
        Assert.assertTrue(login.isMainPage(), "ana sayfada değilsiniz");


    }

    @Test(priority = 5)
    @Description("doğru giriş")

    public void true_login() throws InterruptedException {
        login = new LoginPage(getDriver());
        Thread.sleep(2000);
        login.clickLogin();
        login.Login(configProp.getProperty("userName"), configProp.getProperty("password"));
        login.clickLoginButton();
        Thread.sleep(2000);
        Assert.assertTrue(login.isLoginDisplayed(), "Giriş işlemi başarısız oldu!");


    }
}













