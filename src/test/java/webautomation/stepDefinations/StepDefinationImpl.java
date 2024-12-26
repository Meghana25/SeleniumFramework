package webautomation.stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webautomation.TestComponents.BaseTest;
import webautomation.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinationImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^I logged in with username(.+) and password (.+)$")
    public void I_logged_in_with_username_and_password(String username,String password)
    {
         productCatalogue  = landingPage.loginApplication(username,password);
    }
    @When("^I add product(.+) to Cart$")
    public void I_add_product_to_Cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @And("^Checkout(.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) throws InterruptedException {
        cartPage  = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(productName));
        checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("india");
        confirmationPage = checkOutPage.submitOrder();
    }
    @Then("verify {string} message is displayed on ConfirmationPage")
    public void verify_message_is_deplayed_on_ConfirmationPage(String confirmMsg)
    {
        Assert.assertTrue(confirmationPage.getConfirmationMessgae().equalsIgnoreCase(confirmMsg));
        driver.close();
    }
    @Then("verify {string} message is displayed")
    public void  verify_message_is_displayed(String errorMsg)
    {
        Assert.assertEquals(errorMsg,landingPage.getErrorMessage());
    }


}
