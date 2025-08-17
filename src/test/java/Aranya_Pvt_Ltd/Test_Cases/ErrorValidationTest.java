package Aranya_Pvt_Ltd.Test_Cases;

import Aranya_Pvt_Ltd.Page_Object.PlaceOrder;
import Aranya_Pvt_Ltd.Page_Object.ProductCatalogue;
import Aranya_Pvt_Ltd.TestComponents.Base_Test;
import Aranya_Pvt_Ltd.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationTest extends Base_Test {

    @Test //(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void TestData() {
        landingPage.loginApp("anweshabachha@gmail.com","pop@2018");
        Assert.assertEquals(landingPage.errorValidation(), "Incorrect email or pasword.");
    }

    @Test
    public void productDisplayValidation() throws InterruptedException {
        String myProdName = "IPHONE 13 PRO";
        ProductCatalogue productCatalogue = landingPage.loginApp("anweshabachha@gmail.com", "Bsdk@2018");
        List<WebElement> products = productCatalogue.getProductList();
        PlaceOrder placeOrder = productCatalogue.addProductToCart(myProdName);
        Thread.sleep(2000);
        placeOrder.goToCart();
        boolean match = placeOrder.isMyProductPresent(myProdName);
        Assert.assertTrue(match);
    }
}
