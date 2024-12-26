package webautomation.pageobjects;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
    @FindBy(css=".cartSection h3")
    private List<WebElement> cartProducts;

    // Assert.assertTrue(cartProducts.stream().anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName)));
    public boolean verifyProductDisplay(String productName)
    {
        return cartProducts.stream().anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));
    }

    //driver.findElement(By.cssSelector(".totalRow button")).click();
    @FindBy(css = ".totalRow button")
    private WebElement checkoutEle;
    public CheckOutPage goToCheckOut()
    {
        checkoutEle.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }
}
