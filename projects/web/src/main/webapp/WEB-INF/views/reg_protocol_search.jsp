<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
<title> Choose a Study </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/css/common.css" rel="stylesheet" type="text/css">
<link href="/css/common-search.css" rel="stylesheet" type="text/css">
<tags:stylesheetLink name="tabbedflow"/>
<tags:javascriptLink name="tabbedflow"/>
<script>
function submitPage(s){
	document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm").submit();
}
</script>
</head>
<body>

<!-- MAIN BODY STARTS HERE -->
<chrome:search title="">
<form:form id="searchForm" name="searchForm" method="post">
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
					onClick="submitPage('protocol');return false;" src="/images/chrome/b-go.gif" alt="GO"
					align="middle" width="22" height="10" border="0"></td>
			</tr>

		</table>
		<span class="notation"><span class="labels">(<span
			class="red">*</span><em>Required Information </em>)</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;^
		Minimum two characters for search.</span></td>
	</tr>
</table>
</form:form>
</chrome:search>

<chrome:body title="Study Search results">

<!-- TITLE/QUICK SEARCH AREA ENDS HERE --> <!-- CONTENT AREA STARTS HERE -->
	<span class="notation"><span class="labels">Please Select one of the studies to proceed</span></span>
	<ec:table 
    	items="studies"
    	var="study" 
    	action="${pageContext.request.contextPath}/pages/home" 
    	imagePath="${pageContext.request.contextPath}/images/table/*.gif"
    	showPagination="false"
    	cellspacing="0" cellpadding="0" border="0" width="80%" style="" styleClass="">
    	<ec:row highlightRow="true">
        <ec:column property="shortTitle" width="6" title="Short Title">
           <a href="/caaers/pages/participant/create?studySiteId=${study.studySites[0].id}">${study.shortTitle}</a>
        </ec:column>
        <ec:column property="longTitle" title="Long Title" />
        <ec:column property="description" title="Description" />
        <ec:column property="precis" title="Precis" />
        <ec:column property="diseaseCode" title="Disease Code" />
        <ec:column property="monitorCode" title="Monitor Code" />
        <ec:column property="primarySponsorCode" title="Sponsor Code" />
        <%--
        <ec:column property="shortTitle" width="2" sortable="false" filterable="false" title="cpodfgdf">
        	<a href="newParticipant?studySiteId=${study.studySites[0].id}">cp</a>
        </ec:column>--%>
    </ec:row>
</ec:table>
</div>
</chrome:body>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>