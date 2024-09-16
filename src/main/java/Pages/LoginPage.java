package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By clickLoginLocator = By.xpath("//div[@class='account-nav-item user-login-container']");
    private By clickEmailLocator = By.id("login-email");
    private By clickPasswordLocator = By.id("login-password-input");
    private By enterLoginLocator = By.xpath("//*[@class=\"q-primary q-fluid q-button-medium q-button submit\"]");
    private By myAccount=By.xpath("//div//p[ contains (text(), 'Hesabım' ) ]");
    private By errorMessage = By.xpath("//*[@class=\"message\"]");
    private By forgotThePasswordLocator = By.xpath("//*[@class=\"forgot-password\"]//span");
    private By renewEmailLocator =By.xpath("//*[@name=\"email\"]");
    private  By renewalPasswordTextLocator =By.xpath("//span[@class=\"title\"]");
    private By renewPasswordLocator=By.xpath("//*[@class=\"ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-small ty-primary\"]");
    private By returnBackLocator = By.xpath("//*[@class=\"ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-small ty-notr\"]");
    private By returnBackLocator2 = By.xpath("//*[@id=\"change-password-container\"]/form/button[3]");
    private By login2Locator=By.xpath("//*[@id=\"change-password-container\"]/form/button[1]");
    private By PasswordinvalideEmailMessageLocator=By.xpath("//div[@class='ty-display-flex ty-mgt-1']//span[contains (text (), 'Geçerli bir e-posta giriniz.')]");
    private By mainPageCheckLocator= By.xpath("//div[@class='lr-title']//h1");






    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void Login(String email, String password) {
        type(clickEmailLocator, email);
        type(clickPasswordLocator, password);
    }

    public void enterEmail(String email) {
        find(clickEmailLocator).sendKeys(email);
    }


    public void clickLogin() {
        click(clickLoginLocator);
    }

    public void clickLoginButton() {
        click(enterLoginLocator);

    }
    public String falseMailText(){
        return getText(errorMessage);
    }
    public String falsePassText(){
        return getText(errorMessage);
    }

    public Boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public Boolean isLoginDisplayed() {
        return isDisplayed(myAccount);
    }

    public void forgotThePassword() {
        click(forgotThePasswordLocator);
    }

    public Boolean isPasswordDisplayed() {
        return isDisplayed(renewalPasswordTextLocator);
    }

    public void RenewEmail(String text) {
        type(renewEmailLocator, text);
        click(renewPasswordLocator);
    }

    public void returnback() {
        click(returnBackLocator);
    }

    public void returnback2() {
        click(returnBackLocator2);
    }

    public void againlogin() {
        click(login2Locator);
    }

    public void email2(String email) {
        type(renewEmailLocator, email);

    }

    public Boolean passwordInvalidemail(String text) {
        type(renewEmailLocator, text);
        click(login2Locator);
        return isDisplayed(PasswordinvalideEmailMessageLocator);
    }

    public Boolean isMainPage() {
        return isDisplayed(mainPageCheckLocator);
    }

    public Boolean goToPaswordCheck() {
        return isDisplayed(renewalPasswordTextLocator);


    }
}
