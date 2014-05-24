package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class FileLoader {
	
	private final static String pathFile = "/src/input/Configuration/Paths.txt";
	private static String parcelPath = null;
	private static String procedurePath = null;

	public FileLoader(){
		//Empty Constructor
	}
	
	/**
	 * Function for obtaining the parcel data file.
	 * 
	 * @return File with parcel data
	 */
	public static File chooseParcelFile(){
		//Check for paths file
		FileLoader.checkPaths();
		
		//Create local variables
		File file = null;
		String title = "Choose parcel data file";
		
		//Create file object for parcel file
		if(FileLoader.parcelPath != null){
			file = new File(FileLoader.parcelPath);
		}
		else{
			file = produceFileChooser(title);
		}
		return file;
	}
	
	/**
	 * Function for obtaining the procedure file.  This file
	 * provides instructions for finding data for a specific county's
	 * web site.
	 * 
	 * @return File with county procedure data
	 */
	public static File chooseProcedureFile(){
		//Check for paths file
		FileLoader.checkPaths();
		
		//Create local variables
		File file = null;
		String title = "Choose county procedure file";
		
		//Create file object for parcel file
		if(FileLoader.procedurePath != null){
			file = new File(FileLoader.procedurePath);
		}
		else{
			file = produceFileChooser(title);
		}
		return file;
	}
	
	/**
	 * Function for generating a file chooser dialog with a custom title.
	 * 
	 * @param custom dialog title
	 * @return chosen file
	 */
	private static File produceFileChooser(String title){
		//Initialize file object
		File file = null;
		
		//Use a file chooser dialog to allow the user to pick the desired excel file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			file = fileChooser.getSelectedFile();
		}
		
		return file;
	}
	
	private static void checkPaths(){
		String path = System.getProperty("user.dir")+FileLoader.pathFile;
		
		try{
			//Attempt to read in the paths file
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			//Find the procedure and parcel file paths
			while(br.ready()){
				String curLine = br.readLine();
				String[] parts = curLine.split("=");
				if(parts[0].equalsIgnoreCase("Procedure")){
					FileLoader.procedurePath = parts[1];
				}
				else if(parts[0].equalsIgnoreCase("Parcel")){
					FileLoader.parcelPath = parts[1];
				}
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("Path file could not be read!");
		}
	}
}
