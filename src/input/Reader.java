package input;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;

public abstract class Reader {

	protected Sheet sheet;
	
	/**
	 * Initializes a new workbook and sheet for an implementing child reader.
	 * 
	 * @param excel input file
	 */
	public Reader(File inputFile){
		//Create a new workbook based on the provided input file
		try {
			Workbook workbook = Workbook.getWorkbook(inputFile);
			this.sheet = workbook.getSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
