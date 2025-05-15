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

public class orderPage extends AbstractComponent {

	WebDriver driver;

	public orderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	@FindBy(css = ".ng-star-inserted td:nth-child(3)")
	List<WebElement> orderProductList;

	public Boolean matchProductInOrderpage(String[] productsname) {

		List productList = Arrays.asList(productsname);
		Boolean match = orderProductList.stream().anyMatch(s -> productList.contains(s.getText()));
		return match;
	}

}
