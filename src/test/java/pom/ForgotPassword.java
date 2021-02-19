package pom;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ForgotPassword {

	AndroidDriver<MobileElement> driver;

	public ForgotPassword() {

	}

	public ForgotPassword(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void test() {
		System.out.println("This is new code..");
	}

}
