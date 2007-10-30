<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Search for a Participant</title>
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
<chrome:box autopad="true">
    <form:form id="searchForm" method="post" cssClass="standard">
        <table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
            </tr>
            <tr>
                <td class="searchType">
                    Search for a Particpant&nbsp;&nbsp;
                </td>
                <td><form:select path="participantType">
						<form:options items="${participantSearchType}" itemLabel="desc"itemValue="code" />
					</form:select></td>
                <td><form:input path="participantText" size="30" /></td>
                <c:set var="targetPage" value="${assignType == 'study' ? '_target1' : '_target0'}"/>
                <td><input type="submit" alignment="center" value="go" name="${targetPage}" alt="GO" align="middle" width="22"
						height="10" border="0">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4" class="notation">
                    <span class="labels">(<span class="red">*</span><em>Required Information</em>)</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ^ Minimum two characters for search.
                </td>
            </tr>
        </table>
    </form:form>
</chrome:box>


<p id="instructions">
Please use the form above to search for a Participant and assign it to <b>${command.studySites[0].study.shortTitle}</b> and then press Save & Continue to proceed 
</p>
<tags:tabForm tab="${tab}" flow="${flow}" title="Participant search results">
    <jsp:attribute name="singleFields">
        <ec:table autoIncludeParameters="false"
        items="command.participantSearchResults"
        var="participant"
        action="${pageContext.request.contextPath}/pages/home"
        imagePath="${pageContext.request.contextPath}/images/table/*.gif"
        filterable="false"
        showPagination="false"
        cellspacing="0" cellpadding="0" border="0" width="80%" style="" styleClass="">
        <ec:row highlightRow="true">
        <ec:column property="kk" style="width:10px" filterable="false" sortable="false" title=" ">
            <form:radiobutton path="participantId" value="${participant.id}" />
        </ec:column>
        <ec:column property="primaryIdentifier" title="Primary ID"/>
        <ec:column property="firstName" title="First Name"/>
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
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
