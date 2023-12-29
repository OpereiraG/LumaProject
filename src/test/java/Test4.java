import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Test4 {
    static WebDriver driver;



    @Test

    public void AddItemsToCart(){
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("TheBugHunters29@gmail.com");
        WebElement password = driver.findElement(By.id("userPassword"));
        password.sendKeys("Batch1529*");
        WebElement clickContinue = driver.findElement(By.name("login"));
        clickContinue.sendKeys(Keys.ENTER);

        List <WebElement> products = driver.findElements(By.cssSelector(".row .mb-3"));
        List <WebElement> addCartButton = driver.findElements(By.xpath("//*[contains(text(),' Add To Cart')]"));

        for (int i = 0; i <products.size() ; i++) {
            String desiredProduct = products.get(i).getText();
            if (desiredProduct.contains("iphone 13 pro")){
                addCartButton.get(i).click();
            }
        }
    }
}
