package Cucumber.StepDefinition;

import Aranya_Pvt_Ltd.Page_Object.ConfirmationPage;
import Aranya_Pvt_Ltd.Page_Object.LandingPage;
import Aranya_Pvt_Ltd.Page_Object.PlaceOrder;
import Aranya_Pvt_Ltd.Page_Object.ProductCatalogue;
import Aranya_Pvt_Ltd.TestComponents.Base_Test;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.io.IOException;

public class StepDefinitionImplementation extends Base_Test {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public PlaceOrder placeOrder;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
       landingPage = getLandingPage();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        productCatalogue = landingPage.loginApp(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName){
        placeOrder = productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) & submit the order$")
    public void Checkout_submit_the_order(String productName) throws InterruptedException {
        Thread.sleep(2000);
        placeOrder.goToCart();
        boolean match = placeOrder.isMyProductPresent(productName);
        Assert.assertTrue(match);
        confirmationPage = placeOrder.checkOut("ind");
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_displayed_on_ConfirmationPage(String string){
        String success = confirmationPage.verifySuccessMessage();
        Assert.assertTrue(success.equalsIgnoreCase(string));
    }
}
