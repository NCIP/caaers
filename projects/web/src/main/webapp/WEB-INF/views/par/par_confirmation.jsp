<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>caAERS</title>
<link href="resources/styles.css" rel="stylesheet" type="text/css">
<link href="resources/search.css" rel="stylesheet" type="text/css">
<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("command").submit();
}

</script>
</head>
<body>
<form:form method="post">
<tags:tabFields tab="${tab}" />
<b>Participant</b> <br><br>
Institutional Number     : <b>${command.instituitionalPatientNumber}</b><br>
Institution              : <b>${command.institution}</b><br>
Participant First Name   : <b>${command.firstName}</b><br>
Participant Middle Name  : <b>${command.middleName}</b><br>
Participant Maiden Name  : <b>${command.maidenName}</b><br>
Participant Date of Birth: <b>${command.dateOfBirth}</b><br>
Participant Gender       : <b>${command.gender}</b><br>
Participant Race         : <b>${command.race}</b><br>
Particiapnt Ethnicity    : <b>${command.ethnicity}</b><br><br>

<b>Study / Site</b><br><br>
<c:forEach var="studySite" items="${command.studySites}" varStatus="status">
Study Short Title        : <b>${studySite.study.shortTitle}</b><br>
Site                     : <b>${studySite.site.name}</b><br>
</c:forEach>

<!-- For Hibernate to initialize these lists so when we -->
<!-- go back to the previous page the list would load correctly -->
<c:forEach var="study" items="${command.studies}" varStatus="status">
<input type="hidden" name="test" value="${study.studySites[0].id}"/>
</c:forEach>

<br>
<input type="hidden" id="nextView" name="nextView"/>
<a href="javascript:submitPage('processFinish')">Finish</a> 
</form:form>

</body>