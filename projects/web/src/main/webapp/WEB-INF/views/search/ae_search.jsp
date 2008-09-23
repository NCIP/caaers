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
         .box.paired { width: 30%; }
</style>
<title>${tab.longTitle}</title>
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
	search.getAdverseEventTable(parameterMap, type, text, showTable);		
}

</script>
</head>
<body>


<form:form name="searchForm" id="searchForm" method="post">
<p>
  Search for AEs by choosing any of the listed criteria.
</p>

<chrome:box title="AE Criteria" cssClass="paired" autopad="true">
		     <div class="row">
		    	<div class="label"> CTC Category :&nbsp; </div>
		    	<div class="value">
		    	<select onChange="copyValues('prop0_select','prop0')" style="width:150px" id="prop0_select">
		    			<option>---</option>
		    		<c:forEach items="${ctcCategories}" varStatus="status" var="field">
		    			<option>${field.name}</option>
		    		</c:forEach>
		    	</select>
		    	<input id="prop0" type="hidden" name="gender"/>
		    	</div>
		    </div>
		      
		       <div class="row">
		    	<div class="label"> CTC Term :&nbsp; </div>
		    	<div class="value"><input  class="ae" id="prop1" type="firstName"/></div>
		      </div>
		      
		       <div class="row">
		    	<div class="label"> MedDRA Code :&nbsp; </div>
		    	<div class="value"><input   class="ae" id="prop2" type="firstName"/></div>
		      </div>
		      
		       <div class="row">
		    	<div class="label"> Grade :&nbsp; </div>
		    	<div class="value"><input  class="ae"  id="prop3" type="firstName"/></div>
		      </div>
			
</chrome:box>


<chrome:box title="Study Criteria" cssClass="paired" autopad="true">
		    
		     <div class="row">
		    	<div class="label"> Identifier :&nbsp;</div>
		    	<div class="value"><input class="ae" id="prop4" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Short Title :&nbsp; </div>
		    	<div class="value"><input  class="ae" id="prop5" type="text" name="longTitle"/></div>
		    </div>
		   
</chrome:box>


<chrome:box title="Subject Criteria" cssClass="paired" autopad="true">
		        <div class="row">
		    	<div class="label"> Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop6" type="firstName"/></div>
		    </div>
		    
		    
		     <div class="row">
		    	<div class="label"> First Name :&nbsp; </div>
		    	<div class="value"><input id="prop7" type="firstName"/></div>
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
	<input class='ibutton' type='button' onclick="buildTable('assembler');" value='Search'  title='Search Study'/>
	<tags:indicator id="indicator" />
</div>
<div class="endpanes" />
 </form:form>
<br>			

<form:form id="assembler" >


<div>			
	<input type="hidden" name="_prop" id="prop" >
	<input type="hidden" name="_value" id="value"  >
</div>


<chrome:box title="AE search results">
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/> 
		</div>
	</chrome:division>
</chrome:box>
</form:form>
</body>
</html>