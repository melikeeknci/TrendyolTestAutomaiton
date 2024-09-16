package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public Properties configProp;
    public Properties errorProp;


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @BeforeTest
    public  void setUpBeforeClass() throws Exception {
        readConfigFile();
        readErrorConfigFile();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        if(options.toString().contains("--disable-notifications")){
            driver.set(new ChromeDriver(options));
            getDriver().get("https://www.trendyol.com/");
            getDriver().manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("Group-38")));
            element.click();
            WebElement alert = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='onetrust-accept-btn-handler'][1]")));
            alert.click();
        }else {
            throw new Exception("Bildirimler devre dışı bırakılamadı. Ayarlar kontrol edilemiyor.");
        }


    }
    public void readConfigFile() {
        configProp = new Properties();

        try (FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/Configuration/config.properties");
             InputStreamReader reader = new InputStreamReader(fs, StandardCharsets.UTF_8)) {
            configProp.load(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to load configuration file: " + e.getMessage());
        }
    }

    public  void  readErrorConfigFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/Configuration/error.properties";
        errorProp = new Properties();
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            errorProp.load((new java.io.StringReader(content)));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        /*
        errorProp = new Properties();
        try (FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/Configuration/error.properties");
             String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

             InputStreamReader reader = new InputStreamReader(fs, StandardCharsets.UTF_8)) {
            errorProp.load((new java.io.StringReader(content));
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to load configuration file: " + e.getMessage());
        }
    }

         */
    public WebDriver getDriver() {
        return driver.get();
    }

    public String getProperty(String key) {
        return configProp != null ? configProp.getProperty(key) : null;
    }
    /*
    public String getErrorProperty(String key) {
        return errorProp != null ? errorProp.getProperty(key) : null;
    }

     */
/*
    @AfterTest
    public void tearDown() {
        getDriver().quit();
        driver.remove();
   }

 */
}