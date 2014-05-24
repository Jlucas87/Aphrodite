package execution;

import java.util.List;

import jxl.write.WriteException;
import input.FileLoader;
import input.ParcelReader;
import input.ProcedureReader;

import org.junit.After;
import org.junit.Test;

import output.Writer;
import data.AutopropData;
import data.Parcel;
import data.WebdriverObject;

public class Launcher {

	public Launcher(){
		//Empty Constructor
	}

	@Test
	public void launcher(){
		//Setup classes
		ExecutionScheme scheme = new ExecutionScheme();
		
		//Setup Reader classes
		ParcelReader parcelReader = new ParcelReader(FileLoader.chooseParcelFile());
		ProcedureReader procedureReader = new ProcedureReader(FileLoader.chooseProcedureFile());
		
		//Create county procedure based on excel data
		Procedure procedure = new Procedure(procedureReader.readCountyURL(),
				procedureReader.readInstructions());
		
		//Retrieve a list of parcels from the county excel file
		parcelReader.readCountyData();
		List<Parcel> parcels = parcelReader.readParcels();
		
		//Create a new Webdriver instance
		WebdriverObject.getInstance().createNewDriver("firefox");
		
		//Carry out the desired execution scheme
		parcels = scheme.runCountyOnly(parcels, procedure);
		
		//Create a writer and generate the output file
		Writer writer = new Writer(System.getProperty("user.dir")+"/src/output/results.xls");
		try {
			writer.writeParcelDataToFile(parcels, AutopropData.getInstance().getKeys());
		} 
		catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void Teardown(){
		WebdriverObject.getInstance().endSession();
	}	
}
