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
         div.row div.label { width: 9em; } 
         div.row div.value { margin-left: 10em; }
         input { width:8em; }
         
</style>
<title>${tab.longTitle} Expedited report search</title>
<tags:javascriptLink name="extremecomponents"/>
<tags:dwrJavascriptLink objects="search"/>

<script>
   
function buildTable(form) {	
	$('indicator').className=''
	var type = "";
	var text = "";

	for(var x=0; x < 12; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  'prop'+x+',';
		}
	}
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);
	search.getExpeditedReportTable(parameterMap, type, text, showTable);		
}

</script>
</head>
<body>

<form:form name="searchForm" id="searchForm" method="post">
<p>
  <tags:instructions code="advancedreportsearch" />
</p>
<chrome:box title="Study Criteria" autopad="true">
		    <div class="row">
		    	<div class="label"> Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop4" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Short Title :&nbsp; </div>
		    	<div class="value"><input id="prop5" type="text" name="shortTitle"/></div>
		    </div>
</chrome:box>

<chrome:box title="Expedited AE Report Criteria" cssClass="paired" autopad="true">

			<div class="row">
		    	<div class="label">Start Date :&nbsp; </div>
		    	<div class="value"><input id="prop0" type="text"/></div>
		    </div>
				
		    <div class="row">
		    	<div class="label"> CTC Term :&nbsp; </div>
		    	<div class="value"><input id="prop1" type="text"/></div>
		    </div>
		    
		   <div class="row">
		    	<div class="label"> CTC Category :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop2_select','prop2')" style="width:150px" id="prop2_select">
		    			<option>---</option>
		    		<c:forEach items="${ctcCategories}" varStatus="status" var="field">
		    			<option>${field.name}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop2" type="hidden" name="gender"/>
		    	</div>
		    </div>

		     <div class="row">
		    	<div class="label"> MedDRA Code :&nbsp; </div>
		    	<div class="value"><input id="prop3" type="text"/></div>
		    </div>
</chrome:box>

<chrome:box title="Subject Criteria" cssClass="paired" autopad="true">
		     <div class="row">
		    	<div class="label"> Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop6" type="text" name="identifier"/></div>
		    </div>
		    
		    
		     <div class="row">
		    	<div class="label"> First Name :&nbsp; </div>
		    	<div class="value"><input id="prop7" type="text" name="firstname"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Last Name :&nbsp; </div>
		    	<div class="value"><input id="prop8" type="text" name="lastName"/></div>
		    </div>
		    
		    
		    <div class="row">
		    	<div class="label"> Ethnicity :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop9_select','prop9')" style="width:150px" id="prop9_select">
		    		<c:forEach items="${ethnicity}" varStatus="status" var="field">
		    			<option>${field.code}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop9" type="hidden" name="gender"/>
		    	</div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Gender :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop10_select','prop10')" id="prop10_select">
		    		<c:forEach items="${genders}" varStatus="status" var="field">
		    			<option>${field.code}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop10" type="hidden" name="gender"/>
		    	</div>
		    </div>
		 	
		 	<div class="row">
		    	<div class="label"> Birth Date :&nbsp; </div>
		    	<div class="value"><input id="prop11" type="text" name="dateOfBirth"/></div>
		    </div>
</chrome:box>

<div class="endpanes" />
<div class="row" style="float:right;">
	<input class='ibutton' type='button' onclick="buildTable('assembler');  $('bigSearch').show();" value='Search'  title='Search Study'/>
	<tags:indicator id="indicator" />
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