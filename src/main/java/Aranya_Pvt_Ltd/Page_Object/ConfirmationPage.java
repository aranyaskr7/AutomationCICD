package Aranya_Pvt_Ltd.Page_Object;

import Aranya_Pvt_Ltd.Abstract_Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement successMessage;

    public String verifySuccessMessage(){
        String success = successMessage.getText();
        return success;
    }
}
