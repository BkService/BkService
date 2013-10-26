function replaceShow() {
	var sign = document.getElementById("signin");
	var reg = document.getElementById("registration");
	if(sign.getAttribute("class") == "visible") {
		sign.setAttribute("class", "none");
		reg.setAttribute("class", "visible");
	} else {
		sign.setAttribute("class", "visible");
		reg.setAttribute("class", "none");
	}
}