<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<script type="text/javascript">

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
</script>

<%@attribute name="description"%>

				<div id="Tip1" 
					style="position: absolute; 
									 width: 200px; 
									 left:285px; 
									 top:188px; 
									 visibility: hidden; 
									 z-index:1; 
									 padding: 4px; 
									 background-color: #FFFFCC; 
									 BORDER: 1px solid #000000"></div>
									 
									 
<a href="javascript:void(0)"  style="color:black;" onmouseover="getLayer('Tip1',event,'${description}')" onmousemove="floatLayer('Tip1',event)" onmouseout="hideLayer('Tip1')">
<img src="/caaers/images/checkyes.gif" border="0" alt="Add"></a>
						