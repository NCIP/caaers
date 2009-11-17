<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title><caaers:message code="investigator.review.pageTitle"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
        div.content {
            padding: 5px 15px;
        }
</style>

<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("command").submit();
}

</script>
</head>
<body>

<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <li id="thirdlevelnav" class="tab0 selected">
                <div><a href="createInvestigator"><caaers:message code="investigator.menu.createEditInvestigator"/></a></div>
            </li>
            <li id="thirdlevelnav" class="tab1">
                <div>
                    <a href="searchInvestigator"><caaers:message code="investigator.menu.searchInvestigator"/></a>
                </div>
            </li>
        </ul>
    </div>
    
</div>
<chrome:flashMessage/>
<chrome:box title="${investigator.lastName}, ${investigator.firstName}" >
<form:form>
<caaers:message code="investigator.review.detailsSection" var="detailsSectionTitle"/>
<chrome:division title="${detailsSectionTitle}">
 <div class="leftpanel">
	       <ui:row path="firstName">
    			<jsp:attribute name="value">
    				${investigator.firstName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="firstName" text="First name"></ui:label>
    			</jsp:attribute>
    		</ui:row>
    		
	       <ui:row path="middleName">
    			<jsp:attribute name="value">
    				${investigator.middleName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="middleName" text="Middle name"></ui:label>
    			</jsp:attribute>
			</ui:row>

	       <ui:row path="lastName">
    			<jsp:attribute name="value">
    				${investigator.lastName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="lastName" text="Last name"></ui:label>
    			</jsp:attribute>
			</ui:row>

	       <ui:row path="nciIdentifier">
    			<jsp:attribute name="value">
    				${investigator.nciIdentifier}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="nciIdentifier" labelProperty="investigator.nciIdentifier" text="Investigator number"></ui:label>
    			</jsp:attribute>
			</ui:row>
 </div>

 <div class="rightpanel">
   
	        <ui:row path="emailAddress">
    			<jsp:attribute name="value">
    				${investigator.emailAddress}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="emailAddress" text="Email address"></ui:label>
    			</jsp:attribute>
			</ui:row>
			
	        <ui:row path="phoneNumber">
    			<jsp:attribute name="value">
    				${investigator.phoneNumber}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="phoneNumber" text="Phone"></ui:label>
    			</jsp:attribute>
			</ui:row>
			
	        <ui:row path="faxNumber">
    			<jsp:attribute name="value">
    				${investigator.faxNumber}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="faxNumber" text="Fax"></ui:label>
    			</jsp:attribute>
			</ui:row>
			<c:if test="${command.allowedToLogin}">
	        <ui:row path="loginId">
    			<jsp:attribute name="value">
    				${investigator.loginId}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="loginId" text="Username"></ui:label>
    			</jsp:attribute>
			</ui:row>
			</c:if>
 </div>
</chrome:division>

 <c:if test="${not empty investigator.siteInvestigators}">
   <caaers:message code="investigator.review.associateSitesSection" var="associateSitesSectionTitle"/>
   <chrome:division title="${associateSitesSectionTitle}">
   <br>
	 <table class="tablecontent" width="70%">
	  <tr>
		 <th scope="col">Site</th>
		 <th scope="col">Start date</th>
		 <th scope="col">End date</th>
		 <th scope="col">Status</th>
	  </tr>
	  <c:forEach items="${investigator.siteInvestigators}" var="siteInvestigator">
		<tr class="results">
			<td>${siteInvestigator.organization.fullName}</td>
		    <td><tags:formatDate value="${siteInvestigator.startDate}"/></td>
		    <td><tags:formatDate value="${siteInvestigator.endDate}"/></td>
		    <td>${siteInvestigator.status}</td>
		</tr>
	  </c:forEach>
	  </table>
	</chrome:division>
 </c:if>
 </form:form>
 <br style="clear:both;"/>
</chrome:box>

</body>
</html>