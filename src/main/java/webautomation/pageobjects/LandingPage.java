package webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webautomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
   // driver.findElement(By.id("userEmail")).sendKeys("meghana25chittti@gmail.com");
    //PageFactory
    @FindBy(id="userEmail")
    private WebElement email;

    @FindBy(id="userPassword")
    private WebElement password;

    @FindBy(id="login")
    private WebElement submit;

    @FindBy(css="[class*='flyInOut")
    private WebElement loginError;

    public ProductCatalogue loginApplication(String emailID,String pass)
    {
        email.sendKeys(emailID);
        password.sendKeys(pass);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(loginError);
        return loginError.getText();
    }

    public void goToWebsite()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }



}
