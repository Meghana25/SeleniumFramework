package webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webautomation.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    //    PageFactory
    @FindBy(css=".mb-3")
    private List<WebElement> products;

    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    private By productBy = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".mb-3 button:last-of-type");
    private By toastMessgae = By.cssSelector("#toast-container");
    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productBy);
        return products;
    }

    // WebElement prod = products.stream().filter(product->
    //                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);

    public WebElement getProductByName(String productName)
    {
        return getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }
    // if (prod != null) {
    //            prod.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
    //        }
    //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    //        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    @FindBy(css=".ng-animating")
    private WebElement spinner;
    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        if (prod != null) {
            prod.findElement(addToCart).click();
            waitForElementToAppear(toastMessgae);
            waitForElementToDisappear(spinner);
        }
    }
}
