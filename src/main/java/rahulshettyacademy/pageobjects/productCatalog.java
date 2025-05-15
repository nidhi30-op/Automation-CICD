package rahulshettyacademy.pageobjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class productCatalog extends AbstractComponent {

	WebDriver driver;

	public productCatalog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// pagefactory takes 2 argument, one is driver & this for referring to this
		// driver so go & intialize the driver ad
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	

	By productby = By.cssSelector(".mb-3");
	By toast = By.id("toast-container");
	By productinvisble = By.cssSelector(".ng-animating");
	By toCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductList() {
		elementToLocated(productby);
		return products;
	}

	public cartPage getProducttoCart(String[] productsname) throws InterruptedException {

		for (String name : productsname) {

			WebElement prod = getProductList().stream()
					.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(name)).findFirst().orElse(null);

			if (prod != null) {
				System.out.println("Adding product to cart: " + name);
				prod.findElement(toCart).click();
				elementToLocated(toast);
				elementToInvisible(productinvisble);

			}

		}
		// returning new object of cartPage class
		return new cartPage(driver);
	}

}
