package Pages;

import org.openqa.selenium.By;//
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderProcessingPage extends BasePage {




    private By addtoBasketLocator = By.className("add-to-basket");
    private By sizeLocator = By.xpath("//div[@class='sp-itm']");
    private By checkBasketLocator = By.xpath("//div//div[@class='basket-item-count-container visible'][contains(text(), 1)]");
    private By basketLocator= By.xpath("//a[@class='link account-basket']");
    private By confirmCartLocator=By.xpath("//*[@id=\"pb-container\"]/aside/div/div[1]/a");
    private By passtomembershipLocator =By.xpath("//div//button[contains (text(),\"Üye Olmadan Devam Et\")]");
    private By emailLocator =By.xpath("//input[@name='email']");
    private By nameLocator =By.xpath("//input[@name='name']");
    private By surnameLocator =By.xpath("//input[@name='surname']");
    private By cityLocator =By.xpath("//div[@name='cityId']");
    private By districtLocator =By.xpath("//div[@name='districtId']");
    private By neighbourhoodLocator =By.xpath("//div[@name='neighborhoodId']");
    private By addressLocator =By.xpath("//textarea[@name='addressLine']");
    private By titleLocator =By.xpath("//input[@name='addressName']");
    private By saveLocator =By.xpath("//button[@class='ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-medium ty-primary']");
    private By dataCityLocator =By.xpath("//*[@name=\"cityId\"]//div//div");
    private By districtDataLocator = By.xpath("//*[@name=\"districtId\"]//div//div");
    private By neighbourhoodDataLocator = By.xpath("//*[@name=\"neighborhoodId\"]//div//div");
    private By phoneLocator = By.xpath("//input[@name='phone']");
    private By closeLocator = By.xpath("//div[@class=\"ty-modal-content ty-relative user-address-modal\"]/a");
    private By reduceLocator =By.xpath("//button[@aria-label=\"Ürün adedi azaltma\"]");
    private By increaseLocator = By.xpath("//button[@aria-label=\"Ürün adedi arttırma\"]");
    private By sizeCheckLocator = By.xpath("//div//span[@class=\"size-variant-attr-value\"]");
    private By deleteLocator =By.xpath("//div//button[@class=\"checkout-saving-remove-button\"]");
    private By deleteCheckLocator = By.xpath("//div[@class=\"pb-basket-item-removed-item-information-label single\"]//p[contains(text(), '')]");
    private By allErrorMessageLocator =By.xpath("//div[@class='ty-display-flex ty-mgt-1']");
    private By falseEmailMessageLocator = By.xpath("//div[@class='ty-display-flex ty-mgt-1']//span[contains(text(), 'Lütfen geçerli bir email adresi giriniz.')]");
    private By falseNameMessageLocator = By.xpath("//*[@id=\"address-popup-container\"]/div/div[1]/div/div[2]/div/div[2]/form/div[2]/div[1]/div/span[1]");
    private By falseSurnameMessageLocator = By.xpath("//*[@id=\"address-popup-container\"]/div/div[1]/div/div[2]/div/div[2]/form/div[2]/div[2]/div/span[1]");
    private By falseAdressMessageLocator = By.xpath("//*[@id=\"address-popup-container\"]/div/div[1]/div/div[2]/div/div[2]/form/div[5]/div/div/span[1]");
    private By falseTitleMessageLocator = By.xpath("//*[@id=\"address-popup-container\"]/div/div[1]/div/div[2]/div/div[2]/form/div[6]/div/span[1]");
    private By colorLocator= By.xpath("//div[@class='styles-module_sliderBase__swkx1 attributeSlider-carousel']//div[@data-testid='sliderList']/a");
    private By colorCheckLocator = By.xpath("//div[@class='slc-title']/h2/span");
    private By favoritiesButtonLocator = By.xpath("//div//button[@class='fv']");
    private By productLocator=By.xpath("//*[@class=\"pb-basket-item-details\"]//a");
    private By acceptLocator= By.xpath("//button[@class='onboarding-popover__default-renderer-primary-button']");




    public OrderProcessingPage(WebDriver driver) {
        super(driver);

    }

    public List<WebElement> getAllSize(){
        return findAll(sizeLocator);
    }
    public void selectSize(int i) {

        List <WebElement>sizeData=getAllSize();
        WebElement sizeElement=sizeData.get(i);
        Actions action = new Actions(driver);
        action.click(sizeElement).perform();

    }
    public Boolean sizeCheck() {
        return isDisplayed(sizeCheckLocator);
    }
    public List<WebElement> getAllColor(){
        return findAll(colorLocator);
    }
    public void selectColor(int i) {
        List <WebElement>colorData=getAllColor();
        WebElement colorElement=colorData.get(i);
        Actions action = new Actions(driver);
        action.click(colorElement).perform();

    }
    public boolean isColorAvailable() {
        try {
            return driver.findElements(By.xpath("//div[@class='slc-title']/h2/span")).size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void colorChangeCheck() throws InterruptedException{

        String oldcolor = find(colorCheckLocator).getText();
        Thread.sleep(2000);
        selectColor(2);
        Thread.sleep(2000);
        String newcolor = find(colorCheckLocator).getText();
        if(!oldcolor.equals(newcolor)){
            System.out.println("changed color");
        }else {
            System.out.println("not change");
        }
    }

    public boolean isAccept() {
        try {
            return driver.findElements(By.xpath("//div[@class='onboarding-popover__default-renderer']")).size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAddToBasket() {
        click(addtoBasketLocator);
    }
    public Boolean isProductAddedToBasket() {
        return isDisplayed(checkBasketLocator);
    }
    public void basket() {
        click(basketLocator);
    }

    public void productClick() {
        click(productLocator);
    }

    public void selectCity(int i) {

        WebElement cityData=getAllCity().get(i);
        Actions action = new Actions(driver);
        action.click(cityData).perform();

    }

    public void selectDistrict(int i) {

        WebElement districtData=getAllDisctrict().get(i);
        Actions action = new Actions(driver);
        action.click(districtData).perform();

    }
    public void selectNeighbourhood(int i) {

        WebElement neighbourhoodData=getAllNeighbourhood().get(i);
        Actions action = new Actions(driver);
        action.click(neighbourhoodData).perform();
    }
    public List<WebElement> getAllCity(){

        return findAll(dataCityLocator);
    }
    public List<WebElement> getAllDisctrict(){

        return findAll(districtDataLocator);
    }
    public List<WebElement> getAllNeighbourhood(){

        return findAll(neighbourhoodDataLocator);
    }
    public void save(){
        click(saveLocator);
    }

    public void confirmAdressError() throws InterruptedException {
        click(confirmCartLocator);
        Thread.sleep(1000);
        click(passtomembershipLocator);
        Thread.sleep(1000);

    }

    public String falseMailText(){
        return getText(falseEmailMessageLocator);
    }
    public String falseNameText(){
        return getText(falseNameMessageLocator);
    }
    public String falseSurnameText(){
        return getText(falseSurnameMessageLocator);
    }
    public String falseAdressText(){
        return getText(falseAdressMessageLocator);
    }
    public String falseTitleText(){
        return getText(falseTitleMessageLocator);
    }
    public Boolean isAllError() {
        return isDisplayed(allErrorMessageLocator);
    }

    public Boolean isEmailError() {
        return isDisplayed(falseEmailMessageLocator);
    }
    public Boolean isNameError() {
        return isDisplayed(falseNameMessageLocator);
    }
    public Boolean isSurnameError() {
        return isDisplayed(falseSurnameMessageLocator);
    }
    public Boolean isAdressError() {
        return isDisplayed(falseAdressMessageLocator);
    }
    public Boolean isTitleError() {
        return isDisplayed(falseTitleMessageLocator);
    }

    public void falseEmail(String text) {
        type(emailLocator,text);
    }
    public void falseSurname(String text) {
        type(surnameLocator,text);
    }
    public void falseadress(String text) {
        type(addressLocator,text);

    }
    public void falseadressTitle(String text) {
        type(titleLocator,text);
    }
    public void falseName(String text) {
        type(nameLocator,text);
    }

    public void confirmAdress() throws InterruptedException {

        Thread.sleep(1000);
        find(emailLocator).sendKeys("tetotomasyon1234@gmail.com");
        Thread.sleep(1000);
        find(nameLocator).sendKeys("melike");
        Thread.sleep(1000);
        find(surnameLocator).sendKeys("ekinci");
        Thread.sleep(1000);
        WebElement phoneInput= find(phoneLocator);
        Actions actions = new Actions(driver);
        actions.click(phoneInput).sendKeys("5453864552").perform();
        find(addressLocator).sendKeys("mevlana mah.");
        Thread.sleep(1000);
        find(titleLocator).sendKeys("ev");

    }
    public void falsePhone(){
        WebElement phoneInput= find(phoneLocator);
        Actions actions = new Actions(driver);
        actions.click(phoneInput).sendKeys("sdsdc").perform();
    }
     public void ComboBox() throws InterruptedException {
         click(cityLocator);
         Thread.sleep(700);
         selectCity(5);
         Thread.sleep(700);
         click(districtLocator);
         Thread.sleep(700);
         selectDistrict(3);
         Thread.sleep(700);
         click(neighbourhoodLocator);
         Thread.sleep(700);
         selectNeighbourhood(5);
         Thread.sleep(700);
         click(saveLocator);
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='address-popup-container']/div/div[3]/div/div/a")));
         element.click();
         Thread.sleep(700);
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,-500)");
         Thread.sleep(700);
         click(closeLocator);

     }
    public void accept() {
        click(acceptLocator);

    }


     public void increaseIsProduct() throws InterruptedException {
         Thread.sleep(2000);
        click(increaseLocator);
        Thread.sleep(2000);
        click(reduceLocator);

     }

    public void Delete() throws InterruptedException {
        Thread.sleep(2000);
        click(deleteLocator);
    }
    public Boolean deleteIsCheck(){
        return isDisplayed(deleteCheckLocator);
    }
    public void clickFavoritiesButton() {
        click(favoritiesButtonLocator);

    }




}
