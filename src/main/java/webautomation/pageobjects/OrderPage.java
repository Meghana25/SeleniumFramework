package webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {
    WebDriver driver;
    public OrderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> orders;
    public boolean verifyOrderDisplay(String productName)
    {
        return orders.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
    }
}
