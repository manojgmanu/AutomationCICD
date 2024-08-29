package manoj.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import manoj.TestComponents.BaseTest;
import manoj.TestComponents.Retry;
import manoj.pageobject.CartPage;
import manoj.pageobject.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LogInErrorValidation() throws IOException, InterruptedException {

		
		landingPage.loginApplication("manojg1413@gmail.com", "Manoj");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErroMessage());

	}
	@Test
	public  void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalouge productCatalouge = landingPage.loginApplication("manojg2218@gmail.com", "Manoj@18");
		
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
		CartPage cartPage = productCatalouge.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
