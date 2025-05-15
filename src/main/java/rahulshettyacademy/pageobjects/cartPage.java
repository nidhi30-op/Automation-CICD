package rahulshettyacademy.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class cartPage extends AbstractComponent {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> cartProductList;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	By animation = By.cssSelector(".ng-animating");

	public Boolean matchProductInCart(String[] productsname) {

		List productList = Arrays.asList(productsname);
		Boolean match = cartProductList.stream().anyMatch(s -> productList.contains(s.getText()));
		return match;
	}

	public Checkoutpage goToCheckoutPage() throws InterruptedException {
		elementToInvisible(animation);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
		elementTobeClickable(checkoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);

		return new Checkoutpage(driver);
	}

}
