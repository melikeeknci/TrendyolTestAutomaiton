package tests;

import Pages.*;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class CouponTest extends BaseTest {
    HomePage homePage;
    LoginPage login;
    OrderProcessingPage orderProcessingPage;
    CouponPage couponPage;


    @Test(priority = 1)
    @Description("kupon seçimi ")
    public void Filter_Coupon() throws InterruptedException {
        couponPage = new CouponPage(getDriver());
        orderProcessingPage = new OrderProcessingPage(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        login = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        login.clickLogin();
        Thread.sleep(2000);
        login.Login(configProp.getProperty("userName1"), configProp.getProperty("password"));
        login.clickLoginButton();
        Thread.sleep(2000);
        homePage.search("çanta");
        Thread.sleep(2000);
        homePage.clickIcon();
        Thread.sleep(2000);
        couponPage.clickCoupon();
        Thread.sleep(2000);
        boolean productSelected = false;
        for(int i=0;i<homePage.getAllProduct().size();i+=4) {
            homePage.selectProduct(i);
            String originalWindow = getDriver().getWindowHandle();
            for (String winHandle : getDriver().getWindowHandles()) {
                getDriver().switchTo().window(winHandle);
            }
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(2000);
            if (couponPage.isCouponApplyAvaliable()) {
                Thread.sleep(2000);
                if (orderProcessingPage.isAccept()) {
                    orderProcessingPage.accept();
                }
                Thread.sleep(2000);
                couponPage.selectCoupon(0);
                Thread.sleep(2000);
                if (couponPage.isCouponAvailable()) {
                    System.out.println("Bu üründeki kupon geçerli");
                    Thread.sleep(2000);
                    couponPage.couponClose();
                    Thread.sleep(2000);
                    orderProcessingPage.clickAddToBasket();
                    Thread.sleep(1000);
                    orderProcessingPage.basket();
                    Thread.sleep(2000);
                    int numberOfProducts = couponPage.getAllProductPrice().size();
                    for (int j = 0; j < numberOfProducts; j++) {
                        Thread.sleep(2000);
                        Double lowerLimit = couponPage.selectLowerlimit();
                        Thread.sleep(2000);
                        Double productPrice = couponPage.selectProductPrice(j);
                        Thread.sleep(2000);
                        if (lowerLimit <= productPrice) {
                            couponPage.selectApplyCoupon();
                            Double discountPrice = couponPage.getDiscountPrice();
                            Double totalPrice = couponPage.getTotalPrice();
                            if ((Math.abs(productPrice - discountPrice) == totalPrice)) {
                                System.out.println("İndirim doğru");
                                Thread.sleep(2000);
                                couponPage.clickOrder();
                                couponPage.confirmAdress("melike","ekinci","mevlana mah","ev");
                                couponPage.x();
                                couponPage.ComboBox();
                                productSelected = true;
                            } else {
                                System.out.println("İndirim hatalı");
                            }
                            break;
                        } else {
                            System.out.println("kupon uygulanamdı, Limit uyuşmazlığı");
                            Thread.sleep(2000);
                            getDriver().switchTo().window(originalWindow);
                            Thread.sleep(2000);
                            break;
                        }

                    }
                } else if (couponPage.isCouponNotAvailable()) {
                    Thread.sleep(2000);
                    System.out.println("Bu üründeki kuponlar alınmış");
                    couponPage.couponClose();
                    Thread.sleep(2000);
                    getDriver().switchTo().window(originalWindow);
                    Thread.sleep(2000);
                }
                else {
                    System.out.println("Günlük Kupon Limitin Doldu.");
                    Thread.sleep(2000);
                    couponPage.couponClose();
                    productSelected = true;
                    break;
                }
            }else{
                System.out.println("bu üründe kupon tanımlı değildir.");
                Thread.sleep(2000);
                getDriver().switchTo().window(originalWindow);
                Thread.sleep(2000);
            }
        }

        if (!productSelected) {
            System.out.println("Renk seçeneği olan bir ürün bulunamadı.");

        }

    }
}



