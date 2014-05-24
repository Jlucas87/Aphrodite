function getData(){
	var result = "";
	
	/*--Declare Variables--*/
	var asdLandVal = "Assessed Land Value:";
	var asdImpVal = "Assessed Improvement Value:";
	
	var elements = document.getElementsByTagName("td");
	for (var i = 0; i < elements.length; i++)
	{
	    if (elements[i].textContent.contains("Land") && elements[i].align == "left")
		{
	    	elements[i].nextElementSibling.textContent = elements[i].nextElementSibling.textContent.replace(/(\r\n|\n|\r)/gm,"");
	    	elements[i].nextElementSibling.textContent = elements[i].nextElementSibling.textContent.replace(/\s+/g," ");
	    	asdLandVal = asdLandVal + elements[i].nextElementSibling.textContent;
		}
	
	    if (elements[i].textContent.contains("Improvement") && elements[i].align == "left")
		{
	    	elements[i].nextElementSibling.textContent = elements[i].nextElementSibling.textContent.replace(/(\r\n|\n|\r)/gm,"");
	    	elements[i].nextElementSibling.textContent = elements[i].nextElementSibling.textContent.replace(/\s+/g," ");
		   	asdImpVal = asdImpVal + elements[i].nextElementSibling.textContent;
		}
	}
	
	result = asdLandVal + "~" + asdImpVal + "~";

	return result;
}