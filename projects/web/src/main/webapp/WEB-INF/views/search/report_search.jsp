<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<tags:stylesheetLink name="participant"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
        /* Override default lable length */
        div.row div.label { width: 13em; }
</style>
<title>${tab.longTitle}</title>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<tags:dwrJavascriptLink objects="search"/>

<script>
   
function buildTable(form) {	
	var type = "";
	var text = "";

	for(var x=0; x < 3; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  'prop'+x+',';
		}
	}
	var parameterMap = getParameterMap(form);
	search.getExpeditedReportTable(parameterMap, type, text, showTable);		
}

function showTable(table) {
	document.getElementById('tableDiv').innerHTML=table;
}
 
function fireAction(action, selected){	
	document.getElementById("_action").value=action;	
	document.getElementById("_selected").value=selected;
	document.searchForm.submit();	
} 

</script>
</head>
<body>

<chrome:box title=" Expedited Report search">
    <form:form name="searchForm" id="searchForm" method="post">
       <div>			
			<input type="hidden" name="_selected" id="_selected" value="">
			<input type="hidden" name="_action" id="_action" value="">
		</div>
		 <p class="instructions">
        	Search for studies by choosing study, participant criteria or both. 
    	</p>
    	
        <div class="content">
        
		    <div class="pane"> 
		    
		    <div class="row">
		    	<div class="label"> Ctc Term :&nbsp; </div>
		    	<div class="value"><input id="prop0" type="text"/></div>
		    </div>
		    
		     <div class="row">
		    	<div class="label"> Status :&nbsp; </div>
		    	<div class="value"><input id="prop1" type="text"/></div>
		    </div>
		    
		     <div class="row">
		    	<div class="label"> MedDRA Code :&nbsp; </div>
		    	<div class="value"><input id="prop2" type="text"/></div>
		    </div>
		    
		   
		
			</div>
		
		 	
		 	<div class="endpanes" />
			<div class="row">
			     <div class="labl"><input class='ibutton' type='button' onclick="buildTable('searchForm');" value='Search'  title='Search Study'/>
			</div>
        </div>
    </form:form>
</chrome:box>
<br>
<chrome:box title="Results">
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/> 
		</div>
	</chrome:division>
</chrome:box>
</body>
</html>