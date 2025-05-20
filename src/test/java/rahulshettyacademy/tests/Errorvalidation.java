package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.Checkoutpage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.Orderconfimation;
import rahulshettyacademy.pageobjects.productCatalog;
import rahulshettyacademy.testcomponent.Basetest;
import rahulshettyacademy.testcomponent.Retry;

public class Errorvalidation extends Basetest {

	@Test(groups = { "error-handling" },retryAnalyzer=Retry.class)
	public void incorrectPaswrd() throws IOException, InterruptedException {

		String[] productsname = { "ZARA COAT 3", "ADIDAS ORIGINAL" };
		// Move landing page login to new pageobject class

		productCatalog pro = obj.loginApplication("nidhitest30@gmail.com", "Nidhi@");

		String errorMessage = obj.getMeTheErrorMessage();

		Assert.assertEquals("Incorrect email or password.", errorMessage);
		//This is for jenkin testing

	}

	@Test
	public void submitOrderError() throws IOException, InterruptedException {

		String[] productsname = { "ZARA COAT 3", "ADIDAS ORIGINAL" };
		// Move landing page login to new pageobject class

		productCatalog pro = obj.loginApplication("nidhitest50@gmail.com", "Nidhi30@");

		List<WebElement> product = pro.getProductList();

		cartPage check = pro.getProducttoCart(productsname);
		pro.gotoCart();
		String[] products = { "ZARA OAT 33", "ADIDAS ORIGNALLL" };
		Boolean match = check.matchProductInCart(products);

		Assert.assertFalse(match);

	}
}
