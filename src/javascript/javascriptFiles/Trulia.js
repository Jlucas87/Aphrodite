function GetData(){
	var result = "";
	var elements = document.getElementsByClassName("listInline typeEmphasize lhn mtn");
	var element = elements[0].children;

	for (var i = 0; i < element.length; i++)
	{
	    element[i].textContent = element[i].textContent.replace(/(\r\n|\n|\r)/gm,"");
	    element[i].textContent = element[i].textContent.replace(/\s+/g," ");

	    if (element[i].textContent.contains("bed"))
		   result = result + "Beds:"+element[i].textContent+"~";
		else if (element[i].textContent.contains("bath"))
		   result = result + "Baths:"+element[i].textContent+"~";
		else if (element[i].textContent.contains("sqft"))
		   result = result + "Sq. Ft.:"+element[i].textContent+"~";
		else
		   result = result + "Property Type:"+element[i].textContent+"~";
	}

	//----------------------

	elements = document.getElementsByClassName("h3 typeEmphasize mvn");
	element[0].textContent = element[0].textContent.replace(/(\r\n|\n|\r)/gm,"");
	element[0].textContent = element[0].textContent.replace(/\s+/g,"");
	result = result + "Trulia Estimate:"+elements[0].textContent+"~";

	//----------------------

	elements = document.getElementsByTagName("li");

	for (var i = 0; i < elements.length; i++)
	{
	    if (!elements[i].className.contains("listTopAlign"))
	    {
	        if (elements[i].textContent.contains(" Rooms"))
		       result = result + "Rooms:"+elements[i].textContent+"~";
		    if (elements[i].textContent.contains("Lot Size"))
		       result = result + "Lot Size:"+elements[i].textContent+"~";
		    if (elements[i].textContent.contains("Built In"))
		       result = result + "Built In:"+elements[i].textContent+"~";
		}
	}

	result;
}