<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Import MedDRA Dictionary</title>
<tags:includeScriptaculous />
<tags:dwrJavascriptLink objects="importMeddra" />
<style type="text/css">
        td.display {
	        background-color: white;
        }
        
    </style>
<script>

var t= 0 ;

function handleGetData(step) {
  	
  call(step)
  
}

function call(i) {

		var temp = i + ''
		$(temp).style.display=""
		$(temp +'-indicator').className=''
		importMeddra.handleMedDRA($('input').value, 
								  i,
								  function(str) {
									  var message = "<b> " + str + "</b>" 
									  $(temp+'-indicator').className='indicator'
									  $(temp+'-done').innerHTML=message;
									  $(temp+'-done').style.display="";
									  t= t +1; 
									  if( t < 8) handleGetData(t)
									  });
}

	Event.observe(window, "load", function() {
		Event.observe("button","click",function() { call(0) })
    	}           
    )



</script>
</head>
<body>
<p id="instructions" style="background:yellow;">
 To import the <b>MedDRA</b> terminology you need to specify the full path of the folder that contains the ASCII files, and then hit the Import Button.<br>
 MedDRA delivers the terminology as a set of ASCII files, file extensions end with .asc. Currently we support MedDRA versions 9.0 - 9.1
</p>
	<input typ="text" id="input" />
	<input type="button" id="button" value="Import Meddra"><br><br>
	
	<div id="0" style="display:none">
		Importing Low Level Terms :
		<tags:indicator id="0-indicator" />
		<span id="0-done" style="display:none">Done</span>
	</div>
	<div id="1" style="display:none">
		Importing Preferred Terms :
		<tags:indicator id="1-indicator" />
		<span id="1-done" style="display:none">Done</span>
	</div>
	<div id="2" style="display:none">
		Importing High Level Terms :
		<tags:indicator id="2-indicator" />
		<span id="2-done" style="display:none">Done</span>
	</div>
	<div id="3" style="display:none">
		Importing High Level Group Terms :
		<tags:indicator id="3-indicator" />
		<span id="3-done" style="display:none">Done</span>
	</div>
	<div id="4" style="display:none">
		Importing System Organ Classes :
		<tags:indicator id="4-indicator" />
		<span id="4-done" style="display:none">Done</span>
	</div>
	<div id="5" style="display:none">
		Importing High Level Term & Preferred Term relationships :
		<tags:indicator id="5-indicator" />
		<span id="5-done" style="display:none">Done</span>
	</div>
	<div id="6" style="display:none">
		Importing High Level Group Term & High Level Term relationships :
		<tags:indicator id="6-indicator" />
		<span id="6-done" style="display:none">Done</span>
	</div>
	<div id="7" style="display:none">
		Import System Organ Class & High Level Group Term relationships :
		<tags:indicator id="7-indicator" />
		<span id="7-done" style="display:none">Done</span>
	</div>

</body>