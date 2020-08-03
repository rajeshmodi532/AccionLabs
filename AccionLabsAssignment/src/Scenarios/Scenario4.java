package Scenarios;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import BusinessFunctions.ReusableMethods;
import WebPageObjects.LoginPage;

public class Scenario4 extends ReusableMethods{

	@Test(groups={"Smoke","Regression"})
	public void LoginPageFailure() throws IOException
	{
		try
		{
			System.out.println("------Scenario Four Execution started------"+"\n");
			HandleBrowserExtensions();
			LaunchURL();

			//login
			driver.findElement(LoginPage.SignIn_link()).click();
			driver.findElement(LoginPage.EmailAddress()).sendKeys(prop.getProperty("EmailAddress"));
			driver.findElement(LoginPage.Password()).sendKeys(prop.getProperty("Password"));
			driver.findElement(LoginPage.SignIn_btn()).click();

			//Verify Authentication
			String Auth = driver.findElement(LoginPage.Auth_fail()).getText();
			Assert.assertEquals(Auth, "Authentication failed.");
		}
		catch (Exception e)
		{
			System.out.println("Error while trying SignIn to page."+e.getMessage());
		}
		finally
		{
			TakeScreenshot_closeBrowser();
			System.out.println("------Scenario Four Execution Completed------"+"\n");
		}
	}
}