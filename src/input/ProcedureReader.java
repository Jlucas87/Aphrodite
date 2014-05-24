package input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import data.Instruction;
import jxl.Cell;

public class ProcedureReader extends Reader {
	
	/**
	 * Create a new ProcedureReader with the provided File data.
	 * 
	 * @param File procedureFile
	 */
	public ProcedureReader(File procedureFile){
		super(procedureFile);
	}
	
	/**
	 * Method for reading the URL for the county site.
	 * 
	 * @return String URL
	 */
	public String readCountyURL(){
		//Initialize variables
		String countyURL = null;
		
		//Retrieve the url of the county site
		Cell county = sheet.getCell(0, 0);
		countyURL = county.getContents();
		
		//Return the result string
		return countyURL;
	}
	
	/**
	 * This method iterates through the worksheet until all procedure instructions have
	 * been read in and then returns the resulting list.
	 * 
	 * @return List of instructions
	 */
	public List<Instruction> readInstructions(){
		//Initialize a list of instructions
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		//Get the contents of cell B1, where the first instruction is located
		Cell currentCommandCell = sheet.getCell(1, 0);
		int column = 1;
		
		//Loop through the spreadsheet until no further commands are found
		while(!currentCommandCell.getContents().isEmpty()){
			Instruction inst = new Instruction();
			
			//Enter the values from the excel into the instruction
			inst.setCommand(currentCommandCell.getContents());
			inst.setIdentifier(sheet.getCell(column, 1).getContents());
			
			try{
				inst.setInput(sheet.getCell(column, 2).getContents());
			}catch(Exception e){
				//No need for output
			}
			try{
				inst.setCondition(sheet.getCell(column, 3).getContents());
			}catch(Exception e){
				//No need for output
			}
			
			//Add the instruction to the list and iterate to next column
			instructions.add(inst);
			
			//Prepare the next command cell
			column++;
			try{
				currentCommandCell = sheet.getCell(column, 0);
			}catch(Exception e){
				break;
			}
		}
		
		//Return the completed instruction list
		return instructions;
	}
}
