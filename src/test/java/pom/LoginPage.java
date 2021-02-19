package pom;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	private AndroidDriver<AndroidElement> driver;

	public LoginPage() {

	}

	public LoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "de.stroetmann.food2go:id/user_email_edit_text")
	private AndroidElement username;

	@AndroidFindBy(id = "de.stroetmann.food2go:id/user_password_edit_text")
	private AndroidElement password;

	public void EntUserName(String str) {
		username.sendKeys(str);
	}

	public void EntPass(String str) {
		password.sendKeys(str);
	}

	public void loginApp(String username, String password) {

		EntUserName(username);
		EntPass(password);

	}

}
