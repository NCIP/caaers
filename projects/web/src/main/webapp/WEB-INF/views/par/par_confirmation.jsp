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
                <td width="75%" valign="top">${command.firstName}</td>
            </tr>
            <tr>
                <td class="label">Last Name:</td>
                <td width="75%" valign="top">${command.lastName}</td>
            </tr>
            <tr>
                <td class="label">Middle Name:</td>
                <td>${command.middleName}</td>
            </tr>
            <tr>
                <td class="label"><strong>Maiden Name:</strong>
                <td valign="top">${command.maidenName}</td>
            </tr>
            <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
            </tr>
            <tr>
                <td class="label">Birth Date:</td>
                <td><tags:formatDate value="${command.dateOfBirth}"/></td>
            </tr>
            <tr>
                <td class="label">Ethnicity:</td>
                <td>${command.ethnicity}</td>
            </tr>
            <tr>
                <td class="label">Race:</td>
                <td>${command.race}</td>
            </tr>
            <tr>
                <td class="label">Gender:</td>
                <td>${command.gender}</td>
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
               <c:forEach var="studySite" items="${command.studySites}" varStatus="status">
               		<tr><td><strong><c:out value="${status.count}"/>.</strong></td></tr>
					<tr><td class="label">Study Short Title:</td><td><c:out value="${studySite.study.shortTitle}"/></td></tr>
					<tr><td class="label">Site:</td><td><c:out value="${studySite.site.name}"/></td></tr>
					</tr>
			   </c:forEach>
        </table>
        </td>
        </tr>
   </table> 
    </jsp:attribute>
</tags:tabForm>
</body>