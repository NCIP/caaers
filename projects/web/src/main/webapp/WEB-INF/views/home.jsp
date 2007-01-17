<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>caAERS</title>
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

<!-- TOP NAVIGATION STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="topNav">
	<tr valign="middle">
		<td>Registration | Study | Participant | Reports | <a href="logOff">LogOff</a></td>
	</tr>
</table>
<!-- TOP NAVIGATION ENDS HERE -->


<!-- SUB NAV STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="subNav">
	<tr>
		<td width="99%" valign="middle">&nbsp;</td>
		<td valign="middle" class="right"><a href="help">Help</a></td>
	</tr>
</table>
<!-- SUB NAV ENDS HERE -->

<!-- MAIN BODY STARTS HERE -->
<div class="workArea">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="titleArea">
	<tr>
		<!-- TITLE STARTS HERE -->
		<td width="99%" height="43" valign="middle" id="title">Welcome User</td>
		<!-- TITLE ENDS HERE -->
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<form:form id="searchForm" name="searchForm" method="post">
		<form:hidden path="searchCategory" />	
		<%--<tr><!-- CURRENT PROTOCOL/Subject TITLE STARTS HERE -->
			<!-- <td id="current">Search</td>-->
			<!-- CURRENT PROTOCOL/Subject TITLE ENDS HERE --></tr>--%>
		<tr>
			<td class="display"><!-- TABS LEFT START HERE -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="50%">
					
					<!-- TABS LEFT END HERE --></td>
					<td><img src="images/spacer.gif" width="2" height="1"></td>
					<td width="50%"><!-- TABS RIGHT START HERE -->
					
					<!-- TABS RIGHT END HERE --></td>
				</tr>
				<tr>
					<!-- LEFT CONTENT STARTS HERE -->
					<td valign="top" class="searchL"><!-- LEFT FORM STARTS HERE -->
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

							^ Minimum two characters for Study Name search.<br>
							<br>
							<a href="" onClick="submitPage('study');return false;"><img
								src="images/SerachProtocols.gif" alt="Search Studies"
								width="100" height="16" border="0"></a></td>
						</tr>
					</table>
					<!-- LEFT FORM ENDS HERE --></td>
					<!-- LEFT CONTENT ENDS HERE -->
					<td><img src="images/spacer.gif" width="2" height="1"></td>
					<td valign="top" class="searchR">
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
							<a href="" onClick="submitPage('participant');return false;"><img
								src="images/SerachPatients.gif" alt="Search Participants" width="100"
								height="16" border="0"></a></td>

							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</form:form>
</table>
<div id="copyright"></div>
</div>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>