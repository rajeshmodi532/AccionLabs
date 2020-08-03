package WebPageObjects;

import org.openqa.selenium.By;

public class CartSummary {

	public static By CartTitle()
	{
		return By.cssSelector("#cart_title");
	}
	public static By CartTableBody()
	{
		return By.xpath("//table[@id='cart_summary']//tbody");
	}
	public static By ProductName()
	{
		return By.xpath("//td[@class='cart_description']//p[@class='product-name']//a");
	}
}
