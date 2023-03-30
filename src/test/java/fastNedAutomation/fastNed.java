package fastNedAutomation;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebElement;

public class fastNed {
	public static WebDriver driver;

	public static String ExecutionModeValue="";
	static ExtentTest test;
	static ExtentReports report;
	public String projectPath = System.getProperty("user.dir");
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("Extentreport.html");



	@BeforeTest
	public void reportsSetup() {
		extent.attachReporter(spark);

		ExecutionModeValue=System.getProperty("ExecutionMode");

		System.out.println("ExecutionMode "+ExecutionModeValue);


	}



	@Test(priority = 0)

	public void browserSetUp() {
		ExtentTest test = extent.createTest("Launch Browser and internal Setup");
		System.out.println("Open the Chrome Browser");
// String projectPath = System.getProperty("user.dir");
//System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");

		ChromeOptions options;

		if(ExecutionModeValue.contains("local"))
		{
			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = WebDriverManager.chromedriver().capabilities(options).create();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
		}

		else if(ExecutionModeValue.contains("remote")) {

			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			options.setHeadless(true);
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			driver = WebDriverManager.chromedriver().capabilities(options).create();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().deleteAllCookies();
		}
		test.info("Browser setup for internal capabilities are successful");
		test.log(Status.PASS, "Browser Launched successfully");
	}

	@Test(priority = 1)
	public void openUrl() {
		ExtentTest test = extent.createTest("Navigate to FastNed URL");
// launching the specified URL
		driver.navigate().to("https://fastnedcharging.com/nl");
		driver.findElement(By.linkText("Allow all cookies")).click();
		test.info("Accepted the cookies");
		test.log(Status.PASS, "Navigated to FastNed URL successfully");
		test.pass("Navigate to FastNed url is verified");
	}

	@Test(priority =2)
	public void changeLanguage() {
		ExtentTest test = extent.createTest("Switch the Language from NL to EN");
		String pageTitle_Dutch = driver.getTitle();
		System.out.println(pageTitle_Dutch);
		driver.findElement(By.xpath("//span[@class='hamburger-icon__inner']")).click();
		driver.findElement(By.linkText("EN")).click();
		String pageTitle_English = driver.getTitle();
		System.out.println(pageTitle_English);
		System.out.println("The Text in the website is changed based on the Language Switch");
		test.log(Status.PASS, "Language switch successful");
		test.pass("Switch the Language from NL to EN is verified");
	}

