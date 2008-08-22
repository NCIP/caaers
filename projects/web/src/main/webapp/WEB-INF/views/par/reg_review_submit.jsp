<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
<p id="instructions">
   Please review the information and press Save to assign the Subject <b>${command.participant.lastName}, ${command.participant.firstName}</b>
   to Study <b>${command.studySites[0].study.shortTitle}</b>
</p>
<tags:tabForm tab="${tab}" flow="${flow}" title="${command.participant.lastName}, ${command.participant.firstName}" >
    <jsp:attribute name="singleFields">
        <input type="hidden" id="_finish" name="_finish"/>

        <table cellpadding="0" border="1" cellspacing="4" width="100%">
			<tr valign="top">
				<td valign="top" width="50%" align="left">
				<chrome:division title="Subject Details">
		      		<div class="row">
      					<div class="label">First name</div>
      					<div class="value">${command.participant.firstName}</div>
      				</div>
      		
      		<div class="row">
      			<div class="label">Last name</div>
      			<div class="value">${command.participant.lastName}</div>
      		</div>
      		
      		<div class="row">
      			<div class="label">Middle name</div>
      			<div class="value">${command.participant.middleName}</div>
      		</div>
      		
      		<div class="row">
      			<div class="label">Maiden name</div>
      			<div class="value">${command.participant.maidenName}</div>
      		</div>
      		
      		
      		<div class="row">
      			<div class="label">Birth date</div>
      			<div class="value">${command.participant.dateOfBirth}</div>
      		</div>
      		
      		<div class="row">
      			<div class="label">Ethnicity</div>
      			<div class="value">${command.participant.ethnicity}</div>
      		</div>
      		
      		<div class="row">
      			<div class="label">Race</div>
      			<div class="value">${command.participant.race}</div>
      		</div>
      		
      		<div class="row">
      			<div class="label">Gender</div>
      			<div class="value">${command.participant.gender}</div>
      		</div>
			</chrome:division>	
				</td>
				
				<td valign="top" width="50%">
					<chrome:division title="Study Details">
					   <c:forEach var="studySite" items="${command.studySites}" varStatus="status">
      					<div class="row">
      						<div class="label">Study primary ID</div>
      						<div class="value">${studySite.study.primaryIdentifier ne null ? studySite.study.primaryIdentifier.value : ''}</div>
      					</div>
      		
      					<div class="row">
      						<div class="label">Study short title</div>
      						<div class="value">${studySite.study.shortTitle}</div>
      					</div>
      					<div class="row">
      						<div class="label">Study long title</div>
      						<div class="value">${studySite.study.longTitle}</div>
      					</div>
      		
      					<div class="row">
      						<div class="label">Site</div>
      						<div class="value">${studySite.organization.name}</div>
      					</div>
      					<img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
							height="1" class="heightControl">
					   </c:forEach>
                        <div class="row">
                            <div class="label">Study subject identifer </div>
                            <div class="value">${command.studySubjectIdentifier}</div>
                        </div>

                    </chrome:division>
				</td>
			</tr>
		</table>
        
    </jsp:attribute>
</tags:tabForm>


</body>