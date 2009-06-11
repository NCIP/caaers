<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title><caaers:message code="researchStaff.review.pageTitle"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
<style type="text/css">
  div.content {
   height:160px;
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
	<li id="thirdlevelnav" class="tab selected">
	<div><a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a></div>
	</li>
	<li id="thirdlevelnav" class="tab">
	<div><a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a></div>
	</li>
 </ul>
        </div>
</div>
<chrome:flashMessage/>
<chrome:box title="${researchStaff.lastName}, ${researchStaff.firstName}" >
<form:form>
	<caaers:message code="researchStaff.review.detailsSection" var="detailsSectionTitle"/>	
     <chrome:division title="${detailsSectionTitle}">

    		<ui:row path="organization">
    			<jsp:attribute name="value">
    				${researchStaff.organization.fullName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="organization" text="Organization"></ui:label>
    			</jsp:attribute>
    		</ui:row>     
     
    	<div class="leftpanel">
	       <ui:row path="firstName">
    			<jsp:attribute name="value">
    				${researchStaff.firstName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="firstName" text="First name"></ui:label>
    			</jsp:attribute>
    		</ui:row>
    		
	       <ui:row path="middleName">
    			<jsp:attribute name="value">
    				${researchStaff.middleName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="middleName" text="Middle name"></ui:label>
    			</jsp:attribute>
			</ui:row>

	       <ui:row path="lastName">
    			<jsp:attribute name="value">
    				${researchStaff.lastName}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="lastName" text="Last name"></ui:label>
    			</jsp:attribute>
			</ui:row>

	       <ui:row path="nciIdentifier">
    			<jsp:attribute name="value">
    				${researchStaff.nciIdentifier}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="nciIdentifier" labelProperty="researchStaff.nciIdentifier" text="Researcher ID"></ui:label>
    			</jsp:attribute>
			</ui:row>
    	</div>

    	<div class="rightpanel">
	        <ui:row path="emailAddress">
    			<jsp:attribute name="value">
    				${researchStaff.emailAddress}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="emailAddress" text="Email address"></ui:label>
    			</jsp:attribute>
			</ui:row>
			
	        <ui:row path="phoneNumber">
    			<jsp:attribute name="value">
    				${researchStaff.phoneNumber}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="phoneNumber" text="Phone"></ui:label>
    			</jsp:attribute>
			</ui:row>
			
	        <ui:row path="faxNumber">
    			<jsp:attribute name="value">
    				${researchStaff.faxNumber}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="faxNumber" text="Fax"></ui:label>
    			</jsp:attribute>
			</ui:row>

	        <ui:row path="loginId">
    			<jsp:attribute name="value">
    				${researchStaff.loginId}
    			</jsp:attribute>
    			<jsp:attribute name="label">
    				<ui:label path="loginId" text="Username"></ui:label>
    			</jsp:attribute>
			</ui:row>
			
    	</div>
      
	</chrome:division>
	
	<caaers:message code="researchStaff.review.rolesSection" var="rolesSectionTitle"/>
	<chrome:division title="${rolesSectionTitle}">
        <div style="padding-left:50px;">
        <c:forEach var="role" items="${researchStaff.userGroupTypes}">
			<li>
				<c:if test="${role.csmName eq 'caaers_participant_cd'}"><caaers:message code="role.subjectCoordinator"/></c:if>
				<c:if test="${role.csmName eq 'caaers_study_cd'}"><caaers:message code="role.studyCoordinator"/></c:if>
				<c:if test="${role.csmName eq 'caaers_ae_cd'}"><caaers:message code="role.adverseEventCoordinator"/></c:if>
				<c:if test="${role.csmName eq 'caaers_site_cd'}"><caaers:message code="role.siteCoordinator"/></c:if>
				<c:if test="${role.csmName eq 'caaers_central_office_sae_cd'}"><caaers:message code="role.centralOfficeReportReviewer"/></c:if>
				<c:if test="${role.csmName eq 'caaers_data_cd'}"><caaers:message code="role.dataCoordinator"/></c:if>
			</li>
		</c:forEach>
        </div>
    </chrome:division>
</form:form>	
</chrome:box>

</body>
