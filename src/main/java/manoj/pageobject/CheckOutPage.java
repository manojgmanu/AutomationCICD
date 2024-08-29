package manoj.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import manoj.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath  = "//a[normalize-space()='Place Order']")
	WebElement submit;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath ="(//button[@type='button'])[2]")
	WebElement selectCountry;
	
	
	public void selectCountry(String countryName) throws InterruptedException {
		
		Actions a = new Actions(driver);
		a.sendKeys(Country,countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		
		selectCountry.click();
	}
	public ConfirmationPage submitOrder ()
	{
		submit.click();
		 return new ConfirmationPage(driver);
	}
	
}