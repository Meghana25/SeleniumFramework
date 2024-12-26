package webautomation.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webautomation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //  a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country; //Encapsulation

    //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;

    //  driver.findElement(By.cssSelector(".action__submit")).click();
    @FindBy(css=".action__submit")
    private WebElement submit;

    private By results = By.id(".ta-results");
    public void selectCountry(String countryToSelect) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryToSelect).build().perform();
        //waitForElementToAppear(results);
        Thread.sleep(5000);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder()
    {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}
