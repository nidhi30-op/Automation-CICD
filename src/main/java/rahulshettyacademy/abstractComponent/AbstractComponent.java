package rahulshettyacademy.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.orderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, driver);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement myOrder;

	public void elementToLocated(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
		// this is BY locator

	}

	public void elementToInvisible(By invisible) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//	
//    wait.until(ExpectedConditions.invisibilityOfElementLocated(invisible));

	}

	public void elementTobeClickable(WebElement checkoutButton) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
	}

	public void WebelementTobeVisible(WebElement error) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(error));
	}

	public void gotoCart() {
		cart.click();
	}

	public orderPage goToOrder() {
		myOrder.click();
		return new orderPage(driver);
	}
}
