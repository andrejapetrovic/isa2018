//pomocna funkcija za upadtovanje genericne liste na frontendu prilikom filtriranja na backendu
function filterData(data, scopeData) {
		if(data == undefined)
			return;
    	var destCodes = data.map(d => d.airportCode)
    	var scopeDestCodes = scopeData.map(d => d.airportCode);
    	var toRemove = scopeData.filter(function(d){
    		return destCodes.indexOf(d.airportCode) == -1;
    	});
    	var toAdd = data.filter(function(d){
    		return scopeDestCodes.indexOf(d.airportCode) == -1;
    	});
    	scopeData = scopeData.filter(d => !toRemove.includes(d));
    	scopeData = scopeData.concat(toAdd);
     	return scopeData;
	}

//stop propagation na dropdown
$('.stop-propagation').on('click', function (e) {
    e.stopPropagation();
});

function rgbToHex(r, g, b) {
    if (r > 255 || g > 255 || b > 255)
        throw "Invalid color component";
    return ((r << 16) | (g << 8) | b).toString(16);
}
