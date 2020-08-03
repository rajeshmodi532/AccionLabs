package BusinessFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	protected static String URL = prop.getProperty("URL");
	static FileInputStream fis;
	public static WebDriverWait ExpWait;

	//@Test(priority=0)
	public static void HandleBrowserExtensions()
	{
		try
		{
			DesiredCapabilities ch = new DesiredCapabilities();
			ChromeOptions c = new ChromeOptions();
			c.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			c.setExperimentalOption("useAutomationExtension", false);
			c.merge(ch);
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver(c);
		}
		catch (Exception e)
		{
			System.out.println("Error while handling browser extensions"+e.getMessage());
		}
	}

	//@Test(dependsOnMethods = {"HandleBrowserExtensions"})
	public static void LaunchURL()
	{
		try
		{
			fis = new FileInputStream("H:\\Eclipse Workspace\\AccionLabsAssignment\\config.properties");
			prop.load(fis);
			driver.get(prop.getProperty("URL"));
			driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			System.out.println("Exception in launching URL in browser."+e.getMessage());
		}
	}

	//@AfterSuite
	public static void TakeScreenshot_closeBrowser() throws IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh.mm.ss");
		Date dt = new Date();
		File src =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(("Screenshots/"+sdf.format(dt)+".png"));
		FileUtils.copyFile(src,dest);
		driver.close(); }
}