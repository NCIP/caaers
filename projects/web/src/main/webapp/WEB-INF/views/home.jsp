<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
<style type="text/css">
        /* TODO: all these are temporary */
        .division {
            float: left;
            width: 45%;
            margin: 1em;
        }
        .division-content {
            padding: 1em;
        }
</style>
<title> Search for a Study or Participant  
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="resources/styles.css" rel="stylesheet" type="text/css">
<link href="resources/search.css" rel="stylesheet" type="text/css">
<script>
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function search(s){

}
function submitPage(s){
	document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm").submit();
}
</script>
</head>
<body>
<br><br>
	<form:form id="searchForm" name="searchForm" action="/caaers/pages/searchRegister" method="post">
	<form:hidden path="searchCategory" />
	<chrome:division title="Select Study" id="study-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="top">
							<td width="99%">
							<h3>Study Search</h3>
							<strong>1. Search Study by:</strong> <form:select
								path="searchType">
								<form:options items="${searchType}" itemLabel="desc"
									itemValue="code" />
							</form:select> <br>
							<br>
							<strong>2. Fill in the Fields:</strong><br>
							<br>
							<table border="0" cellspacing="0" cellpadding="0" id="search">
								<div id="foo">
								<tr><td align="left" class="labels">Search String:</td></tr>
								<tr><td><form:input path="searchTypeText" size="25" /></td></tr>
								</div>
							</table>
							<br>
							<a href="" onClick="submitPage('study');return false;">Search Studies</a></td>
						</tr>
					</table>
	
	</chrome:division>
	<chrome:division title="Select Participant" id="participant-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="top">
							<td width="99%">
							<h3>Participant Search</h3>
							<strong>1. Search Participant by:</strong> <form:select
								path="searchTypePart">
								<form:options items="${searchTypePart}" itemLabel="desc"
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
									<td><form:input path="searchTypeTextPart" size="25" /></td>
								</tr>
							</table>
							<br>
							<a href="" onClick="submitPage('participant');return false;">Search Participants</a></td>

							</td>
						</tr>
					</table>
	</chrome:division>
</form:form>
</body>
</html>