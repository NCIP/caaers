<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Review and Submit</title>
<tags:stylesheetLink name="participant"/>
<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("command").submit();
}

</script>
</head>
<body>


<tags:tabForm tab="${tab}" flow="${flow}" title="${command.lastName}, ${command.firstName}">
    <jsp:attribute name="instructions">
        Please verify this data and press Save to Create this Participant
    </jsp:attribute>
    <jsp:attribute name="singleFields">
        <input type="hidden" id="_finish" name="_finish"/>
        <br>
        
         <div class="leftpane">
	        <div class="row">
	            <div class="label">First Name:</div>
	            <div class="value">${command.firstName}</div>
	        </div>
	        
	         <div class="row">
	            <div class="label">Last Name:</div>
	            <div class="value">${command.lastName}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Maiden Name:</div>
	            <div class="value">${command.maidenName}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value">${command.middleName}</div>
	        </div>
	        
	         <div class="row">
	            <div class="label">Date of Birth:</div>
	            <div class="value"><tags:formatDate value="${command.dateOfBirth}"/></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Ethnicity:</div>
	            <div class="value">${command.ethnicity}</div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Race:</div>
	            <div class="value">${command.race}</div>
			</div>
	        
	        
	        <div class="row">
	            <div class="label">Gender:</div>
	            <div class="value">${command.gender}</div>
	        </div>
	              
	     </div>
	     
	     <div class="leftpane">
	     
	     	<div class="row"><div class="label"><strong>Assigned to Study</strong></div></div><br>
	     	<c:forEach var="studySite" items="${command.studySites}" varStatus="status"> 
               		 
					 <div class="row"><div class="label">Study Short Title:</div><div class="value"><c:out value="${studySite.study.shortTitle}"/></div></div>
					 <div class="row"><div class="label">Site:</div><div class="value"><c:out value="${studySite.site.name}"/></div></div>
			   </c:forEach>
			   
	     </div>   
    </jsp:attribute>
</tags:tabForm>
</body>