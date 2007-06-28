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

	for(var x=0; x < 7; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  'prop'+x+',';
		}
	}
	search.getTable(null, type, text, showTable,0);		
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

<chrome:box title="Study search">
    <form:form name="searchForm" id="searchForm" method="post">
       <div>			
			<input type="hidden" name="_selected" id="_selected" value="">
			<input type="hidden" name="_action" id="_action" value="">
		</div>
		 <p class="instructions">
        	Search for studies by choosing study, participant criteria or both. 
    	</p>
        <div class="content">
        	<%--
   			 <div class="row" align="center">
   			 
               <div class="label">Search By Study: <a href="javascript:fireAction('addCriteria',-1);"><img
					src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></div>
		      </div>
		      --%>
		    <div class="pane"> 
		    
		    <div class="row">
		    	<div class="label"> Study Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop0" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Study Short Title :&nbsp; </div>
		    	<div class="value"><input id="prop1" type="text" name="shortTitle"/></div>
		    </div>
		    
		    <%--
		    <div class="row">
		    	<div class="label"> Study Identifier :&nbsp; </div>
		    	<div class="value"><input id="prop3" type="text" name="studyIdentifier"/></div>
		    </div>
		    
		    
        	<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs">
            	<div class="label">Search By Study:</div>
                <div class="value"><form:select path="searchCriteria[${status.index}].searchType">
					<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
				</form:select>
				<form:input path="searchCriteria[${status.index}].searchText" size="10"/>				
					<a href="javascript:fireAction('removeCriteria', ${status.index});"><img
					src="/caaers/images/checkno.gif" border="0" alt="remove"></a></div>						
			</div>
			</c:forEach>
			
			<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs">
            	<div class="label">Search By Study:</div>
                <div class="value"><form:select path="searchCriteria[${status.index}].searchType">
					<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
				</form:select>
				<form:input path="searchCriteria[${status.index}].searchText" size="10"/>				
					<a href="javascript:fireAction('removeCriteria', ${status.index});"><img
					src="/caaers/images/checkno.gif" border="0" alt="remove"></a></div>						
			</div>
			</c:forEach>
			--%>
			</div>
			
			<div class="pane">
		 	
		 	<%--
		 	<div class="row" align="center">
   			 
               <div class="label">Search By Participant: <a href="javascript:fireAction('addCriteria',-1);"><img
					src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></div>
		    </div>
		    --%>
		     <div class="row">
		    	<div class="label"> Participant ID :&nbsp; </div>
		    	<div class="value"><input id="prop2" type="firstName"/></div>
		    </div>
		    
		    
		     <div class="row">
		    	<div class="label"> Participant First Name :&nbsp; </div>
		    	<div class="value"><input id="prop3" type="firstName"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Participant Last Name :&nbsp; </div>
		    	<div class="value"><input id="prop4" type="text" name="lastName"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Participant Ethnicity :&nbsp; </div>
		    	<div class="value"><input id="prop5" type="text" name="ethnicity"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Participant Gender :&nbsp; </div>
		    	<div class="value"><input id="prop6" type="text" name="gender"/></div>
		    </div>
		 	
		 	<%--
		 	<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs">
            	<div class="label">Search By Participant:</div>
                <div class="value"><form:select path="searchCriteria[${status.index}].searchType">
					<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
				</form:select>
				<form:input path="searchCriteria[${status.index}].searchText" size="10"/>				
					<a href="javascript:fireAction('removeCriteria', ${status.index});"><img
					src="/caaers/images/checkno.gif" border="0" alt="remove"></a></div>						
			</div>
		 	</c:forEach>
		 	--%>
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
<form:form>
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/> 
		</div>
	</chrome:division>
</form:form>
</chrome:box>
</body>
</html>