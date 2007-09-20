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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>${tab.longTitle}</title>
<tags:javascriptLink name="extremecomponents"/>
<tags:dwrJavascriptLink objects="searchStudy"/>

<script>
   
function buildTable(form) {		
	var r = document.getElementsByName("inputs");	
	var type = "";
	var text = "";
	for(var x=0; x < r.length; x++) {
		if(x==0)
		{
			type = document.getElementById("searchCriteria[" + x + "].searchType").value;
			text = document.getElementById("searchCriteria[" + x + "].searchText").value;			
		}
		else 
		{
			type = type + "," + document.getElementById("searchCriteria[" + x + "].searchType").value;
			text = text + "," + document.getElementById("searchCriteria[" + x + "].searchText").value;			
		}
	}

	searchStudy.getTable(null, type, text, showTable);		
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

<chrome:box title="Search" autopad="true">
    <form:form name="searchForm" id="searchForm" method="post">
       <div>			
			<input type="hidden" name="_selected" id="_selected" value="">
			<input type="hidden" name="_action" id="_action" value="">
		</div>
        <div class="content">
   			 <div class="row" align="center">
               <div class="label">Add Criteria<a href="javascript:fireAction('addCriteria',-1);"><img
					src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></div>
		      </div>
        	<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs">
            	<div class="label"> Search By: </div>
                <div class="value"><form:select path="searchCriteria[${status.index}].searchType">
					<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
				</form:select>
				<form:input path="searchCriteria[${status.index}].searchText" size="25"/>				
					<a href="javascript:fireAction('removeCriteria', ${status.index});"><img
					src="/caaers/images/checkno.gif" border="0" alt="remove"></a></div>						
			</div>
		 	</c:forEach>
			<div class="row">
			     <div class="lable"><input class='ibutton' type='button' onclick="buildTable('searchForm');" value='Search'  title='Search Study'/>
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