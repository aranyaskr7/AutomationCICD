package Aranya_Pvt_Ltd.Page_Object;

import Aranya_Pvt_Ltd.Abstract_Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    //Constructor
    public LandingPage(WebDriver driver){
        //Initialization
        super(driver); //super is Calling the Parent Class (AbstractComponent) Constructor
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordElement;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    WebElement errorToaster;

    public void GoTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginApp(String email, String password){
        userEmail.sendKeys(email);
        passwordElement.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String errorValidation(){
        waitForWebElementToAppear(errorToaster);
        return errorToaster.getText();
    }
}
