package scripts;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import pom.LoginPage;

public class FirstAppiumTest {

	private static AndroidDriver driver;
	String curDir = System.getProperty("user.dir");
	File file = null;
	LoginPage loginPage = null;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		file = new File(curDir + "//Apps//app-food2go-release.apk");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		// This capability will open the Chrome browser instead of Native app.
		dc.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

	}

	@Test
	public void loginScreen() throws InterruptedException, IOException {

		driver.navigate().to("https://www.google.co.in");
		Thread.sleep(3000);
		
		
		TouchActions actions = new TouchActions(driver);
		actions.clickAndHold(driver.findElement(By.id("D"))).moveToElement(driver.findElement(By.id("S"))).build().perform();
		Dimension size = driver.manage().window().getSize();
		
		TouchAction action = new TouchAction<>(driver);
		
		//action.press(PointOption.point(12,12)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(PointOption.point(xOffset, yOffset))
		MultiTouchAction mtaction = new MultiTouchAction(null);
		mtaction.add(action).add(action).perform();
		
		
		

	}

	@AfterMethod
	public void onFailure(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS) {

			File image = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File(
					System.getProperty("user.dir") + "//screenshot//" + result.getMethod().getMethodName() + ".jpg"));

		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
