import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test1 {

    String website = "https://magento.softwaretestingboard.com/";
    static WebDriver driver;

    @BeforeMethod

    public void Login() {
        driver = new ChromeDriver();
        driver.get(website);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //driver.manage().deleteAllCookies();
        WebElement clickSignIn = driver.findElement(By.xpath("//a[contains(text(),'Sign In')][1]"));
        clickSignIn.click();
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("TheBugHunters29@gmail.com");
        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("Batch1529*ll");
        WebElement clickContinue = driver.findElement(By.name("send"));
        clickContinue.sendKeys(Keys.ENTER);
    }


    @Test
    public void endToEndTesting() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,1500)", "");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        List<WebElement> products = driver.findElements(By.cssSelector(".widget-product-grid .product-item"));
        List<WebElement> sizeXl = driver.findElements(By.cssSelector("swatch-option text"));

        WebElement addCart = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        Actions actions = new Actions(driver);

        for (int i = 1; i <= products.size(); i++) {
            actions.moveToElement(driver.findElement(By.cssSelector(".widget-product-grid .product-item"))).perform();
            String desiredProduct = products.get(i).getText();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Add to Cart']")));
            if (desiredProduct.contains("Argus All-Weather Tank")) {
                products.get(i).click();
                break;
            }

        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-168")));
        WebElement selectSize = driver.findElement(By.id("option-label-size-143-item-168"));
        selectSize.click();
        WebElement selectColor = driver.findElement(By.xpath("//div[@option-label='Gray']"));
        selectColor.click();
        WebElement clickAddToCart = driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]"));
        clickAddToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'You added Argus All-Weather Tank to your shopping cart.')]")));
        String confirmation = driver.findElement(By.xpath("//span[contains(text(),'You added Argus All-Weather Tank to your')]")).getText();
        Assert.assertTrue(true, confirmation);


    }

    @AfterMethod

    public void closeBrowser() {
        //driver.close();


    }


}

