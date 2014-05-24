package execution;

import java.util.List;

import data.AutopropData;
import data.Parcel;
import data.WebdriverObject;

/**
 * This class coordinates the order in which different sites are accessed and which sites 
 * are accessed. 
 * 
 * @author JLucas
 */
public class ExecutionScheme {

	public ExecutionScheme(){
		//Empty constructor!
	}
	
	public void runCountyZillowTrulia(){
		//Needs to be implemented
	}
	
	public List<Parcel> runCountyOnly(List<Parcel> parcels, Procedure procedure){
		//Setup the keys array
		String[] keys = {"county"};
		AutopropData.getInstance().setKeys(keys);
		
		//Execute the procedure until we run through all the parcels
		for (int i = 0; i < parcels.size(); i++) {
			//Parcel containing new data
			Parcel parcel = parcels.get(i);
			
			//Set current site variable
			AutopropData.getInstance().setCurSite("county");
			
			//Create new county hashmap to store data
			parcel.setNewInnerMap("county");
			
			//Run the procedure for the current parcel
			parcel = procedure.runProcedure(parcels.get(i));
			
			//replace the old parcel with the new parcel
			parcels.set(i, parcel);
			
			//Revert to the beginning of the county site
			WebdriverObject.getInstance().getDriver().get(procedure.getCountryURL());
		}
		
		//Return the parcel list after running the procedure against all of them
		return parcels;
	}
}
