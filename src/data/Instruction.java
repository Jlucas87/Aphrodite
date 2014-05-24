package data;

public class Instruction {

	private String command;
	private String identifier;
	private String input;
	private String condition;
	
	/**
	 * Constructor initializes instructor variables.
	 */
	public Instruction(){
		this.command = "";
		this.identifier = "";
		this.input = "";
		this.condition = "";
	}
	
	//-- Setters --//
	public void setCommand(String commandIn){
		command = commandIn;
	}
	
	public void setIdentifier(String identifierIn){
		identifier = identifierIn;
	}
	
	public void setInput(String inputIn){
		input = inputIn;
	}
	
	public void setCondition(String conditionIn){
		condition = conditionIn;
	}
	
	//-- Getters --//
	public String getCommand(){
		return command;
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public String getInput(){
		return input;
	}
	
	public String getCondition(){
		return condition;
	}
	
	/**
	 * A simple utility method for viewing the contents of an instruction.
	 */
	public void printInstruction(){
		System.out.println("---- Viewing Instruction Contents ----");
		System.out.println("Command: "+this.command);
		System.out.println("Identifier: "+this.identifier);
		System.out.println("Input: "+this.input);
		System.out.println("Condition: "+this.condition);
	}
}
