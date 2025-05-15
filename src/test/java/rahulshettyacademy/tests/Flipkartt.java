package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Flipkartt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.flipkart.com/");

		driver.findElement(By.cssSelector("input[class='Pke_EE']")).sendKeys("mobile" + Keys.ENTER);

		List<WebElement> productList = driver.findElements(By.cssSelector(".cPHDOP ._75nlfW"));

		WebElement desiredProduct = productList.stream()
				.filter(product -> product.findElement(By.cssSelector(".KzDlHZ")).getText()
						.equals("Vox V19 New Keypad Mobile Phone with 1000mAh Battery, FM Radio, Mp3 Player"))
				.findFirst().orElse(null);

		desiredProduct.findElement(By.cssSelector(".KzDlHZ ")).click();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();

		String parentid = it.next();
		String childid = it.next();

		driver.switchTo().window(childid);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='_1ri+WN lwANdH'] ul li:nth-child(1)")));
		driver.findElement(By.cssSelector("div[class='_1ri+WN lwANdH'] ul li:nth-child(1)")).click();

		if (driver.findElement(By.xpath("//div[@class='gE4Hlh']")).getText().contains("Vox V19 New Keypad")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".zA2EfJ")));
			driver.findElement(By.cssSelector(".zA2EfJ")).click();
		}

		List<WebElement> addressList = driver.findElements(By.cssSelector(".VKzPTL"));
		Boolean match = addressList.stream()
				.anyMatch(a -> a.findElement(By.xpath("//p/span[1]")).getText().contains("Ujjwal"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("[randomly id='CNTCTC45F018273CE40F3A0A28D9A6'] .qsHXPi")).click();

	}
}
