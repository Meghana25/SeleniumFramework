package webautomation.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webautomation.pageobjects.CartPage;
import webautomation.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementToAppear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement Element) throws InterruptedException {
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//        wait.until(ExpectedConditions.invisibilityOf(Element));
    }
    //  driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;
    public CartPage goToCartPage() throws InterruptedException {
        Thread.sleep(1000);
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;
    public OrderPage goToOrderPage()
    {
        orderHeader.click();;
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
