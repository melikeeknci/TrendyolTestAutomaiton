package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CouponPage extends BasePage {

    private WebDriverWait wait;


    private By couponLocator = By.xpath("//*[@class=\"fltr-item-text\"][contains(text(),'Kuponlu Ürünler')]");
    private By winLocator = By.xpath("//div[@class='coupon-collect-button']");
    private By couponCloseLocator = By.xpath("//div[@class='modal-close']");
    private By applyCouponLocator = By.className("coupon-card-item-button-approve");
    private By confirmCartLocator = By.xpath("//*[@id=\"pb-container\"]/aside/div/div[1]/a");
    private By nameLocator = By.xpath("//input[@name='name']");
    private By surnameLocator = By.xpath("//input[@name='surname']");
    private By phoneLocator = By.xpath("//input[@name='phone']");
    private By cityLocator = By.xpath("//div[@name='cityId']");
    private By districtLocator = By.xpath("//div[@name='districtId']");
    private By neighbourhoodLocator = By.xpath("//div[@name='neighborhoodId']");
    private By dataCityLocator = By.xpath("//*[@name=\"cityId\"]//div//div");
    private By districtDataLocator = By.xpath("//*[@name=\"districtId\"]//div//div");
    private By neighbourhoodDataLocator = By.xpath("//*[@name=\"neighborhoodId\"]//div//div");
    private By addressLocator = By.xpath("//textarea[@name='addressLine']");
    private By titleLocator = By.xpath("//input[@name='addressName']");
    private By saveLocator = By.xpath("//*[@class=\"ty-display-flex ty-flex-row ty-form-group\"]/button");
    private By totalPriceLocator = By.xpath("//div[@class='pb-summary-total-price discount-active']");
    private By discountLocator = By.xpath("//div[@class='total-saving']");
    private By productPriceLocator = By.xpath("//div[@class='pb-basket-item-price']");
   // private By lowerLimitLocator = By.xpath("//span[@class='coupon-card-item-lower-limit-price']");
    private By lowerLimitLocator = By.xpath("//span[@class='coupon-card-item-information-limit']");

    public CouponPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void confirmAdress(String name, String surname, String adress, String title) {

        type(nameLocator, name);
        type(surnameLocator, surname);
        type(addressLocator, adress);
        type(titleLocator, title);
    }

    public void x() {

        WebElement phoneInput = find(phoneLocator);
        Actions actions = new Actions(driver);
        actions.click(phoneInput).sendKeys("5453864552").perform();

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
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ty-modal-content ty-relative']//a")));
        element.click();
        Thread.sleep(700);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ty-modal-content ty-relative user-address-modal']//a")));
        element1.click();

    }

    public void selectCity(int i) {

        WebElement cityData = getAllCity().get(i);
        Actions action = new Actions(driver);
        action.click(cityData).perform();

    }

    public void selectDistrict(int i) {

        WebElement districtData = getAllDisctrict().get(i);
        Actions action = new Actions(driver);
        action.click(districtData).perform();

    }

    public void selectNeighbourhood(int i) {

        WebElement neighbourhoodData = getAllNeighbourhood().get(i);
        Actions action = new Actions(driver);
        action.click(neighbourhoodData).perform();
    }

    public List<WebElement> getAllCity() {

        return findAll(dataCityLocator);
    }

    public List<WebElement> getAllDisctrict() {

        return findAll(districtDataLocator);
    }

    public List<WebElement> getAllNeighbourhood() {

        return findAll(neighbourhoodDataLocator);
    }

    public List<WebElement> getAllApplyCoupon() {
        return findAll(applyCouponLocator);
    }

    public List<WebElement> getAllProductPrice() {
        return findAll(productPriceLocator);
    }

    public List<WebElement> getAllLowerLimit() {
        return findAll(lowerLimitLocator);
    }

    public Double selectLowerlimit() throws InterruptedException {

        List<WebElement> lowerLimit = getAllLowerLimit();
        if (lowerLimit.size() > 0) {
            while (isSliderAvailable()) {
                WebElement sliderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='coupons-box-slider']//button[@class='styles-module_carousel-arrow__26sRw'][@data-direction='right']")));
                if (sliderButton != null) {
                    sliderButton.click();
                }
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            WebElement lowerLimitData = lowerLimit.get(lowerLimit.size()-1);
            Thread.sleep(1000);
            String lastLowerLimit= lowerLimitData.getText();
           return cleanPrice(lastLowerLimit);

        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds for product price list.");
        }
    }

    public Double selectProductPrice(int j) {
        List<WebElement> productPrice = getAllProductPrice();
        if (productPrice.size()>0) {
            WebElement productPriceData = productPrice.get(j);
            String lastPrice= productPriceData.getText();
            return cleanPrice(lastPrice);

        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds for product price list.");
        }
    }

    public void selectApplyCoupon() {
        List<WebElement> couponApplyData = getAllApplyCoupon();
        if (couponApplyData.size() > 0) {
            WebElement lastcouponApplyData = couponApplyData.get(couponApplyData.size()-1);
            Actions action = new Actions(driver);
            action.click(lastcouponApplyData).perform();
        } else {
        throw new IndexOutOfBoundsException("Kupon listesi geçerli değil.");
    }

    }

    public List<WebElement> getAllCoupon() {
        return findAll(winLocator);
    }

    public void selectCoupon(int i) {
        List<WebElement> couponData = getAllCoupon();
        WebElement couponElement = couponData.get(i);
        Actions action = new Actions(driver);
        action.click(couponElement).perform();

    }
    public Boolean isSliderAvailable(){
        try{
            return driver.findElements(By.xpath("//div[@class='coupons-box-slider']//button[@class='styles-module_carousel-arrow__26sRw'][@data-direction='right']")).size() >0;
        } catch(NoSuchElementException e){
            return false;
        }
    }

    public boolean isCouponAvailable() {
        try {
            return driver.findElements(By.xpath("//div//p[contains(text(), 'KUPON TANIMLANDI')]")).size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCouponNotAvailable() {
        try {
            return driver.findElements(By.xpath("//div//p[contains(text(), 'Kazanılan kuponlar maalesef tekrar toplanamıyor. Keyifli alışverişler')]")).size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isLimitFullAvailable() {
        try {
            return driver.findElements(By.xpath("//div//p[contains(text(), 'Kazanılan kuponlar maalesef tekrar toplanamıyor. Keyifli alışverişler')]")).size() > 0;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public Boolean isCouponApplyAvaliable(){
        try {
            return driver.findElements(By.xpath("//div[@class='coupon-collect-button']")).size() > 0;

        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void couponClose() {

        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(couponCloseLocator));
        close.click();

    }

    public void clickCoupon() {
        WebElement coupon = wait.until(ExpectedConditions.elementToBeClickable(couponLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", coupon);
        coupon.click();
    }

    public void clickOrder() {
        WebElement order = wait.until(ExpectedConditions.elementToBeClickable(confirmCartLocator));
        order.click();
    }


    public Double getDiscountPrice() {
        return getPrice(discountLocator);
    }

    public Double getTotalPrice() {
        return getPrice(totalPriceLocator);
    }


}
