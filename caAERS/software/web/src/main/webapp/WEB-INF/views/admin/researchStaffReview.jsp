<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title><caaers:message code="researchStaff.review.pageTitle"/></title>
</head>
<body>

<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <li id="thirdlevelnav" class="tab selected"><div><a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a></div></li>
            <li id="thirdlevelnav" class="tab"><div><a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a></div></li>
        </ul>
    </div>
</div>

<chrome:flashMessage/>

<chrome:box title="${command.researchStaff.lastName}, ${researchStaff.firstName}">
    <form:form>

        <caaers:message code="researchstaff.details.detailsSection" var="detailsSectionTitle"/>
        <chrome:division title="${detailsSectionTitle}">
            <div class="leftpanel">
                <ui:row path="researchStaff.firstName">
                    <jsp:attribute name="label"><ui:label path="researchStaff.firstName" text="First name"></ui:label></jsp:attribute>
                    <jsp:attribute name="value">${command.researchStaff.firstName}</jsp:attribute>
                 </ui:row>
                <ui:row path="researchStaff.middleName">
                    <jsp:attribute name="label"><ui:label path="researchStaff.middleName" text="Middle name"></ui:label></jsp:attribute>
                    <jsp:attribute name="value">${command.researchStaff.middleName}</jsp:attribute>
                 </ui:row>
                <ui:row path="researchStaff.lastName">
                    <jsp:attribute name="label"><ui:label path="researchStaff.lastName" text="Last name"></ui:label></jsp:attribute>
                    <jsp:attribute name="value">${command.researchStaff.lastName}</jsp:attribute>
                 </ui:row>
                <ui:row path="researchStaff.emailAddress">
                    <jsp:attribute name="label"><ui:label path="researchStaff.emailAddress" text="Primary email address"></ui:label></jsp:attribute>
                    <jsp:attribute name="value">${command.researchStaff.emailAddress}</jsp:attribute>
                 </ui:row>
                <ui:row path="researchStaff.loginId">
                    <jsp:attribute name="label"><ui:label path="researchStaff.loginId" text="Login ID"></ui:label></jsp:attribute>
                    <jsp:attribute name="value">${command.researchStaff.loginId}</jsp:attribute>
                 </ui:row>
            </div>
        </chrome:division>

        <caaers:message code="researchstaff.details.organizations" var="rsOrganizations"/>
        <chrome:division title="${rsOrganizations}">
            <c:set var="size" value="${fn:length(command.researchStaff.siteResearchStaffs)}" />
            <c:forEach items="${command.researchStaff.siteResearchStaffs}" varStatus="status" var="rss">
                <c:set var="newIndex" value="${size - (status.index + 1)}" />
                <researchStaff:oneSiteResearchStaff index="${newIndex}" collapsed="false" readOnly="true" siteResearchStaff="${rss}"/>
            </c:forEach>
        </chrome:division>
        
    </form:form>
</chrome:box>

</body>
</html>
