<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>caAERS</title>
<link href="resources/styles.css" rel="stylesheet" type="text/css">
<link href="resources/search.css" rel="stylesheet" type="text/css">
<script>
function submitPage(s){
	document.getElementById("searchCategory").value=s;
	document.getElementById("searchForm").submit();
}
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function doNothing(){
}
</script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="topNav">

	<tr valign="middle">
		
		<td class="right"><img src="images/topDivider.gif" width="2"
			height="20" align="absmiddle" class="divider"><a href="logOff">Log
		Off</a></td>
	</tr>
</table>
<!-- TOP NAVIGATION ENDS HERE -->
<!-- SUB NAV STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="subNav">
	<tr>
		<td width="99%" valign="middle" class="welcome">Welcome, User
		Name</td>
		<td valign="middle" class="right"><a href="help">Help</a></td>
	</tr>
</table>
<!-- SUB NAV ENDS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="display">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="tabs">
					<tr>
						<td width="100%" id="tabDisplay"><span class="current">
						<img src="images/tabWhiteL.gif" width="3" height="16"
							align="absmiddle"> 1. Select Participant <img
							src="images/tabWhiteR.gif" width="3" height="16"
							align="absmiddle"> </span><span class="tab"><img
							src="images/tabGrayL.gif" width="3" height="16" align="absmiddle">
						2. Select Study <img src="images/tabGrayR.gif" width="3"
							height="16" align="absmiddle"><img
							src="images/tabGrayL.gif" width="3" height="16" align="absmiddle">
						3. Review and Submit <img src="images/tabGrayR.gif" width="3"
							height="16" align="absmiddle"></span></td>
						<td><img src="images/spacer.gif" width="7" height="1"></td>
					</tr>
					<tr>
						<td colspan="2" class="tabBotL"><img src="images/spacer.gif"
							width="1" height="7"></td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- MAIN BODY STARTS HERE -->
			<tr>
				<td>
				<div class="workArea">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="titleArea">
					<form:form id="searchForm" name="searchForm" method="post">
						<form:hidden path="searchCategory" />
						<tr>
							<!-- TITLE STARTS HERE -->
							<td width="99%" height="43" valign="middle" id="title"><a
								href="createparticipant.do?url=SearchRegisterStudy.do"></td>
							<td valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								id="search">
								<tr>
									<td class="labels">&nbsp;</td>
								</tr>
								<tr>
									<td class="searchType">Search Participant by <form:select
										path="searchTypePart">
										<form:options items="${searchTypeParticipant}"
											itemLabel="desc" itemValue="code" />
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
									<td><form:input path="searchTypeTextPart" size="25" /></td>
									<td><input name="imageField" type="image" class="button"
										onClick="submitPage('participant');return false;"
										src="images/b-go.gif" alt="GO" align="middle" width="22"
										height="10" border="0"></td>
								</tr>
							</table>
							<span class="notation">^ Minimum two characters for
							search.</span></td>
						</tr>
					</form:form>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<!-- CURRENT DRIVER/UNIT TITLE STARTS HERE -->

					</tr>
					<tr>

						<td class="display"><!-- TABS LEFT START HERE -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>

								<!-- LEFT CONTENT STARTS HERE -->

								<td valign="top" class="additionals"><!-- LEFT FORM STARTS HERE -->


								
		<ec:table 
    	items="participants"
    	var="participant" 
    	action="${pageContext.request.contextPath}/pages/home" 
    	imagePath="${pageContext.request.contextPath}/images/*.gif"
    	title="Participant Search Results"
    	showPagination="false"
    	cellspacing="0" cellpadding="0" border="0" width="80%" style="" styleClass="">
    	<ec:row highlightRow="true">
        <ec:column property="firstName" title="First Name">
           <a href="searchRegisterStudy?participantId=${participant.id}">${participant.firstName}</a>
        </ec:column>
        <ec:column property="lastName" title="Last Name" />
        <ec:column property="dateOfBirth" title="Date of Birth" cell="date" parse="yyyy-MM-dd" format="MM/dd/yyyy" />
        <ec:column property="gender" title="Gender" />
        <ec:column property="race" title="Race" />
        <ec:column property="ethnicity" title="Ethnicity" />
        <%--
        <ec:column property="shortTitle" width="2" sortable="false" filterable="false" title="cpodfgdf">
        	<a href="newParticipant?studySiteId=${study.studySites[0].id}">cp</a>
        </ec:column>--%>
    </ec:row>
</ec:table>
								<br>
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
<div id="copyright">&copy; 2006 SemanticBits Company. All Rights
Reserved</div>
</div>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
