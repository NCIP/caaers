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
<chrome:body title="Review and Submit">
<form:form method="post">
<br><br>
<b>Participant</b><br><br>
<table width="70%" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td width="50%" valign="top">
							<table width="308" border="0" cellspacing="0" cellpadding="0"
								id="table1">
								<tr>
									<td class="label">First
									Name:</td>
									<td><b>${command.firstName}</b><br></td>
								</tr>
								<tr>
									<td class="label">Last
									Name:</td>
									<td><b>${command.lastName}</b><br></td>
								</tr>
								<tr>
									<td class="label">Middle
									Name:</td>
									<td><b>${command.middleName}</b><br></td>
								</tr>
								<tr>
									<td class="label"><em></em>Maiden
									Name:</td>
									<td><b>${command.maidenName}</b><br></td>
								</tr>
							</table>
							</td>
							<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
							<table width="308" border="0" cellspacing="0" cellpadding="0"
								id="table1">
								<tr>
									<td class="label">Birth
									Date:</td>
									<td valign="top"><b>${command.dateOfBirth}</b><br></td>
								</tr>
								<tr>
									<td class="label"><em></em>Ethnicity:
									</td>
									<td><b>${command.ethnicity}</b><br></td>
								</tr>
								<tr>
									<td class="label"><em></em>Race:</td>
									<td><b>${command.race}</b><br></td>
								</tr>
								<tr>
									<td class="label"><em></em>Gender:
									</td>
									<td><b>${command.gender}</b><br></td>

								</tr>
							</table>
							</td>
						</tr>
					</table><br><br><br>

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
</chrome:body>
</body>