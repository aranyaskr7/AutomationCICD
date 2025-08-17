package Aranya_Pvt_Ltd.Page_Object;

import Aranya_Pvt_Ltd.Abstract_Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    //Constructor
    public ProductCatalogue(WebDriver driver){
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement animationElement;

    By productsBY = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".w-10");
    By toasterMessage = By.id("toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBY);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement myProd = getProductList().stream().filter(product->
                        product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        return myProd;
    }

    public PlaceOrder addProductToCart(String productName){
        WebElement myProd = getProductByName(productName);
        myProd.findElement(addToCart).click();
        waitForElementToAppear(toasterMessage);
        waitForElementToDisappear(animationElement);
        PlaceOrder placeOrder = new PlaceOrder(driver);
        return placeOrder;
    }
}
