package input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import data.Parcel;

public class ParcelReader extends Reader {

	//-- Variables containing county data used for parcels --//
	private String siteName = "";
	private String mapsSearchTerm = "";
	private String addressFormat = "";
	private String gisListed = "";
	
	/**
	 * Constructor utilizes super constructor to build a new worksheet for the
	 * provided file.
	 * 
	 * @param parcelFile
	 */
	public ParcelReader(File parcelFile){
		super(parcelFile);
	}
	
	/**
	 * This method reads the county data cells in the first row
	 */
	public void readCountyData(){
		try{
			this.siteName = sheet.getCell(2, 0).getContents();
		}catch(Exception e){
			System.out.println("Site Name not found!");
		}
		try{
			this.mapsSearchTerm = sheet.getCell(3, 0).getContents();
		}catch(Exception e){
			System.out.println("Map search term not found!");
		}
		try{
			this.addressFormat = sheet.getCell(4, 0).getContents();
		}catch(Exception e){
			System.out.println("Address format not found!");
		}
		try{
			this.gisListed = sheet.getCell(5, 0).getContents();
		}catch(Exception e){
			System.out.println("GIS listed not found!");
		}
	}
	
	/**
	 * This method iterates through the first column of the worksheet until 
	 * all the parcel numbers have been found.
	 * 
	 * @return
	 */
	public List<Parcel> readParcels(){
		//Initialize array list to contain all the parcels
		List<Parcel> parcels = new ArrayList<Parcel>();
		
		//Find first parcel
		int row = 0;
		Cell parcelCell = sheet.getCell(0, row);
		
		//Verify first cell contains a value
		if(!parcelCell.getContents().isEmpty()){
			
			//Iterate through the sheet until no parcels remain
			while(!parcelCell.getContents().isEmpty()){
				//Add the parcel to the list
				Parcel parcel = new Parcel(parcelCell.getContents());
				
				//Add county data to the parcel
				parcel.setSiteName(this.siteName);
				parcel.setMapSearchTerm(this.mapsSearchTerm);
				parcel.setAddressFormat(this.addressFormat);
				parcel.setGisListed(this.gisListed);
				parcels.add(parcel);
				
				//Retrieve the next parcel from the worksheet
				row++;
				try{
					parcelCell = sheet.getCell(0, row);
				}
				catch(Exception e){
					break;
				}
			}
		}
		
		//Return the list
		return parcels;
	}
}
