package Aranya_Pvt_Ltd.Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) throws InterruptedException {
        String myProdName = "IPHONE 13 PRO";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("anweshabachha@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Bsdk@2018");
        driver.findElement(By.id("login")).click();

        //Store all products in a list
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement myProd = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(myProdName))
                .findFirst().orElse(null);
        //using scope to click respective Add to cart button
        assert myProd != null;
        myProd.findElement(By.cssSelector(".w-10")).click();
        //explicitly waiting for the loader to disappear & toaster to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //Go to Cart
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        //Check my product is present in cart
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".items"));
        boolean match = cartItems.stream().anyMatch(item ->
                item.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(myProdName));
        Assert.assertTrue(match);
        //Click on checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results span"));
        WebElement cClick = countries.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
        assert cClick != null;
        cClick.click();
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        String success = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(success.equalsIgnoreCase("Thankyou for the order."));

        Thread.sleep(5000);
        driver.quit();
    }
}
