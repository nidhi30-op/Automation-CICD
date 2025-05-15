package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class Orderconfimation extends AbstractComponent {

	WebDriver driver;

	public Orderconfimation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public String orderConfirmation() {
		String Msg = confirmMessage.getText();
		return Msg;
	}

}
