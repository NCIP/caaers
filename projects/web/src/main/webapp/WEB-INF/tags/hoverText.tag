<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="description"%>
<%-- Make sure the page imports hover-display.js --%>
<div id="Tip1"   style="position: absolute; 
					    width: 200px; 
				    	left:285px; 
						top:188px; 
						visibility: hidden; 
						z-index:1; 
						padding: 4px; 
						background-color: #FFFFCC; 
						BORDER: 1px solid #000000"></div>
<a href="javascript:void(0)"  style="color:black; padding-left:3px; vertical-align:-5px;" onmouseover="getLayer('Tip1',event,'${description}')" onmousemove="floatLayer('Tip1',event)" onmouseout="hideLayer('Tip1')">
<img src="<c:url value="/imaages/q.gif"/>" border="0"></a>
						