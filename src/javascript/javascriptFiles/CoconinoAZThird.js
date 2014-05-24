function getData(){
	var result = "";
	
	/*--Declare Variables--*/
	var propertyType = "Property Type:";
	
	var elements = document.getElementsByTagName("span");
	for (var i = 0; i < elements.length; i++)
	{
	    if (elements[i].textContent.contains("Property Code"))
		{
	    	elements[i].nextElementSibling.nextElementSibling.textContent = elements[i].nextElementSibling.nextElementSibling.textContent.replace(/(\r\n|\n|\r)/gm,"");
	    	elements[i].nextElementSibling.nextElementSibling.textContent = elements[i].nextElementSibling.nextElementSibling.textContent.replace(/\s+/g," ");
	    	propertyType = propertyType + elements[i].nextElementSibling.nextElementSibling.textContent;
		}
	}
	
	result = propertyType;

	return result;
}