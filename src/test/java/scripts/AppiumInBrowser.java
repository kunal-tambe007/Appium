package scripts;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;
import pom.LoginPage;

public class AppiumInBrowser {

	private static AndroidDriver driver;
	String curDir = System.getProperty("user.dir");
	File file = null;
	LoginPage loginPage = null;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		file = new File(curDir + "//Apps//app-food2go-release.apk");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "4f1ad2a");
		dc.setCapability("platformVersion", "9.0");
		dc.setCapability("platformName", "Android");
		dc.setCapability("automationName", "UiAutomator2");
		// dc.setCapability("appPackage", "com.facebook.lite");
		// dc.setCapability("appActivity", "com.facebook.lite.MainActivity");
		
		dc.setCapability("language", "en"); // language is Spanish
		dc.setCapability("locale", "en_US"); // region is Spanish (Uruguay)
		
		//dc.setCapability("language", "es"); // language is Spanish
		//dc.setCapability("locale", "es_UY"); // region is Spanish (Uruguay)


		dc.setCapability("appPackage", "de.stroetmann.food2go");
		dc.setCapability("appActivity", "de.stroetmann.views.activities.SplashActivity");
		// dc.setCapability("app", file.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

	}

	@Test
	public void loginScreen() throws InterruptedException, IOException {
		// loginPage = new LoginPage(driver);

		// loginPage.loginApp("paypal", "test123");

		

		Set<String> appViews = driver.getContextHandles();

		for (String window : appViews) {
			System.out.println(window);
		}

		System.out.println("Battery Information " + driver.getBatteryInfo().getLevel());
		System.out.println("Correct Context is " + driver.getContext());

		System.out.println("SHOWS KEYPADDDD");
		driver.getKeyboard();

		Thread.sleep(3000);

		driver.hideKeyboard();

		LogEntries logs = driver.manage().logs().get("logcat");
		System.out.println("First and last ten lines of log: ");
		StreamSupport.stream(logs.spliterator(), false).limit(10).forEach(System.out::println);
		System.out.println("...");

		System.out.println("Current activity " + driver.currentActivity());

		System.out.println("AUtomation Name is " + driver.getAutomationName());

		System.out.println("Current time " + driver.getDeviceTime());
		
		
	System.out.println("Is Device locked " + driver.isDeviceLocked());
	
		//driver.lockDevice(Duration.ofSeconds(3));
		System.out.println("Is Device locked " + driver.isDeviceLocked());
		
		System.out.println("Current location " + driver.location());
		
		System.out.println("IsBrowser " + driver.isBrowser());
		Activity activity = new Activity("de.stroetmann.food2go", "de.stroetmann.views.activities.SplashActivity");
		//driver.startActivity(activity);
		
		driver.openNotifications();

		Thread.sleep(3000);
	}

	@AfterMethod
	public void onFailure(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS) {

			File image = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image,
					new File(System.getProperty("user.dir") + "//screenshot//" + result.getMethod().getMethodName() + ".jpg"));

		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
