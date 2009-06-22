/*
 * This script is used by the hoverText tag.
 *
 */
// Main function to retrieve mouse x-y pos.s
	function getMouseXY(e) {
		var IE = document.all?true:false
		var tempX = 0
		var tempY = 0	
  		//alert ("I am in");
  		if (IE) { // grab the x-y pos.s if browser is IE
    		//alert ("I am IE");
    		tempX = event.clientX + document.body.scrollLeft
    		tempY = event.clientY + document.body.scrollTop
  		} else {  // grab the x-y pos.s if browser is NS
    		//alert ("I am not IE");
    		tempX = e.pageX
    		tempY = e.pageY
  		}  
  		// catch possible negative values in NS4
  		if (tempX < 0){tempX = 0}
  		if (tempY < 0){tempY = 0}  
  		var coordinates = { X: tempX , Y:tempY }
  		return coordinates
	}

	function getLayer(SRC, event, description ){
		var tDIV = document.getElementById(SRC)             
        tDIV.style.cursor = "hand"
		var coordinates = getMouseXY(event);
			
		tDIV.style.left = coordinates.X +"px"
		tDIV.style.top = (coordinates.Y - 40 )+"px"
		tDIV.innerHTML=description;
		tDIV.style.visibility = "visible"
		}

	function floatLayer(SRC, event){
		var tDIV = document.getElementById(SRC)
		var coordinates = getMouseXY(event);
			
		tDIV.style.left = coordinates.X +"px"
		tDIV.style.top = (coordinates.Y - 40 )+"px"
		tDIV.style.visibility = "visible"
		}
			
		function hideLayer(SRC){
			var tDIV = document.getElementById(SRC)
            tDIV.style.visibility='hidden'
		}