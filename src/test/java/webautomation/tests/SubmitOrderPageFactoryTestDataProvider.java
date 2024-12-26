package webautomation.tests;


import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webautomation.TestComponents.BaseTest;
import webautomation.pageobjects.*;

import java.util.List;

public class SubmitOrderPageFactoryTestDataProvider extends BaseTest {
    String productName = "BANARSI SAREE";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrderTest(String email,String password,String productName) throws InterruptedException {

        ProductCatalogue productCatalogue  = landingPage.loginApplication(email,password);
        //------------PRODUCT CATALOGUE PAGE------------------------------------//
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage  = productCatalogue.goToCartPage();
        //----------------CART PAGE---------------------//
        Assert.assertTrue(cartPage.verifyProductDisplay(productName));
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        //--------------CheckOutPage-------------------//
        String countryToSelect = "india";
        checkOutPage.selectCountry(countryToSelect);
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        Assert.assertTrue(confirmationPage.getConfirmationMessgae().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods= {"submitOrderTest"})
    public void OrderHistoryTest()
    {
        //"ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("meghana25chittti@gmail.com","Chitti@1998");
        OrderPage ordersPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

    }

    //Using DataProvider - it will messy if we pass N number of parameters conitnuously ,
    // (Better way to handle this can be achieved used HashMap with keyvalue pairs
    @DataProvider
    public Object[][] getData()
    {
        return new Object[][] {{"meghana25chittti@gmail.com","Chitti@1998","ZARA COAT 3"},{"mchitti@gmail.com","Abcd@1998","ADIDAS ORIGINAL"}};
    }

    //Using HashMap
//    @DataProvider
//    public Object[][] getData()
//    {
//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","meghana25chittti@gmail.com");
//        map.put("password","Chitti@1998");
//        map.put("product","ZARA COAT 3");
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","mchitti@gmail.com");
//        map1.put("password","Abcd@1998");
//        map1.put("product","ADIDAS ORIGINAL");
//        return new Object[][]{{map},{map1}};
//    }

}
