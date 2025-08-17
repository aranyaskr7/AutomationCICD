package Aranya_Pvt_Ltd.Page_Object;

import Aranya_Pvt_Ltd.Abstract_Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tbody/tr/td[2]")
    List<WebElement> orders;

    public boolean isMyOrderPresent(String productName){
        boolean match = orders.stream().anyMatch(item ->
                item.getText().equalsIgnoreCase(productName));
        return match;
    }
}

