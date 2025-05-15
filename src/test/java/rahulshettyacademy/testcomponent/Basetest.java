package rahulshettyacademy.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class Basetest {

	private static final boolean True = false;
	public WebDriver driver;
	public LandingPage obj;

	public WebDriver intializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C://Users//DELL//eclipse-workspace//Selenium//src//main//java//rahulshettyacademy//resources//globalvalue.properties");
		prop.load(fis); // it takes inputstream so first convert to inputstream using FileInputStream class
		
		
						// this is when we send browser from cmd 
    String browserName=   System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
       
//       prop.getProperty("browser");
		if (browserName.contains("Chrome")) {
			ChromeOptions option=new ChromeOptions();
		
			if(browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));// help you to run in full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C://Users//DELL//Documents//gecko//geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", "C://Users//DELL//Documents//edge//msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = intializeDriver();
		obj = new LandingPage(driver);
		obj.goToLandingPage();
		return obj;
	}

	public List<HashMap<String, String>> getJsonDataToHashmap(String FilePath) throws IOException {
		// Read the file content into a string
		String jsonData = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// Convert JSON string into List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}
	
	@AfterMethod(alwaysRun = true)
	public void orderSucess() {
		driver.close();
	}

}
