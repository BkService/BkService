
/* 
 * Generate post request to SwitchHandler servlet.
 * parameter of request - data.id
 * not ajax request (!)
 */
function switchPage(data) {
	var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", "/SimpleServer/SwitchHandler");
	
	var field = document.createElement("input");
		field.setAttribute("name", "target");
		field.setAttribute("type", "hidden");
		field.setAttribute("value", data.id);
	
	form.appendChild(field);
	document.body.appendChild(form);
	form.submit();
}

function showChild() {
	var count = document.getElementsByName('market').length;
	var elements = document.getElementsByName('market');
	for(var i = 0; i < count; ++i) {
		elements[i].setAttribute("class", "itemMarketView");
	}
}

function showc(marker) {
	var coeffs = document.getElementsByName('coeff');
	var countc = document.getElementsByName('coeff').length;
	var num = parseInt(marker.id.substr(1));
	var idr = new RegExp('^c'+ num +'+$');
	for(var i = 0; i < countc; ++i) {
		if(idr.test(coeffs[i].id))
		   if(coeffs[i].getAttribute("class") == "cell")
			  coeffs[i].setAttribute("class", "cellshow");
		   else
			   coeffs[i].setAttribute("class", "cell");
	}
}

