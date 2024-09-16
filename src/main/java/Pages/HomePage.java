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


public class HomePage  extends BasePage{

    private WebDriverWait wait;

    private By searchLocator= By.xpath("//*[@class='N4M8bfaJ']//input");
    private By productNameLocator= By.xpath("//div[@class='image-overlay-body']");
    private By titleLocatorShoes= By.xpath("//*[@class='dscrptn dscrptn-V2']//h1[contains (text(), 'ayakkabı')]");
    private By titleLocatorShoes1= By.xpath("//*[@class='lefted-search-title'][contains (text(), 'ayakkabı')]");
    private By titleLocatorBag= By.xpath("//div[@class='dscrptn dscrptn-V2']//h1[ contains (text(), 'çanta' ) ]");
    private By titleBag1Locator=By.xpath("//div[@class='lefted-search-title'][ contains (text(), 'çanta' ) ]");
    private By titleLocatorLipstick= By.xpath("//div[@class='dscrptn dscrptn-V2']//h1[ contains (text(), 'ruj' ) ]");
    private By titleLocatorLipstick1= By.xpath("//div[@class='lefted-search-title'][ contains (text(), 'ruj' ) ]");
    private By iconLocator= By.xpath("//*[@data-testid=\"search-icon\"]");
    private By allCategoriesLocator= By.xpath("//div[@data-title='Tüm Kategoriler']//input");
    private By categoriesDataLocator= By.xpath("//*[@id=\"sticky-aggregations\"]/div/div[2]/div[3]/div/div/div[1]/div/a");
    private By brandLocator= By.xpath("//div[@data-title='Marka']//input");
    private By brandDataLocator= By.xpath("//*[@id=\"sticky-aggregations\"]/div/div[3]/div[3]/div/div/div[1]/div/a");
    private By colorLocator =By.xpath("//*[@class=\"fltr-cntnr-ttl\"][contains(text(),'Renk')]");
    private By materialSearchLocator=By.xpath("");
    private By colorDataLocator =By.xpath("//*[@class=\"fltrs color\"]/a");
    private By priceLocator =By.xpath("//*[@class=\"fltr-cntnr-ttl\"][contains(text(),'Fiyat')]");
    private By minpriceLocator= By.xpath("//div//input[@class='fltr-srch-prc-rng-input min']");
    private By maxpriceLocator= By.xpath("//div//input[@class='fltr-srch-prc-rng-input max']");
    private By priceButtonLocator= By.xpath("//button[@class=\"fltr-srch-prc-rng-srch\"]");
    private By formLocator =By.xpath("//*[@class=\"fltr-cntnr-ttl\"][contains(text(),'Form')]");
    private By formDataLocator =By.xpath("//a[contains(@href, 'attr=40|3995') or contains(@href, 'attr=40|417')]/div[contains(@class, 'fltr-item-text') and (text()='Sıvı' or text()='Stick')]\n");








    public HomePage( WebDriver driver)
    {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void search(String text){
      type(searchLocator,text);

    }
    public void clickIcon(){
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(iconLocator));
        icon.click();
    }
    public List<WebElement> getAllProduct(){
        return findAll(productNameLocator);
    }
    public void selectProduct(int i) {
        List<WebElement> products = getAllProduct();
        if (i < products.size()) {
            WebElement productElement = products.get(i);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productElement);
        } else {
            System.out.println("Ürün indexi mevcut ürünlerin dışına çıkıyor: " + i);
        }
    }
    public List<WebElement> getAllDataForm() {
        return findAll(formDataLocator);
    }
    public void selectDataForm(int i) {

        List<WebElement> form = getAllDataForm();
        if(i>=0 && i<form.size()){
            WebElement formElement = form.get(i);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", formElement);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(formElement));
            Actions actions = new Actions(driver);
            actions.moveToElement(formElement);
        }

    }
    public  List<WebElement> getAllCategories(){
      return findAll(categoriesDataLocator);
    }
    public void selectCategory(int i){
        List<WebElement> categories = getAllCategories();
        if (i >= categories.size()) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds for length " + categories.size());
        }
        WebElement categoryElement = categories.get(i);
        Actions action = new Actions(driver);
        action.click(categoryElement).perform();

    }


    public void clickBrand(){
        click(brandDataLocator);
    }

    public List<WebElement> getAllColor() {
        return findAll(colorDataLocator);
    }
    public void selectcolor(int i) {

        List<WebElement> color = getAllColor();
        WebElement colorElement=color.get(i);
        Actions action = new Actions(driver);
        action.click(colorElement).perform();
    }
    public void clickPrice() {
        WebElement price = wait.until(ExpectedConditions.elementToBeClickable(priceLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", price);

    }


    public Boolean isOnProductPageShoes() {
       return isDisplayed(titleLocatorShoes) || isDisplayed(titleLocatorShoes1);
    }
    public Boolean isOnProductPageBag() {
        return isDisplayed(titleBag1Locator) || (isDisplayed(titleLocatorBag));

    }
    public Boolean isOnProductPageLipstick() {
        return isDisplayed(titleLocatorLipstick) || isDisplayed(titleLocatorLipstick1) ;
    }

    public void allcategories(String text){
        type(allCategoriesLocator,text);

    }
    public void brand(String text){
        type(brandLocator,text);

    }
    public void colorClick(){
        WebElement color= wait.until(ExpectedConditions.elementToBeClickable(colorLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", color);


    }
    public void materialSearch(String text){
      type(materialSearchLocator,text);


    }
    public void priceClick(){
        WebElement price= wait.until(ExpectedConditions.elementToBeClickable(priceLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", price);


    }
    public void priceText(String min,String max) throws InterruptedException {
        type(minpriceLocator, min);
        Thread.sleep(2000);
        type(maxpriceLocator, max);
    }
    public void clickpriceButton(){
        click(priceButtonLocator);

    }

    public void formClick(){
        WebElement form= wait.until(ExpectedConditions.elementToBeClickable(formLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", form);



    }
    public boolean isColorAvaliable(){
        try {
            return driver.findElements(By.xpath("//div[@class='popup-heading']")).size() > 0;
        }catch (Exception e){
            return false;
        }
    }


}
