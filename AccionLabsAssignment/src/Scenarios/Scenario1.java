package Scenarios;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import BusinessFunctions.ReusableMethods;
import WebPageObjects.HomePage;

public class Scenario1 extends ReusableMethods {

	static String strSearchInput, strSearchOutput;

	@Test(groups= { "Smoke" })
	public static void SearchProduct() throws IOException
	{
		try
		{
			System.out.println("------Scenario One Execution started------"+"\n");
			HandleBrowserExtensions();
			LaunchURL();
			//Get Search Input from Config file
			strSearchInput = prop.getProperty("SearchKeyword").toLowerCase();
			//Enter search keyword
			driver.findElement(HomePage.SearchFiled()).sendKeys(strSearchInput);
			//Click on Search magnifier
			driver.findElement(HomePage.SearchMagniFier()).click();
			//Get text from search results
			strSearchOutput = driver.findElement(HomePage.SearchResult()).getText().replaceAll("^\"|\"$", "").toLowerCase();

			Assert.assertEquals(strSearchOutput, strSearchInput);
		}
		catch (Exception e)
		{
			System.out.println("Error while Searching for product"+e.getMessage());
		}
		finally
		{
			System.out.println("------Scenario One Execution Completed------"+"\n");
			TakeScreenshot_closeBrowser();
		}
	}
}