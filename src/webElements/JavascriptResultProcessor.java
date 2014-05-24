package webElements;

import java.util.HashMap;
import java.util.Map;

import data.AutopropData;
import data.Parcel;

public class JavascriptResultProcessor {

	public JavascriptResultProcessor(){
		//Empty constructor
	}
	
	/**
	 * This method is used to process the result of a javascript run.  This method takes in a
	 * string that must be formatted in a specific manner such that a hashmap can be generated
	 * by using split actions.  Each key-value pair should be separated by a ":" and every pair 
	 * should be separated by a "~".  
	 * 
	 * Example:  "primaryUse:Playing with dogs~totalValue:$500,000"
	 * 
	 * @param results
	 * @return
	 */
	public Parcel processResult(String results, Parcel parcel){
		//Determine current site
		String site = AutopropData.getInstance().getCurSite();
		
		//Split up the string into an array of key-value pairs
		String[] pairs = results.split("~");
		
		//Create a hashmap for storing the key-value pairs
		Map<String, String> map = new HashMap<String, String>();
		
		//Place all the key-value pairs into a hashmap for processing
		for(int i = 0; i < pairs.length; i++){
			String[] pair = pairs[i].split(":", 2);
			map.put(pair[0], pair[1]);
		}
		
		//Iterate through the hashmap and place all matching keys into the parcel object
		for (Map.Entry<String, String> entry : map.entrySet()) {
			//Set the value for the specified site hashmap
			parcel.setPropertyData(site, entry.getKey(), entry.getValue());
			
			//Print out information about data set
			System.out.println("Setting the '"+entry.getKey()+"' property to '"+entry.getValue()+
			"' for the following site: "+site);
		}
		
		//Return the parcel with the added data
		return parcel;
	}
}
