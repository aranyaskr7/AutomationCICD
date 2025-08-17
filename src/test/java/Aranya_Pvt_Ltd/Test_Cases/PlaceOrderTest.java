package Aranya_Pvt_Ltd.Test_Cases;

import Aranya_Pvt_Ltd.Page_Object.ConfirmationPage;
import Aranya_Pvt_Ltd.Page_Object.OrderPage;
import Aranya_Pvt_Ltd.Page_Object.PlaceOrder;
import Aranya_Pvt_Ltd.Page_Object.ProductCatalogue;
import Aranya_Pvt_Ltd.TestComponents.Base_Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderTest extends Base_Test {
    String myProdName = "IPHONE 13 PRO";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void TestData(HashMap<String,String> input) throws InterruptedException {
        //creating object of ProductCatalogue class inside landing Page > loginApp & sendKeys for login
        ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"),input.get("password"));
        //Store all products in a list using object of ProductCatalogue class
        List<WebElement> products = productCatalogue.getProductList();
        //creating object of PlaceOrder class inside ProductCatalogue > addProductToCart & passing String to check
        PlaceOrder placeOrder = productCatalogue.addProductToCart(input.get("myProdName"));
        //Go to Cart
        Thread.sleep(2000);
        placeOrder.goToCart();
        //Check my product is present in cart
        boolean match = placeOrder.isMyProductPresent(input.get("myProdName"));
        Assert.assertTrue(match);
        //Click on checkout
        ConfirmationPage confirmationPage = placeOrder.checkOut("ind");
        //check success message on confirmation page
        String success = confirmationPage.verifySuccessMessage();
        Assert.assertTrue(success.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"TestData"})
    public void OrderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginApp("anweshabachha@gmail.com","Bsdk@2018");
        OrderPage orderPage = productCatalogue.goToMyOrders();
        Assert.assertTrue(orderPage.isMyOrderPresent(myProdName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/Aranya_Pvt_Ltd/test_data/SubmitOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}

//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","anweshabachha@gmail.com");
//        map.put("password","Bsdk@2018");
//        map.put("myProdName","IPHONE 13 PRO");
//
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","rahulshetty@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("myProdName","ZARA COAT 3");