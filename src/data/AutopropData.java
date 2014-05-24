package data;

public class AutopropData {

	private static AutopropData instance;
	
	/**
	 * A string indicating the desired execution scheme, which corresponds 
	 * to which sites will be used to gather information and the order in which 
	 * those sites will be accessed.
	 */
	private String eScheme;
	
	private String curSite;
	
	private String[] keys;
	
	private AutopropData(){
		//Empty Constructor
	}
	
	public static AutopropData getInstance(){
		if(instance == null){
			instance = new AutopropData();
		}
		return instance;
	}

	//-- GETTERS --//
	public String geteScheme() {
		return eScheme;
	}

	public String getCurSite() {
		return curSite;
	}
	
	public String[] getKeys(){
		return keys;
	}

	//-- SETTERS --//
	public void seteScheme(String eScheme) {
		this.eScheme = eScheme;
	}

	public void setCurSite(String curSite) {
		this.curSite = curSite;
	}
	
	public void setKeys(String[] keysIn){
		this.keys = keysIn;
	}
}
