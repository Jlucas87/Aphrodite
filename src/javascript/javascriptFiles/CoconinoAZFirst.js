function getData(){
	var result = "";
	
	/*--Declare Variables--*/
	var parcelNumber = "ParcelNumber(VAR):";
	var address = "Address(VAR):";
	var landSqFt = "Lot Size:";
	
	var elements = document.getElementsByTagName("b");
	for (var i = 0; i < elements.length; i++)
	{
	    if (elements[i].textContent == "Parcel Number")
		{
		  parcelNumber = parcelNumber + elements[i].nextSibling.textContent;
		}
	
	    if (elements[i].textContent == "Situs Address")
		{
		  address = address + elements[i].nextSibling.textContent;
		}
		 if (elements[i].textContent == "Parcel Size")
		{
		  landSqFt = landSqFt + elements[i].nextSibling.textContent;
		}
	}

	var elements = document.getElementsByTagName("a");
	
	for (var i = 0; i < elements.length; i++)
	{
	    if (elements[i].textContent.contains("Assessment History"))
	        elements[i].setAttribute("id", "Assessment");
	}
	
	result = address + "~" + parcelNumber + "~" + landSqFt;

	return result;
}