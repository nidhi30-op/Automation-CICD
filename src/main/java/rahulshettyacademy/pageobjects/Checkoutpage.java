package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class Checkoutpage extends AbstractComponent {

	WebDriver driver;

	public Checkoutpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	@FindBy(css = ".form-group input")
	WebElement country;

	@FindBy(css = ".ta-results button:last-of-type")
	WebElement name;

	@FindBy(css = ".actions a")
	WebElement orderButton;

	By countrylist = By.cssSelector(".ta-results");

	public void enterOrderDetail(String countryname) {
		country.sendKeys(countryname);

		elementToLocated(countrylist);
		name.click();
	}

	public Orderconfimation placeOrder() {
		orderButton.click();
		Orderconfimation order = new Orderconfimation(driver);
		return order;
	}

}
