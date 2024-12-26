package webautomation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import webautomation.TestComponents.BaseTest;
import webautomation.TestComponents.ReTry;
import webautomation.pageobjects.CartPage;
import webautomation.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer = ReTry.class)
    public void loginErrorValidation()
    {
        landingPage.loginApplication("abc@gmail.com","abc1234");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("meghana25chittti@gmail.com","Chitti@1998");
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertFalse(cartPage.verifyProductDisplay("ZARA COAT 33"));

    }
}
