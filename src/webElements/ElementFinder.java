package webElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import data.WebdriverObject;

/**
 * This class is used to find elements used by the CommandProcessor.
 * 
 * @author JLucas
 */
public class ElementFinder {

	private String attribute;
	private String value;
	private String locator;
	
	public ElementFinder(){
		//Empty Constructor
	}
	
	/**
	 * This method attempts to find the element on the page.  If it cannot, 
	 * an error will be returned indicating as much.
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement findElement(String locator, String type){
		//setup variables
		WebElement el = null;
		this.locator = locator;
		
		//Break out the locator and the value
		String[] locatorParts = locator.split("=");
		attribute = locatorParts[0];
		value = locatorParts[1];
		
		//Find the element based on the search type
		if(type.equalsIgnoreCase("generic")){
			el = this.findByAttribute(el);
		}
		else if(type.equalsIgnoreCase("partial")){
			el = this.findPartialLink(el);
		}
		
		//Return the webelement
		return el;
	}
	
	/**
	 * A generic search function that uses the css selector with the attribute
	 * and value found in the excel sheet.
	 * 
	 * @param Empty WebElement
	 * @return Discovered WebElement
	 */
	private WebElement findByAttribute(WebElement el){
		//Attempt to find the element based on the provided attribute
		try{
			WebDriver driver = WebdriverObject.getInstance().getDriver();
			el = driver.findElement(By.cssSelector("["+attribute+"='"+value+"']"));
		}
		catch(Exception e){
			System.out.println("Unable to locate the element: "+locator);
		}
		
		//Return the discovered WebElement or null if there was an exception
		return el;
	}
	
	/**
	 * A specialized function used to find a link with partial matching 
	 * link text.
	 * 
	 * @param Empty WebElement
	 * @return Discovered WebElement
	 */
	private WebElement findPartialLink(WebElement el){
		//Attempt to find the element based on the provided attribute
		try{
			//Find all the links on the page
			WebDriver driver = WebdriverObject.getInstance().getDriver();
			el = driver.findElement(By.partialLinkText(value));
		}
		catch(Exception e){
			System.out.println("Unable to locate the element: "+locator);
			System.out.println(e.getMessage());
		}
		
		//Return the discovered WebElement or null if there was an exception
		return el;
	}
}
