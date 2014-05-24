package data;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverObject {

	private static WebdriverObject instance;
	private WebDriver driver;
	
	private WebdriverObject(){
		//Empty constructor
	}
	
	/**
	 * This function returns an instance of the current webdriver object.
	 * 
	 * @return Webdriver instance
	 */
	public static WebdriverObject getInstance(){
		if(instance == null){
			instance = new WebdriverObject();
		}
		return instance;
	}
	
	/**
	 * Create a new WebDriver object based on the desired browser type.
	 * 
	 * @param browser
	 */
	public void createNewDriver(String browser){
		//Create a new Webdriver instance based on the provided browser string
		if(browser.equalsIgnoreCase("FIREFOX")){
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("CHROME")){
			driver = new ChromeDriver();
		}
		
		//Setup timeout settings for the new driver
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		//Open up a blank webpage
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
	}
	
	/**
	 * Returns the current webdriver object.
	 * 
	 * @return webdriver
	 */
	public WebDriver getDriver(){
		return this.driver;
	}
	
	/**
	 * Terminates the selenium session at the end of a test run.
	 */
	public void endSession(){
		driver.close();
		driver.quit();
	}
}
