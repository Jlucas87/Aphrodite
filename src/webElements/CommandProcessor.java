package webElements;

import javascript.ScriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import data.Instruction;
import data.Parcel;

/**
 * This class processes the commands from the procedure file.
 * 
 * @author JLucas
 */
public class CommandProcessor {
	
	//-- Additional utility classes used by the CommandProcessor --//
	private ElementFinder finder;
	private ScriptExecutor scriptRunner;
	private JavascriptResultProcessor jsResults;
	
	//-- Constants --//
	private final String generic = "GENERIC";
	private final String partial = "PARTIAL";
	private final String jPath = "/src/javascript/javascriptFiles/";
	
	public CommandProcessor(){
		finder = new ElementFinder();
		scriptRunner = new ScriptExecutor();
		jsResults = new JavascriptResultProcessor();
	}
	
	/**
	 * This method takes in an Instruction parameter and carries out the 
	 * desired command using webdriver.
	 * 
	 * @param instruction
	 * @return boolean indicating if there was a result
	 */
	public Parcel executeInstruction(Instruction instruction, Parcel parcel){
		//Perform all variables replacements
		String input = instruction.getInput();
		String locator = instruction.getIdentifier();
		input = variableReplacement(input, parcel);
		locator = variableReplacement(locator, parcel);
		
		//Carry out the command based on the action cell
		String command = instruction.getCommand();
		
		//Find an click on an element
		if(command.equalsIgnoreCase("click")){
			try{
				WebElement el = finder.findElement(locator, generic);
				el.click();
			}
			catch(Exception e){
				System.out.println("Cannot click on the element!");
			}
		}
		
		//Find a link that matches a partial link text
		if(command.equalsIgnoreCase("ClickLinkPartial")){
			try{
				WebElement el = finder.findElement(locator, partial);
				el.click();
			}
			catch(Exception e){
				System.out.println("Cannot click on the element!");
				System.out.println(e.getMessage());
			}
		}
		
		//Find and type into an element
		if(command.equalsIgnoreCase("type")){
			try{
				//Grab the element using the provided identifier
				WebElement el = finder.findElement(locator, generic);
				
				//Click on the element in preparation to type
				el.click();
							
				//Type the input value into the field
				el.sendKeys(input);
			}
			catch(StaleElementReferenceException sere){
				System.out.println("Cannot click on the element!");
			}
		}
		
		//Execute a javascript routine to obtain parcel data
		if(command.equalsIgnoreCase("RetrieveValues")){
			//Generate the file path for the javascript file
			String systemPath = System.getProperty("user.dir");
			String fullPath = systemPath + jPath + locator;
			
			try{
				//Build and execute the javascript routine
				String jsContent = scriptRunner.readFile(fullPath);
				jsContent = jsContent + "var result = getData(); return result;";
				String result = scriptRunner.executeScript(jsContent);
			
				//Process the results
				parcel = jsResults.processResult(result, parcel);
			}
			catch(Exception e){
				System.out.println("Error handling javascript routine!");
				System.out.println(e.getMessage());
			}
		}
		
		//Return true if data was obtained, otherwise return false
		return parcel;
	}
	
	/**
	 * This method will carry out all variable replacements.  Can be extended as more variables
	 * are added to the framework.
	 * 
	 * @param input
	 * @param parcel
	 * @return input
	 */
	private String variableReplacement(String input, Parcel parcel){
		//Replace parcelNumber variable
		input = input.replace("ParcelNumber", parcel.getParcelNumber());
		
		//Replace screenSize variable
		//input = input.replace("screenWidth", Toolkit.getDefaultToolkit().getScreenSize().width;
		//input = input.replace("screenHeight", Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//Add additional replacements here
		return input;
	}
}
