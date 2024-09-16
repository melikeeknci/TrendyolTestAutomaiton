package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class FavoritiesPage extends BasePage {


    private By favoritiesButtonLocator = By.xpath("//*[@class=\"account-nav-item account-favorites\"]");
    private By productLocatorLocator = By.xpath("//div[@class='image-overlay-body']");
    private By loginLocator = By.xpath("//div[@class='link account-user']");
    private By favoriteIsCheckLocator = By.xpath("//div//a[@class='active']");
    private By addToBasketLocator = By.xpath("//*[@id=\"account-gw-favorites\"]/div/div[3]/div[1]/div/div/div[2]/div[3]/span");
    private By basketIsCheckLocator = By.xpath("//div[@class='basket-item-count-container visible']");
    private By sizeLocator= By.xpath("//*[@class=\"size-dropdown cursor \"]");
    //private  By sizeCheck = By.xpath("//div[@class='size-dropdown cursor ']//span[@class='text-multiple-size ']");
    private By dataSizeLocator=By.xpath("//span[@class='lower-dropdown-span false']");
    private By colletionClickLocator= By.xpath("//div[@class='add-to-collections-wrapper']");
    private  By newCollectionLocator=By.xpath("//div[@class='create-new-collection']");
    private By collectionNameLocator = By.xpath("//input[@class=\"collection-input\"]");
    private By createCollectionLocator = By.xpath("//button[@class=\"collection-submit-button\"]");
    private By closeLocator = By.id("Group-38");
    private By collectionLocator= By.xpath("//i[@class='i-collections-icon']");
    private By gotToCollectionLocator = By.xpath("//div[@class='collection-navigation']");
    private By deleteLocator= By.xpath("//div[@class='ufvrt-btn-wrppr']/i");
    private By searchLocator = By.xpath("//div[@class='click-outside']//input");
    private By searchButtonLocator = By.xpath("//i[@class='search-icon']");
    private By searchIsCheckLocator = By.xpath("//span[@class='searchtext']");
    private By giveUPLocator= By.xpath("//span[@class='cancel-button']");












    public FavoritiesPage(WebDriver driver) {
        super(driver);

    }

    public void login_click() {
        click(loginLocator);

    }
    public void giveUp() {
        click(giveUPLocator);

    }
    public void delete() {
        click(deleteLocator);

    }

    public void clickFavoritiesButton() {
        click(favoritiesButtonLocator);

    }
    public void search(){
        type(searchLocator,"mama");
        click(searchButtonLocator);
    }
    public Boolean isSearchCheck() {
        return isDisplayed(searchIsCheckLocator);

    }

    public Boolean isFavoritiesPage() {
        return isDisplayed(favoriteIsCheckLocator);

    }

    public void Basket()  {
        click(addToBasketLocator);

    }
    public void clickToSize()  {
        click(sizeLocator);

    }
    public List<WebElement> getAllSize(){

        return findAll(dataSizeLocator);
    }
    public List<WebElement> getAllProduct(){

        return findAll(productLocatorLocator);
    }
    public List<WebElement> getAllCollection(){

        return findAll(gotToCollectionLocator);
    }
    public List<WebElement> getAllClose(){

        return findAll(closeLocator);
    }
    public void selectClose(int i) {

        WebElement sizeClose=getAllClose().get(i);
        Actions action = new Actions(driver);
        action.click(sizeClose).perform();

    }

    public void selectCollection(int i) {

        WebElement sizeCollection=getAllCollection().get(i);
        Actions action = new Actions(driver);
        action.click(sizeCollection).perform();

    }
    public void selectProduct(int i) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productLocatorLocator));

        if (products.isEmpty()) {
            throw new NoSuchElementException("No products found");
        }
        WebElement sizeProduct = products.get(i);
        Actions action = new Actions(driver);
        action.click(sizeProduct).perform();


    }
    public void selectSize(int i) {

        WebElement sizeData=getAllSize().get(i);
        Actions action = new Actions(driver);
        action.click(sizeData).perform();

    }

    public Boolean BasketIsCheck()  {
        return isDisplayed(basketIsCheckLocator);

    }

    public void collection() throws InterruptedException {
        click(colletionClickLocator);


    }
    public void newcollection() {
        click(newCollectionLocator);

    }

    public void collectionName(String text){
        type(collectionNameLocator,text);
    }

    public void clickCreateCollection() {
        click(createCollectionLocator);

    }


    public void clickCollection() {
        click(collectionLocator);

    }



}
