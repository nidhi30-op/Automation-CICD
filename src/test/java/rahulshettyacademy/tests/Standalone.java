package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.orderPage;
import rahulshettyacademy.pageobjects.Checkoutpage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.Orderconfimation;
import rahulshettyacademy.pageobjects.productCatalog;
import rahulshettyacademy.testcomponent.Basetest;

public class Standalone extends Basetest {
	String[] productsname = { "ZARA COAT 3", "ADIDAS ORIGINAL" };
	String[] productsname1 = { "ZARA COAT 3", "IPHONE 13 PRO" };

	@Test(dataProvider = "getData", groups = { "data-provider" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// Move landing page login to new pageobject class


		productCatalog pro = obj.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> product = pro.getProductList();

		cartPage check = pro.getProducttoCart(productsname);
		pro.gotoCart();
		Boolean match = check.matchProductInCart(productsname);

		Assert.assertTrue(match);

		Checkoutpage checkout = check.goToCheckoutPage();

		checkout.enterOrderDetail("India");
		Orderconfimation order = checkout.placeOrder();

		String confirmationMsg = order.orderConfirmation();

		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));

	}
	// Verify that place order is correctly showing in order confirmation page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderVerification() {

		productCatalog pro = obj.loginApplication("nidhitest30@gmail.com", "Nidhi30@");
		orderPage order = pro.goToOrder();

		Boolean match1 = order.matchProductInOrderpage(productsname);
		Assert.assertTrue(match1);
	}

// for less data we an send the data like this.
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return new Object[][] { { "nidhitest30@gmail.com", "Nidhi30@", productsname
	 * }, { "nidhitest50@gmail.com", "Nidhi30@", productsname1 } }; }
	 */
	// for more data, data provider return hashmap too
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToHashmap(
				"C://Users//DELL//eclipse-workspace//Selenium//src//test//java//rahulshettyacademy//data//data.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	
}

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "nidhitest30@gmail.com");
//map.put("password", "Nidhi30@");

//HashMap<String,String> map1 = new HashMap<String, String>();
//map1.put("email", "nidhitest50@gmail.com");
//map1.put("password", "Nidhi30@");