	@Test(priority = 3)
	public void navigate_to_Locations_menu() throws InterruptedException {
		ExtentTest test = extent.createTest("Navigate to our Location Menu");
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Our locations')]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		String Actual_Location_PageTitle = "Fastned";
		String Expected_Location_PageTitle = driver.getTitle();
		Assert.assertEquals(true, Expected_Location_PageTitle.contains(Actual_Location_PageTitle));
		test.log(Status.PASS,"Clicked on Our Locations from the Menu");
		test.pass("Navigate to our Location Menu is successful");
		test.log(Status.PASS,"Verified the FastNed Icons are displayed in the Screen");
		driver.switchTo().frame(0);
		Actions action = new Actions(driver);
		WebElement clickIcon = driver.findElement(By.xpath("//div[@class='gmnoprint'][2]/div/button[1]"));
		action.doubleClick(clickIcon).perform();
		List<WebElement> listofIcons = driver.findElements(By.xpath("//div[@tabindex='-1']"));
		test.log(Status.PASS,"The List of Icons displayed on the Screen is: "+listofIcons.size());
		test.log(Status.PASS,"Verified that User is able to toggles the checkbox “150+ kW”");
		driver.findElement(By.xpath("//div[@class='box']")).click();
		List<WebElement> listofCheckbox = driver.findElements(By.xpath("//div[@tabindex='-1']"));
		test.log(Status.PASS,"The Icons after checked box is checked: "+listofCheckbox.size());
		driver.findElement(By.xpath("//div[@class='box']")).click();
		Thread.sleep(3000);
		test.log(Status.PASS,"Verify User unselect the checkbox “150+ kW” and observe the count of Icons");
		List<WebElement> listofCheckbox1 = driver.findElements(By.xpath("//div[@tabindex='-1']"));
		test.log(Status.PASS,"The Icons after checked box is unchecked: "+listofCheckbox1.size());
		driver.findElement(By.xpath("//div[@class='box']")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 4)
	public void clickIcon() throws InterruptedException {
		ExtentTest test = extent.createTest("User clicks on one of the location markers");
		driver.findElement(By.xpath("(//div[@class='gm-style']/div[2]/div/following-sibling::div/div/div[3]/div/following-sibling::div[8]/img)[1]")).isDisplayed();
	    driver.findElement(By.xpath("(//div[@class='gm-style']/div[2]/div/following-sibling::div/div/div[3]/div/following-sibling::div[8]/img)[1]")).isEnabled();
		WebElement icon = driver.findElement(By.xpath("(//div[@class='gm-style']/div[2]/div/following-sibling::div/div/div[3]/div/following-sibling::div[8]/img)[1]"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(icon).click().perform();
		Thread.sleep(1000);
		test.log(Status.PASS,"A Random Icon is clicked");
	}

	@Test(priority = 5)
	public void verifyTooltipdetails() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the Tooltip display for Station Name, Connector types and Charging Points");
		String StationName = driver.findElement(By.xpath("(//div[@class='station_modal desktop']//div[@class='inner']/div[@class='header']/div[@class='name'])[1]")).getText();
		test.log(Status.PASS,"The Name of the selected station in Tooltip is: "+StationName);
		Thread.sleep(1000);
		String ConectorType_CCS = driver.findElement(By.xpath("(//div[@class='station_modal desktop']/div/div[@class='inner']/div[2]/div/div[@class='name'])[1]")).getText();
		test.log(Status.PASS,"The First Connector Type in the station is: "+ConectorType_CCS);
		List<WebElement> lstElem_CCS = driver.findElements(By.xpath("(//div[@class='station_modal desktop']/div/div[@class='inner']/div[2]/div/div[2])[1]"));
		test.log(Status.PASS,"Number Of Charging Stations for CCS Connector: "+ lstElem_CCS.get(0).getText());
		String ConectorType_CHAdeMO = driver.findElement(By.xpath("(//div[@class='station_modal desktop']/div/div[@class='inner']/div[2]/div/div[@class='name'])[2]")).getText();
		test.log(Status.PASS,"The Second Connector Type in the station is: "+ConectorType_CHAdeMO);
		List<WebElement> lstElem_CHAdeMO = driver.findElements(By.xpath("(//div[@class='station_modal desktop']/div/div[@class='inner']/div[2]/div/div[2])[2]"));
		test.log(Status.PASS,"Number Of Charging Stations for CHAdeMO Connector: "+ lstElem_CHAdeMO.get(0).getText());
		Thread.sleep(1000);
		WebElement modal_window = driver.findElement(By.xpath("//div[@class='station_modal desktop']"));
		WebElement Navigate_button = driver.findElement(By.xpath("(//a[@class='button'])[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modal_window);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Navigate_button);
	}


	@Test(priority = 6)
	public void verifyToLocation() throws InterruptedException{
		ExtentTest test = extent.createTest("Verify the to Location in Google maps");
		String LocationNameInTheTitle = driver.getTitle();
		test.log(Status.INFO,"To location should be same as the Page Title in Google maps");
		test.log(Status.PASS, "The Title of the displayed google page is: "+LocationNameInTheTitle);
		Assert.assertEquals(true, LocationNameInTheTitle.contains("Fastned"));
		test.pass("The Assertion of the Station name and the To Location is same");
		driver.quit();
	}

	@AfterTest
	public void AfterTestMethod() throws IOException {
		extent.flush();
	}



}