<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
        /* Override default lable length */
         div.row div.label { width: 9em; } 
        div.row div.value { margin-left: 10em; }
</style>
<title>${tab.longTitle} Study search</title>
<tags:dwrJavascriptLink objects="search"/>

<script>

function buildTable(form) {
	$('indicator').className=''
	showCoppaSearchDisclaimer();
	var type = "";
	var text = "";

	for(var x=0; x < 8; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  'prop'+x+',';
		}
	}
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);		
	search.getTable(parameterMap,type,text,showTable);
}


</script>
</head>
<body>

<form:form name="searchForm" id="searchForm" method="post">
<p><tags:instructions code="advancedstudysearch" /></p>  
<chrome:box title="Study Criteria" cssClass="paired" autopad="true">
		    <div class="row">
		    	<div class="label"> Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop0" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Short Title :&nbsp; </div>
		    	<div class="value"><input id="prop1" type="text" name="shortTitle"/></div>
		    </div>
</chrome:box>

<chrome:box title="Subject Criteria" cssClass="paired" autopad="true">
		     <div class="row">
		    	<div class="label"> Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop2" type="text" name="firstName"/></div>
		    </div>
		    
		    
		     <div class="row">
		    	<div class="label"> First Name :&nbsp; </div>
		    	<div class="value"><input id="prop3" type="text" name="firstName"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Last Name :&nbsp; </div>
		    	<div class="value"><input id="prop4" type="text" name="lastName"/></div>
		    </div>
		    
		    
		    <div class="row">
		    	<div class="label"> Ethnicity :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop5_select','prop5')" id="prop5_select">
		    		<c:forEach items="${ethnicity}" varStatus="status" var="field">
		    			<option>${field.code}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop5" type="hidden" name="gender"/>
		    	</div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Gender :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop6_select','prop6')" id="prop6_select">
		    		<c:forEach items="${genders}" varStatus="status" var="field">
		    			<option>${field.code}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop6" type="hidden" name="gender"/>
		    	</div>
		    </div>
		 	
		 	<div class="row">
		    	<div class="label"> Birth Date :&nbsp; </div>
		    	<div class="value"><input id="prop7" type="text" name="dateOfBirth"/></div>
		    </div>
</chrome:box>

<div class="endpanes" />
<div class="row" style="float:right;">
    <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler');"/>
    <tags:indicator id="indicator" />
    <span id="coppa-search-disclaimer" class="coppa-search-disclaimer" style="display:none;"><caaers:message code="coppa.search.message" /></span>
</div>
<div class="endpanes" />


 </form:form>
<br>


<div id="bigSearch" style="border:0px green dotted; display:none;">
<form:form id="assembler" >
<div>
	<input type="hidden" name="_prop" id="prop" >
	<input type="hidden" name="_value" id="value"  >
</div>
<chrome:box title="Results">
	
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/> 
		</div>
	</chrome:division>
</chrome:box>
</form:form>
</div>    
</body>
</html>