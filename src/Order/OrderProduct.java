package Order;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderProduct{
	
  private WebDriver driver;
  
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  
  public void setUp() throws Exception {
	  
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	
    driver = new ChromeDriver();
    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
  }

  @Test
  
  public void testOrderProductBySearchChrome() throws Exception {
	  
    driver.get("https://www.bentchair.com/");
    
    driver.manage().window().maximize();
    
    WebElement clickLogin = driver.findElement(By.className("quick-login"));
    clickLogin.click();
    
    WebElement enterEmail = driver.findElement(By.id("input-email"));
    enterEmail.sendKeys("sanjaykauldhar123@gmail.com");
    
    WebElement enterPass = driver.findElement(By.id("input-password"));
    enterPass.sendKeys("sanjay");
    
    WebElement clickSubmit = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-lg btn-block']"));
    clickSubmit.submit();
    
    //PageObjects.Home_Page.LOGO(driver).click();
    
    WebElement searchProduct = driver.findElement(By.id("filter_name"));
    searchProduct.sendKeys("Face Coffee Mug (Set of 2)");
    
    WebElement clickSearch = driver.findElement(By.className("button-search"));
    clickSearch.click();
    
    WebElement clickProduct = driver.findElement(By.xpath("//img[@src='https://img.bentchair.com/cache/catalog/Products/product_images/HFC200300004_1-600x800.jpg']"));
    clickProduct.click();
    
    //New Tab Start
    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
    //driver.close();
    //driver.switchTo().window(tabs2.get(0));
    //New Tab End
    WebElement ADD_TO_CART = driver.findElement(By.id("Dbutton-cart"));
    ADD_TO_CART.click();
    
    WebElement CART = driver.findElement(By.xpath("//img[contains(@src,'https://img.bentchair.com/image/icon/cart.png')]"));
    CART.click();
    
    WebElement viewCart = driver.findElement(By.xpath("(//a[contains(text(),' View Cart')])"));
    JavascriptExecutor executor7 = (JavascriptExecutor) driver;
    executor7.executeScript("arguments[0].click();", viewCart);

    WebElement checkout = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", checkout);
    
    WebElement checkoutContinue = driver.findElement(By.xpath("//button[@id='button-shipping-address']"));
    JavascriptExecutor executor1 = (JavascriptExecutor) driver;
    executor1.executeScript("arguments[0].click();", checkoutContinue);
    
    WebElement COD = driver.findElement(By.xpath("(//input[@name='payment_method'])[4]"));
    COD.click();
    
    WebElement TERM = driver.findElement(By.name("agree"));
    JavascriptExecutor executor3 = (JavascriptExecutor) driver;
    executor3.executeScript("arguments[0].click();", TERM);
    
    WebElement finalPayment= driver.findElement(By.xpath("//button[@id='button-payment-method']"));
    JavascriptExecutor executor4 = (JavascriptExecutor) driver;
    executor4.executeScript("arguments[0].click();", finalPayment);
  
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.titleContains("Success"));
    
    Actions action = new Actions(driver);
    WebElement we = driver.findElement(By.id("my_account"));
    action.moveToElement(we).build().perform();
    
    WebElement LOGOUT = driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li[5]//a"));
    LOGOUT.click();
  
  }

  @AfterClass(alwaysRun = true)
  public void logOut() throws Exception {
	driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
