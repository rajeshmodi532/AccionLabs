package WebPageObjects;

import org.openqa.selenium.By;

public class HomePage {

	public static By SearchFiled()
	{
		return By.id("search_query_top");
	}
	public static By SearchMagniFier()
	{
		return By.xpath("//*[@id='searchbox']/button");
	}
	public static By SearchResult()
	{
		return By.cssSelector(".lighter");
	}
	public static By MouseHover_ProductOne()
	{
		return By.xpath("//*[@id='center_column']/ul/li[1]");
	}
	public static By MouseHover_AddToCart()
	{
		return  By.xpath("//*[@id='center_column']/ul/li//span[contains(text(), 'Add to cart')]");
	}
	public static By More()
	{
		return By.xpath("//div[@id='center_column']/ul/li//span[contains(text(), 'More')]");
	}
	public static By ProductSizeDropDown()
	{
		return By.id("group_1");
	}
	public static By Button_AddToCart()
	{
		return By.xpath("//button[@name='Submit']");
	}
	
	public static By ProceedToCheckout()
	{
		return By.xpath("//span[contains(text(),'Proceed to checkout')]");
	}
	


}
