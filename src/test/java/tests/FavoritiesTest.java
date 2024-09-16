package tests;

import Pages.FavoritiesPage;
import Pages.LoginPage;
import Pages.OrderProcessingPage;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FavoritiesTest extends BaseTest {
  FavoritiesPage favoritiesPage;
  LoginPage login;
  OrderProcessingPage order;


  @Test(priority = 1)
  @Description("sepete ekleme")
  public void addto_basket() throws InterruptedException {
    favoritiesPage = new FavoritiesPage(getDriver());
    login = new LoginPage(getDriver());
    favoritiesPage.login_click();
    login.Login(configProp.getProperty("userName"), configProp.getProperty("password"));
    login.clickLoginButton();
    Thread.sleep(5000);
    favoritiesPage.clickFavoritiesButton();
    Thread.sleep(2000);
    Assert.assertTrue(favoritiesPage.isFavoritiesPage(), "favorilerim sayfasında değilsiniz");
    Thread.sleep(2000);
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("window.scrollBy(0,500)");
    Thread.sleep(1000);
    favoritiesPage.Basket();
    Thread.sleep(1000);
    js.executeScript("window.scrollBy(0,-500)");
    Thread.sleep(1000);
    Assert.assertTrue(favoritiesPage.BasketIsCheck(), "Sepete ürün eklenmedi");


  }

  @Test(priority = 2)
  @Description("Sepetim sayfası")

  public void chooese_size() throws InterruptedException {
    favoritiesPage = new FavoritiesPage(getDriver());
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    Thread.sleep(2000);
    js.executeScript("window.scrollBy(0,500)");
    Thread.sleep(1000);
    favoritiesPage.clickToSize();
    Thread.sleep(1000);
    favoritiesPage.selectSize(0);
    js.executeScript("window.scrollBy(0,-500)");
    Thread.sleep(5000);
    favoritiesPage.delete();
    Thread.sleep(5000);
    favoritiesPage.search();
    Thread.sleep(5000);
    Assert.assertTrue(favoritiesPage.isSearchCheck(), "arama yapılamadı");
    Thread.sleep(700);
    favoritiesPage.giveUp();

  }

  @Test(priority = 3)
  @Description(" kolleksiyon oluşturma")

  public void Add_colletion() throws InterruptedException {
    order= new OrderProcessingPage(getDriver());
    favoritiesPage = new FavoritiesPage(getDriver());
    favoritiesPage.selectProduct(0);
    for (String winHandle : getDriver().getWindowHandles()) {
      getDriver().switchTo().window(winHandle);
    }
    Thread.sleep(700);
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("window.scrollBy(0,500)");
    Thread.sleep(2000);

    if (order.isAccept()) {
      order.accept();
    }
    Thread.sleep(2000);
    favoritiesPage.collection();
    Thread.sleep(1000);
    favoritiesPage.newcollection();
    Thread.sleep(1000);
    favoritiesPage.collectionName("Collection");
    Thread.sleep(1000);
    favoritiesPage.clickCreateCollection();
    Thread.sleep(1000);
    favoritiesPage.selectClose(0);


  }

  @Test(priority = 4)
  @Description("koleksiyonlarım sayfası ")

  public void my_collection() throws InterruptedException {
    favoritiesPage = new FavoritiesPage(getDriver());
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("window.scrollBy(0,500)");
    Thread.sleep(1000);
    favoritiesPage.clickFavoritiesButton();
    favoritiesPage.clickCollection();
    Thread.sleep(1000);
    getDriver().navigate().refresh();

    Thread.sleep(1000);
    js.executeScript("window.scrollBy(0,500)");
    Thread.sleep(1000);
    js.executeScript("window.scrollBy(0,-500)");
    favoritiesPage.selectCollection(0);

  }
}
