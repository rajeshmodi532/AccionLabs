package Scenarios;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import BusinessFunctions.ReusableMethods;
import WebPageObjects.CartSummary;
import WebPageObjects.HomePage;

public class Scenario2 extends ReusableMethods{
	static String strSearchInput;
	static  Actions act;

	@SuppressWarnings("unused")
	@Test(groups= {"Regression"})
	public static void AddProductToCart() throws InterruptedException, IOException
	{
		try
		{
			System.out.println("------Scenario Two Execution started------"+"\n");
			HandleBrowserExtensions();
			LaunchURL();

			strSearchInput = prop.getProperty("SearchKeyword").toLowerCase();
			driver.findElement(HomePage.SearchFiled()).sendKeys(strSearchInput);
			driver.findElement(HomePage.SearchMagniFier()).click();

			ExpWait = new WebDriverWait(driver, 12);
			ExpWait.until(ExpectedConditions.elementToBeClickable(HomePage.SearchResult()));

			//Scroll down till AddCart can be visible
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("javascript:window.scrollBy(250,350)");

			//Mouse hover on product and click on AddToCart
			WebElement product = driver.findElement(HomePage.MouseHover_ProductOne());
			WebElement AddToCart = driver.findElement(HomePage.MouseHover_AddToCart());
			act = new Actions(driver);
			act.moveToElement(product).click(AddToCart).build().perform();

			//Wait for the element Proceed To Checkout and click on it
			WebElement ProceedToCheckOut = driver.findElement(HomePage.ProceedToCheckout());
			ExpWait.until(ExpectedConditions.visibilityOf(ProceedToCheckOut));
			ProceedToCheckOut.click();

			//Wait for cart title
			ExpWait.until(ExpectedConditions.visibilityOf(driver.findElement(CartSummary.CartTitle())));

			//Get list of tr from tbody
			List<WebElement> tr= driver.findElements(CartSummary.CartTableBody());
			for (WebElement trlist:tr)
			{
				String ProductName = driver.findElement(CartSummary.ProductName()).getText().toLowerCase();
				Assert.assertEquals(ProductName, strSearchInput);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error while adding product to cart. "+e.getMessage());
		}
		finally
		{
			TakeScreenshot_closeBrowser();
			System.out.println("------Scenario Two Execution Completed------"+"\n");
		}
	}
}