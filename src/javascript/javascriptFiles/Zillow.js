function GetData(){
	var result = "";
	var elements = document.getElementsByClassName("prop-mod prop-summary bump-addr");
	var bedBathSqFt = elements[0].lastChild.textContent;
	var bedBathSqFtArray = bedBathSqFt.split(", ");
		
	for (var i = 0; i < bedBathSqFtArray.length; i++)
	{
		if (bedBathSqFtArray[i].contains("bed"))
			result = result + "Beds:"+bedBathSqFtArray[i]+"~";
		if (bedBathSqFtArray[i].contains("bath"))
			result = result + "Baths:"+bedBathSqFtArray[i]+"~";
		if (bedBathSqFtArray[i].contains("sqft"))
			result = result + "Sq. Ft.:"+bedBathSqFtArray[i]+"~";
	}

	//-------------------

	elements = document.getElementsByClassName("label");
	for (var i = 0; i < elements.length; i++)
	{
	    if (elements[i].textContent.contains("Zestimate"))
	        result = result + "Zestimate:"+elements[i].nextSibling.textContent+"~";
	}

	//-------------------

	elements = document.getElementsByClassName("fact-bullet")
		
	for (var i = 0; i < elements.length; i++)
	{
		if (elements[i].textContent.contains("Lot"))
			result = result + "Lot Size:"+elements[i].textContent+"~";
		if (elements[i].textContent.contains("Built in"))
		{
		    var brokenElement = elements[i].textContent.split(" ");
			result = result + "Built In:"+brokenElement[brokenElement.length - 1]+"~";
		}
	}

	//-------------------

	elements = document.getElementsByClassName("date")
		
	if (elements.length > 0)
	    result = result + "Last Action: Date:"+elements[0].textContent+" "+
	                                 "Action:"+elements[0].nextSibling.textContent+" "+
	                                 "Price:"+elements[0].nextSibling.nextSibling.textContent+"~";
		
	result;
}