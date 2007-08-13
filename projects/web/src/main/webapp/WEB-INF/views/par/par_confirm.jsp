<!-- TODO: This view is virtually identical to the last screen of the create
    participant flow.  Factor out their commonalities. -->

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
<title>View Participant</title>
<tags:stylesheetLink name="participant"/>
<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("participant").submit();
}

</script>
</head>
<body>
<p id="instructions">
   You have successfully created a new Participant
</p>
<chrome:box title="${participant.lastName}, ${participant.firstName}" autopad="true">
    
    
<chrome:division id="single-fields">

  <div class="leftpane">
	        <div class="row">
	            <div class="label">First Name:</div>
	            <div class="value">${participant.firstName}</div>
	        </div>
	        
	         <div class="row">
	            <div class="label">Last Name:</div>
	            <div class="value">${participant.lastName}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Maiden Name:</div>
	            <div class="value">${participant.maidenName}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value">${participant.middleName}</div>
	        </div>
	        
	         <div class="row">
	            <div class="label">Date of Birth:</div>
	            <div class="value"><tags:formatDate value="${participant.dateOfBirth}"/></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Ethnicity:</div>
	            <div class="value">${participant.ethnicity}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Race:</div>
	            <div class="value">${participant.race}</div>
			</div>
	        
	        
	        <div class="row">
	            <div class="label">Gender:</div>
	            <div class="value">${participant.gender}</div>
	        </div>
	              
	     </div>
	     
	     <div class="leftpane">
	   
	     	<div class="row"><div class="label"><strong>Assigned to Study</strong></div></div><br>
	     	<c:forEach var="assignment" items="${participant.assignments}" varStatus="status"> 
               		 
					 <div class="row"><div class="label">Study Short Title:</div><div class="value"><c:out value="${assignment.studySite.study.shortTitle}"/></div></div>
					 <div class="row"><div class="label">Site:</div><div class="value"><c:out value="${assignment.studySite.organization.name}"/></div></div>
			   </c:forEach>
			   
	     </div>
	     
	     <div class="endpanes">&nbsp;</div>   

  </chrome:division>
  
		<c:if test="${not empty participant.identifiers}">
			<chrome:division title="Identifiers" id="repeatingFields">
			<table class="tablecontent">
			<tr>
				<th scope="col">Assigning Authority</th>
				<th scope="col">Identifier Type</th>
				<th scope="col">Identifier</th>
			</tr>
			<c:forEach items="${participant.identifiers}" var="identifier">
			<tr class="results">
				<c:if
								test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
					<td>${identifier.organization}</td>
				</c:if>
				<c:if
								test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier') }">
					<td>${identifier.systemName}</td>
				</c:if>
				<td>${identifier.type}</td>
				<td>${identifier.value}</td>
			</tr>
			</c:forEach>
			</table>
			<br>
			</chrome:division>
			
		</c:if>

<%--
        <br>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="split-pane">
    	<tr>
    	<td width="30%" valign="top" class="contentAreaL">
        <table border="0" cellspacing="0" cellpadding="0"
               class="table1">
               <strong>Participant Details</strong>
            <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
            </tr>
            <tr>
                <td class="label">First Name:</td>
                <td width="75%" valign="top">${participant.firstName}</td>
            </tr>
            <tr>
                <td class="label">Last Name:</td>
                <td width="75%" valign="top">${participant.lastName}</td>
            </tr>
            <tr>
                <td class="label">Middle Name:</td>
                <td>${participant.middleName}</td>
            </tr>
            <tr>
                <td class="label">Maiden Name:</td>
                <td valign="top">${participant.maidenName}</td>
            </tr>
            <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
            </tr>
            <tr>
                <td class="label">Birth Date:</td>
                <td><tags:formatDate value="${participant.dateOfBirth}"/></td>
            </tr>
            <tr>
                <td class="label">Ethnicity:</td>
                <td>${participant.ethnicity}</td>
            </tr>
            <tr>
                <td class="label">Race:</td>
                <td>${participant.race}</td>
            </tr>
            <tr>
                <td class="label">Gender:</td>
                <td>${participant.gender}</td>
            </tr>
        </table>
        </td>
        
        <td width="50%" valign="top" class="contentAreaL">
        <table border="0" cellspacing="0" cellpadding="0"
               class="table1">
               <strong>Assigned to Study</strong>
               <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
               </tr>
               <c:forEach var="assignments" items="${participant.assignments}" varStatus="status">
               		<tr><td><strong><c:out value="${status.count}"/>.</strong></td></tr>
					<tr><td class="label">Study Short Title:</td><td><c:out value="${assignments.studySite.study.shortTitle}"/></td></tr>
					<tr><td class="label">Site:</td><td><c:out value="${assignments.studySite.organization.name}"/></td></tr>
					</tr>
			   </c:forEach>
        </table>
        </td>
        </tr>
   </table>    
   
   --%>
</chrome:box>
</body>