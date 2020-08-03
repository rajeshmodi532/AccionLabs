package WebPageObjects;

import org.openqa.selenium.By;

public class LoginPage {
	public static By SignIn_link()
	{
		return By.className("login");
	}
	public static By EmailAddress()
	{
		return By.id("email");
	}
	public static By Password()
	{
		return By.id("passwd");
	}
	public static By SignIn_btn()
	{
		return By.xpath("//*[@id='SubmitLogin']/span");
	}
	public static By Auth_fail()
	{
		return By.xpath("//*[@id='center_column']/div//li[contains(text(),'Authentication failed.')]");
	}
}
