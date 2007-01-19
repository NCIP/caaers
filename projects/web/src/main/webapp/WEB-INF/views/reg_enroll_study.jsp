<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
function getPage(s){
	parent.window.location="reg_patient_search.htm";
}
function submitPage(){
	document.getElementById("searchStudy").submit();
}
</script>
</head>
<body>

<!-- MAIN BODY STARTS HERE -->
<form:form id="searchStudy" name="searchParticipant" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="titleArea">
	<tr>
		<!-- TITLE STARTS HERE -->
		<td width="99%" height="43" valign="middle" id="title"><a href="/caaers/pages/study/create">Create
						new Study for Registration</a> or search for an existing
						study.</td>

		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			id="search">
			<tr>
				<td class="labels">&nbsp;</td>
			</tr>
			<!--
			<tr>
				<td class="searchType">Search <select name="select"
					class="field1">

					<option selected>Participant</option>
					<option>Protocol</option>
				</select> by <select name="select" class="field1">
					<option selected>Participant Name</option>
					<option>Participant Registration#</option>
				</select></td>

			</tr>
			-->
		</table>
		<span class="notation">&nbsp;</span></td>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			id="search">
			<tr>
				<td align="left" class="labels"><span class="notation"><span
					class="red">*</span><em></em></span>Search Study by:</td>

				<td align="left" class="labels">Search String:</td>
				<td class="labels">&nbsp;</td>
			</tr>
			<tr>
				<td>
				    <form:select path="searchType">
					  <form:options items="${searchType}" itemLabel="desc" itemValue="code" />
					</form:select>
				</td>
				<td><form:input path="searchTypeText" size="25" /></td>
				<td><input name="imageField" type="image" class="button"
					onClick="submitPage()" src="/images/chrome/b-go.gif" alt="GO"
					align="middle" width="22" height="10" border="0"></td>
			</tr>

		</table>
		<span class="notation"><span class="labels">(<span
			class="red">*</span><em>Required Information </em>)</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;^
		Minimum two characters for search.</span></td>
	</tr>
</table>
</form:form>
</body>
</html>
