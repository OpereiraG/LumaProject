import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("TheBugHunters29@gmail.com");
        WebElement password = driver.findElement(By.id("userPassword"));
        password.sendKeys("Batch1529*");
        WebElement clickContinue = driver.findElement(By.name("login"));
        clickContinue.sendKeys(Keys.ENTER);

        List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

        WebElement desiredProduct = productList.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        desiredProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();


    }


}



