package data;

import java.util.HashMap;
import java.util.Map;

public class Parcel {

	//-- Parcel Number --//
	private String parcelNumber;
	
	//-- Information about the property --//
	private Map<String, HashMap<String, String>> propertyData;
	
	//-- Information related to the county site --//
	private String siteName;
	private String mapSearchTerm;
	private String addressFormat;
	private String gisListed;
	
	/**
	 * Constructor creates parcel object with the provided parcel number.
	 * 
	 * @param String parcelNumberIn
	 */
	public Parcel(String parcelNumberIn){
		//append parcel number
		parcelNumber = parcelNumberIn;
		
		//Initialize propertyData hashmap
		propertyData = new HashMap<String, HashMap<String, String>>();
	}

	//-- GETTERS --//
	public String getParcelNumber(){
		return parcelNumber;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getMapSearchTerm() {
		return mapSearchTerm;
	}

	public String getAddressFormat() {
		return addressFormat;
	}

	public String getGisListed() {
		return gisListed;
	}
	
	public Map<String, HashMap<String, String>> getData(){
		return propertyData;
	}
	

	//-- SETTERS --//
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setMapSearchTerm(String mapSearchTerm) {
		this.mapSearchTerm = mapSearchTerm;
	}

	public void setAddressFormat(String addressFormat) {
		this.addressFormat = addressFormat;
	}

	public void setGisListed(String gisListed) {
		this.gisListed = gisListed;
	}
	
	public void setPropertyData(String mapKey, String dataKey, String value){
		this.propertyData.get(mapKey).put(dataKey, value);
	}
	
	public void setNewInnerMap(String mapKey){
		this.propertyData.put(mapKey, new HashMap<String, String>());
	}
}
