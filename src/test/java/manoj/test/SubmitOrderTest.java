package manoj.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import manoj.TestComponents.BaseTest;
import manoj.pageobject.CartPage;
import manoj.pageobject.CheckOutPage;
import manoj.pageobject.ConfirmationPage;
import manoj.pageobject.OrderPage;
import manoj.pageobject.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalouge.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		driver.manage().window().fullscreen();

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getComfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confirmMessage);

//		orders
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {

		ProductCatalouge productCatalouge = landingPage.loginApplication("manojg1413@gmail.com", "Manoj@18");
		OrderPage orderPage = productCatalouge.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	// Extent Reports

	@DataProvider
	public Object[][] getData() throws IOException {

//		Hashmap

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "manojg1413@gmail.com");
//		map.put("password", "Manoj@18");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "manojg2218@gmail.com");
//		map1.put("password", "Manoj@18");
//		map1.put("product", "ADIDAS ORIGINAL");

		List<HashMap<String, String>> data = getjsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\manoj\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
