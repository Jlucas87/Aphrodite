package javascript;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import data.WebdriverObject;

public class ScriptExecutor {

	public ScriptExecutor(){
		//Empty constructor
	}
	
	/**
	 * Method for building a string containing all the contents of a javascript
	 * file.
	 * 
	 * @param path to Javascript file
	 * @return String containing Javascript code
	 */
	public String readFile(String path){
		StringBuilder contents = new StringBuilder();
		
		try{
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			while(br.ready()){
				contents.append(br.readLine());
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("Error occured while reading javascript file!");
		}
		
		//String containing the entire javascript function
		return contents.toString();
	}
	
	/**
	 * Method for executing a string containing a javascript routine.
	 * 
	 * @param jsContent
	 * @return results of javascript routine
	 */
	public String executeScript(String jsContent){
		//String to contain result of javascript execution
		String result = "";
		
		//Setup the Javascript executor
		WebDriver driver = WebdriverObject.getInstance().getDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		//Attempt to run the javascript file
		try{
			result = jse.executeScript(jsContent).toString();
		}
		catch(Exception e){
			System.out.println("Javascript execution failure!");
			System.out.println(e.getMessage());
		}
		
		//Return string of concatenated results
		return result;
	}
}
