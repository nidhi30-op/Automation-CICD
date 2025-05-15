package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	// Page factory design

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement Login;

	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;

	public productCatalog loginApplication(String Email, String Password) {

		userEmail.sendKeys(Email);
		password.sendKeys(Password);
		Login.click();

		return new productCatalog(driver);
	}

	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client/");

	}

	public String getMeTheErrorMessage() {
		WebelementTobeVisible(errorMsg);
		String errorMessage = errorMsg.getText();

		return errorMessage;
	}

}
