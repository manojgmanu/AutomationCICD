package manoj.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manoj.TestComponents.BaseTest;
import manoj.pageobject.CartPage;
import manoj.pageobject.CheckOutPage;
import manoj.pageobject.ConfirmationPage;
import manoj.pageobject.LandingPage;
import manoj.pageobject.ProductCatalouge;

public class StepDefination extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		landingPage = lunchApplication();
		// code
	}

	@Given("^Logged in with username (.+) and password (.+)$") // Regular Expression
	
	public void logged_in_username_and_password(String username, String password) {
		 
		
		productCatalouge = landingPage.loginApplication(username,password);

	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_cart(String ProductName) throws InterruptedException {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(ProductName);
	}

	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_and_submit_order(String ProductName) throws InterruptedException {

		CartPage cartPage = productCatalouge.goToCartPage();

		driver.manage().window().fullscreen();
		Boolean match = cartPage.verifyProductDisplay(ProductName);
		Assert.assertTrue(match);
	

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_ConfirmationPage(String string) {
		

		String confirmMessage = confirmationPage.getComfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
    	Assert.assertEquals(strArg1, landingPage.getErroMessage());
    	driver.close();
    }


}
