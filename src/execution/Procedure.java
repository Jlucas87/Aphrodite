package execution;

import java.util.List;

import org.openqa.selenium.WebDriver;

import webElements.CommandProcessor;
import data.Instruction;
import data.Parcel;
import data.WebdriverObject;

/**
 * This class is used to process a list of instructions.  Ultimately, it will
 * return a parcel object that has been filled with additional data.
 * 
 * @author JLucas
 */
public class Procedure {

	private String countyURL;
	private List<Instruction> instructions;
	private CommandProcessor command;
	
	public Procedure(String countyURLIn, List<Instruction> instructionsIn){
		this.countyURL = countyURLIn;
		this.instructions = instructionsIn;
		this.command = new CommandProcessor();
	}
	
	public Parcel runProcedure(Parcel parcel){
		//Open up the url
		WebDriver driver = WebdriverObject.getInstance().getDriver();
		driver.get(this.countyURL);
		System.out.println("############ Processing Property: "+parcel.getParcelNumber()+" ############");
		
		//Carry out the procedure instructions
		for (Instruction inst : instructions) {
			parcel = command.executeInstruction(inst, parcel);
		}
		
		return parcel;
	}
	
	/**
	 * Returns an instance of the country url string.
	 * 
	 * @return county url
	 */
	public String getCountryURL(){
		return this.countyURL;
	}
}
