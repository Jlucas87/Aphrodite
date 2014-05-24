package output;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import data.Parcel;

public class Writer {

	//-- Primay worksheet in the excel file --//
	private WritableWorkbook workbook;
	private WritableSheet sheet;
	
	//-- Directory to write out file --//
	private String dir;
	
	//-- Various formats to be used when creating cells --//
	private WritableCellFormat titleFormat;
	private WritableCellFormat headerFormat;
	private WritableCellFormat cellFormat;
	
	//-- Boolean to determine if we need to create headers --//
	private boolean headersSet = false;
	
	//-- Keep track of current position in worksheet --//
	private int row;
	private int column;
	
	/**
	 * Create a new writer configured to generate a file at the provided directory.
	 * 
	 * @param file location: dirIn
	 */
	public Writer(String dirIn){
		this.dir = dirIn;
	}
	
	/**
	 * Main method to call externally that will generate a new excel file, and write
	 * out data to the worksheet.
	 * 
	 * @param parcels
	 * @param keys
	 * @throws WriteException
	 */
	public void writeParcelDataToFile(List<Parcel> parcels, String[] keys) throws WriteException{
		//Create a new excel file in the output file
		createWorkbook();
		
		//Create title for the results based on county name
		createTitle(parcels.get(0).getSiteName());
		
		//Iterate through the primary hashmap for each parcel
		for(Parcel parcel : parcels){
			//Retrieve the data hashmap from the current parcel
			Map<String, HashMap<String, String>> mainMap = parcel.getData();
			int lengthMain = mainMap.size();
			
			//Create headers if they haven't been created yet
			if(!headersSet){
				createHeaders(mainMap, keys);
				row++;
				column = 0;
				headersSet = true;
			}
			
			//Iterate through the mainMap
			for(int i = 0; i < lengthMain; i++){
				//Retrieve the sub-map
				HashMap<String, String> subMap = mainMap.get(keys[i]);
				
				//Iterate through the submap and add values to the results file
				for(String key : subMap.keySet()){
					//Write each key-value pair out to the excel sheet
					try {
						//Add the parcel number
						if(column == 0 && i == 0){
							Label headerParcel = new Label(column, row, parcel.getParcelNumber(), this.cellFormat);
							this.sheet.addCell(headerParcel);
							column++;
						}
						Label dataParcel = new Label(column, row, subMap.get(key), this.cellFormat);
						this.sheet.addCell(dataParcel);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
					
					//Iterate to the next column
					column++;
				}
			}
			
			//Iterate to the next row
			row++;
			column = 0;
		}
		
		//Set column widths to autosize
		for(int i = 0; i < sheet.getColumns(); i++)
		{
			CellView cell = sheet.getColumnView(i);
			cell.setAutosize(true);
			sheet.setColumnView(i, cell);
		}
		
		//Write out the excel sheet to the desired file location
		try {
			workbook.write();
			workbook.close();
		} catch (WriteException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to generate a new results file in the output directory within the framework.
	 * It also sets up the WritableCellFormat for generating new cells.
	 */
	private void createWorkbook(){
		try {
			//Create the excel file
			File file = new File(dir);
			workbook = Workbook.createWorkbook(file);
			this.sheet = workbook.createSheet("results", 0);
			
			//Create other elements
			WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 16);
			cellFont.setBoldStyle(WritableFont.BOLD);
		    this.titleFormat = new WritableCellFormat(cellFont);
		    cellFont = new WritableFont(WritableFont.ARIAL, 12);
		    cellFont.setBoldStyle(WritableFont.BOLD);
		    this.headerFormat = new WritableCellFormat(cellFont);
		    cellFont = new WritableFont(WritableFont.ARIAL, 12);
		    cellFont.setBoldStyle(WritableFont.NO_BOLD);
		    this.cellFormat = new WritableCellFormat(cellFont);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates the title for the excel results file.
	 * 
	 * @param title
	 */
	private void createTitle(String title){
		//Create header for parcel numbers
		try {
			Label headerParcel = new Label(0, 0, title, this.titleFormat);
			this.sheet.addCell(headerParcel);
			
			//Move to the next row to create headers
			row++;
		} 
		catch (RowsExceededException e) {
			e.printStackTrace();
		} 
		catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates the headers dynamically based on provided hashmaps.
	 * 
	 * @param mainMap
	 * @param keys
	 */
	private void createHeaders(Map<String, HashMap<String, String>> mainMap, String[] keys){
		//Iterate through the primary hashmap for each parcel
		int lengthMain = mainMap.size();
		
		//Iterate through the mainMap
		for(int i = 0; i < lengthMain; i++){
			//Retrieve the sub-map
			HashMap<String, String> subMap = mainMap.get(keys[i]);
			
			//Iterate through the submap and add values to the results file
			for(String key : subMap.keySet()){
				//Write each key-value pair out to the excel sheet
				try {
					if(column == 0){
						Label headerParcel = new Label(column, row, "Property ID", this.headerFormat);
						this.sheet.addCell(headerParcel);
						column++;
					}
					Label headerParcel = new Label(column, row, keys[i]+": "+key, this.headerFormat);
					this.sheet.addCell(headerParcel);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
				//Iterate to the next column
				column++;
			}
		}
	}
}
