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
<title>choose a Study</title>
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

<chrome:box autopad="true">
  	<p class="instructions">
  	  Choose the item, then type in a minimum of two characters then click <b>Go</b> for search
  	</p>
    <form:form id="searchForm" method="post">
    	<div>
    		<input type="hidden" name="_action" value="go">
		</div>
        <table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
                <td>
                	<tags:requiredIndicator />&nbsp;
                		<form:select path="searchType">
							<form:options items="${searchType}" itemLabel="desc"itemValue="code" />
					</form:select>
				</td>
                <td><form:input path="searchText" size="25" /></td>
                <td><input type="submit" value="Search" name="_target1" alt="SEARCH"></td>
            </tr>
        </table>
    </form:form>
</chrome:box>

<tags:tabForm tab="${tab}" flow="${flow}" willSave="false">
	<jsp:attribute name="instructions">
	 <c:if test="${fn:length(command.studies) gt 0}">
	 	Please choose one or more study from the below listing.
	 </c:if>
	</jsp:attribute>
    <jsp:attribute name="singleFields">
        <ec:table items="command.studies" var="study"
            action="${pageContext.request.contextPath}/pages/newParticipant"
            imagePath="${pageContext.request.contextPath}/images/table/*.gif"
            filterable="false"
            showPagination="false" form="command"
            cellspacing="0" cellpadding="0" border="0" width="80%" style=""
            styleClass=""
            autoIncludeParameters="false">
            <ec:row highlightRow="true">
                <ec:column property="transient0" style="width:10px" filterable="false"
                    sortable="false" title=" ">
                    <form:checkbox path="studySiteArray" value="${study.studySites[0].id}" />
                </ec:column>
               	 <ec:column property="primaryIdentifier" title="Primary ID" />
				<ec:column property="shortTitle" title="Short Title" />
				<ec:column property="primarySponsorCode" title="Funding Sponsor" />
				<ec:column property="phaseCode" title="Phase" />
				<ec:column property="status" title="Status" />
            </ec:row>
        </ec:table>
        <input type="hidden" name="_action" value="" />
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
