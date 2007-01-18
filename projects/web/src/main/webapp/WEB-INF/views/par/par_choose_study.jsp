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
	document.getElementById("command").submit();
}
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function doNothing(){
}

function updateTargetPage(s){
		document.checkEligibility.nextView.value=s;
		document.checkEligibility.submit();
}
</script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="titleArea">
	<form:form id="searchForm" method="post">
		<tr>
			<!-- TITLE STARTS HERE -->
			<td width="99%" height="43" valign="middle" id="title">Study
			Search</td>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="search">
				<tr>
					<td class="labels">&nbsp;</td>
				</tr>
				<tr>
					<td class="searchType">Search Study by <form:select
						path="searchType">
						<form:options items="${searchType}" itemLabel="desc"
							itemValue="code" />
					</form:select></td>
				</tr>
			</table>
			<span class="notation">&nbsp;</span></td>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="search">
				<tr>
					<td align="left" class="labels">Search String:</td>
					<td class="labels">&nbsp;</td>
				</tr>
				<tr>
					<td><form:input path="searchTypeText" size="25" /></td>
					<td><input type="submit" alignment="center" value="go" name="_target1" alt="GO" align="middle" width="22"
						height="10" border="0"></td>
				</tr>
			</table>
			<span class="notation">^ Minimum two characters for search.</span></td>
		</tr>
</table>
</form:form>
<form:form  method="post">
	<tags:tabFields tab="${tab}" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>

		<td class="display"><!-- TABS LEFT START HERE -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>

				<!-- LEFT CONTENT STARTS HERE -->

				<td valign="top" class="additionals"><!-- LEFT FORM STARTS HERE -->
				<br>
				<%--You have selected ${command.studyParticipantAssignment.studySite.study.shortTitle}--%>
				<ec:table items="command.studies" var="study"
					action="${pageContext.request.contextPath}/pages/newParticipant"
					imagePath="${pageContext.request.contextPath}/images/*.gif"
					title="Study Search Results" showPagination="false" form="command"
					cellspacing="0" cellpadding="0" border="0" width="80%" style=""
					styleClass="">
					<ec:row highlightRow="true">
						<ec:column property="kk" style="width:10px" filterable="false"
							sortable="false" title=" ">
							<form:checkbox path="studySiteArray" value="${study.studySites[0].id}" />
						</ec:column>
						<ec:column property="shortTitle" width="6" title="Short Title" />
						<ec:column property="longTitle" title="Long Title" />
						<ec:column property="description" title="Description" />
						<ec:column property="principalInvestigatorCode"
							title="InvestigatorCode" />
						<ec:column property="principalInvestigatorName"
							title="InvestigatorName" />
						<ec:column property="primarySponsorCode" title="Sponsor Code" />
						<ec:column property="primarySponsorName" title="Sponsor Name" />
					</ec:row>
				</ec:table> </form:form> <br>

				<!-- LEFT FORM ENDS HERE --></td>
				<!-- LEFT CONTENT ENDS HERE -->
			</tr>
		</table>

		</td>
	</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
