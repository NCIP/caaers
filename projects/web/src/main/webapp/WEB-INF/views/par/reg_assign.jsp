<!-- TODO: This view needs to be refactored. -->

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
<title> Search for a Study or Participant
<script>
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function search(s){

}
function submitPage(s){
	//document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm").submit();
}

function submitFormTwo(s){
	//document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm2").submit();
}
</script>
</head>
<body>
<p id="instructions">     
In order to assign a Participant to a Study , you need to first search for either a  participant or a study. 
</p>
	<form:form id="searchForm" name="searchForm" action="/caaers/pages/participant/assignStudy" method="post" cssClass="standard">
	<chrome:box autopad="true" cssClass="paired" title="Select Study" id="study-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="top">
							<td width="99%">
							<strong>1. Search Study by:</strong> <form:select
								path="studyType">
								<form:options items="${studySearchType}" itemLabel="desc"
									itemValue="code" />
							</form:select> <br>
							<br>
							<strong>2. Fill in the Fields:</strong><br>
							<br>
							<table border="0" cellspacing="0" cellpadding="0" id="search">
								<div id="foo">
								<tr><td align="left" class="labels">Search String:</td></tr>
								<tr><td><form:input path="studyText" size="25" /></td></tr>
								</div>
							</table>
							<br>
							<a href="" onClick="submitPage('study');return false;">Search Studies</a></td>
						</tr>
					</table>
	
	</chrome:box>
	</form:form>
	<form:form id="searchForm2" name="searchForm2" action="/caaers/pages/participant/assignParticipant" method="post" cssClass="standard">
	<chrome:box autopad="true" cssClass="paired" title="Select Participant" id="participant-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="top">
							<td width="99%">
							<strong>1. Search Participant by:</strong> <form:select
								path="participantType">
								<form:options items="${participantSearchType}" itemLabel="desc"
									itemValue="code" />
							</form:select> <br>
							<br>

							<strong>2. Fill in the Fields:</strong><br>
							<br>
							<table border="0" cellspacing="0" cellpadding="0" id="search">
								<tr>
									<td align="left" class="labels">Search String:</td>
								</tr>
								<tr>
									<td><form:input path="participantText" size="25" /></td>
								</tr>
							</table>
							<br>
							<a href="" onClick="submitFormTwo('participant');return false;">Search Participants</a></td>

							</td>
						</tr>
					</table>
	</chrome:box>
</form:form>
</body>
</html>