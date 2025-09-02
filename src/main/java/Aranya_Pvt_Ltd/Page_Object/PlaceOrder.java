package Aranya_Pvt_Ltd.Page_Object;

import Aranya_Pvt_Ltd.Abstract_Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlaceOrder extends AbstractComponent {
    WebDriver driver;
    //Constructor
    public PlaceOrder(WebDriver driver){
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".items")
    List<WebElement> cartItems;

    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;

    @FindBy(css = ".form-group input")
    WebElement countryInput;

    @FindBy(css = ".ta-results span ")
    List<WebElement> countryList;

    @FindBy(css = ".btnn.action__submit.ng-star-inserted")
    WebElement placeOrder;


    By cartButton = By.xpath("//button[@routerlink='/dashboard/cart']");
    By itemName = By.cssSelector("h3");

    public void goToCart(){
        waitForElementToBeClickable(cartButton);
        driver.findElement(cartButton).click();
    }

    public boolean isMyProductPresent(String productName){
        boolean match = cartItems.stream().anyMatch(item ->
                item.findElement(itemName).getText().equalsIgnoreCase(productName));
        return match;
    }

    public ConfirmationPage checkOut(String country){
        checkOutButton.click();
        countryInput.sendKeys(country);
        WebElement selectCountry = countryList.stream().filter(meraDesh -> meraDesh.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
        assert selectCountry != null;
        selectCountry.click();
        placeOrder.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
//Demo purpose to check git push

}
