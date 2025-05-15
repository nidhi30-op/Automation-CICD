package rahulshettyacademy.tests;

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

import rahulshettyacademy.pageobjects.LandingPage;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String[] productsname = { "ZARA COAT 3", "ADIDAS ORIGINAL" };

		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("nidhitest30@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nidhi30@");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		for (String name : productsname) {
			WebElement prod = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(name))
					.findFirst().orElse(null);

			if (prod != null) {
				System.out.println("Adding product to cart: " + name);
				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
			}

		}

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProductList = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		List productList = Arrays.asList(productsname);
		Boolean match = cartProductList.stream().anyMatch(s -> productList.contains(s.getText()));

		Assert.assertTrue(match);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		WebElement checkoutButton = driver.findElement(By.cssSelector(".totalRow button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);

		driver.findElement(By.cssSelector(".form-group input")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();

		driver.findElement(By.cssSelector(".actions a")).click();
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));

	}

}
