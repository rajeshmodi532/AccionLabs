package Scenarios;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import BusinessFunctions.ReusableMethods;
import WebPageObjects.CartSummary;
import WebPageObjects.HomePage;

public class Scenario3 extends ReusableMethods{

	static String strSearchInput;
	static Actions act;

	@Test(groups= {"Smoke","Regression"})
	public static void ViewMoreAndAddToCart() throws IOException
	{
		try
		{
			System.out.println("------Scenario Three Execution started------"+"\n");
			HandleBrowserExtensions();
			LaunchURL();

			strSearchInput = prop.getProperty("SearchKeyword").toLowerCase();
			driver.findElement(HomePage.SearchFiled()).sendKeys(strSearchInput);
			driver.findElement(HomePage.SearchMagniFier()).click();

			WebDriverWait ExpWait = new WebDriverWait(driver, 12);
			ExpWait.until(ExpectedConditions.elementToBeClickable(HomePage.SearchResult()));

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("javascript:window.scrollBy(250,350)");

			WebElement product = driver.findElement(HomePage.MouseHover_ProductOne());
			WebElement More = driver.findElement(HomePage.More());
			act = new Actions(driver);
			act.moveToElement(product).click(More).build().perform();

			//Increase quantity
			driver.findElement(By.className("icon-plus")).click();
			System.out.println("Quantity increased to 2");

			//Get Size of the  product
			Select size = new Select(driver.findElement(HomePage.ProductSizeDropDown()));
			System.out.println("Size pf the product: "+size.getFirstSelectedOption().getText());

			size.selectByVisibleText("M");
			System.out.println("Updated size of the product: "+size.getFirstSelectedOption().getText());

			driver.findElement(HomePage.Button_AddToCart()).click();

			WebElement ProceedToCheckout = driver.findElement(HomePage.ProceedToCheckout());
			ExpWait.until(ExpectedConditions.visibilityOf(ProceedToCheckout));
			ProceedToCheckout.click();

			String ProductName = driver.findElement(CartSummary.ProductName()).getText().toLowerCase();
			Assert.assertEquals(ProductName, strSearchInput);
		}
		catch(Exception e)
		{
			System.out.println("Error while adding while adding product to cart: "+e.getMessage());
		}
		finally
		{
			TakeScreenshot_closeBrowser();
			System.out.println("------Scenario Three Execution Completed------"+"\n");
		}
	}
}