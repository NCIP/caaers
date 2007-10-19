<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:stylesheetLink name="participant" />
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
<style type="text/css">
        div.content {
            padding: 5px 15px;
        }
</style>

<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("command").submit();
}

</script>
</head>
<body>

<div class="tabpane">
  <ul id="workflow-tabs" class="tabs autoclear">
    <li class="tab selected">
    <div><a href="createInvestigator">Add/Edit Investigator</a></div>
    </li>
    <li class="tab "><div>
        <a href="searchInvestigator">Search Investigator</a>
    </div></li>
  </ul>
</div>
<div style="height:10px"></div>
<chrome:flashMessage key="statusMessage" />
<chrome:box title="${investigator.lastName}, ${investigator.firstName}" >
<chrome:division title="Investigator Details">
 <div class="leftpanel">
 <div class="row">
	<div class="label">First Name:</div>
	<div class="value">${investigator.firstName}</div>
 </div>
	        
 <div class="row">
    <div class="label">Last Name:</div>
    <div class="value">${investigator.lastName}</div>
 </div>
	        
 <div class="row">
    <div class="label">Middle Name:</div>
    <div class="value">${investigator.middleName}</div>
 </div>
 </div>

 <div class="rightpanel">
   <div class="row">
	<div class="label">NCI Identifier:</div>
	<div class="value">${investigator.nciIdentifier} </div>
   </div>
   <div class="row">
	 <div class="label">Email address:</div>
	 <div class="value">${investigator.emailAddress}</div>
   </div>
	        
   <div class="row">
	 <div class="label">Phone:</div>
	 <div class="value">${investigator.phoneNumber}</div>
   <div>
   <div class="row">
	 <div class="label">Fax:</div>
	 <div class="value">${investigator.faxNumber}</div>
   </div>
 
 </div>
</chrome:division>
	
 <c:if test="${not empty investigator.siteInvestigators}">
   <chrome:division title="Associate Sites">
	 <table class="tablecontent" width="70%">
	  <tr>
		 <th scope="col">Site</th>
		 <th scope="col">Status type</th>
	  </tr>
	  <c:forEach items="${investigator.siteInvestigators}" var="siteInvestigator">
		<tr class="results">
			<td>${siteInvestigator.organization}</td>
		    <td>${siteInvestigator.statusCode eq 'ACT' ? 'Active' : 'Inactive' }</td>
		</tr>
	  </c:forEach>
	  </table>
	</chrome:division>
 </c:if>
</chrome:box>

</body>