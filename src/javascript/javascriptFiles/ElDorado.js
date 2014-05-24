function getData(){
    var result = "";
    var elements = document.getElementsByTagName("td");
    
    /* Retrieve values contained in <td> tags */
    for(var i = 0; i < elements.length; i++){
        if(elements[i].textContent == "Land Total"){
            var value = elements[i].nextElementSibling;
            result = result + "Land Assessed Value:"+value.textContent+"~";
        }
        if(elements[i].textContent == "Total Roll"){
            var value = elements[i].nextElementSibling;
            result = result + "Total Value:"+value.textContent+"~";
        }
        if(elements[i].textContent == "Estimated Acreage"){
            var value = elements[i].nextElementSibling;
            result = result + "Lot Size Acres:"+value.textContent+"~";
        }
    }
    
    /* Retrieve value contained in a div */
    var element = document.getElementById("PROPDESC");
    var innerHtml = element.innerHTML;
    var indexOfUse = innerHtml.indexOf("Primary use");
    var indexOfBreak = innerHtml.indexOf("<br>", indexOfUse);
    result = result + "Land Use:"+innerHtml.substring(indexOfUse, indexOfBreak)+"~";
    
    /* Return the result string */
    result = result.substring(0, result.length - 1);
    return result;
}