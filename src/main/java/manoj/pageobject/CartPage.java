package manoj.pageobject;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import manoj.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartproducts;
	
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckOutPage goToCheckOut() {
	 
		checkoutEle.click();
		return new CheckOutPage(driver);
		
	}
	
}
