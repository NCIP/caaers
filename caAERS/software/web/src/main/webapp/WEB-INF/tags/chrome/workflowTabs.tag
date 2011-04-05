<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="tab" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" required="true" %>
<%@attribute name="flow" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" required="true" %>
<div class="workflow-tabs">
<ul id="" class="tabs autoclear">
<c:set var="unfilledTabs">${UNFILLED_TABS}</c:set>
<c:set var="mandatoryTabs">${MANDATORY_TABS}</c:set>
<c:set var="mandatoryFieldedTabs">${MANDATORY_FIELD_TABS}</c:set>

<c:forEach items="${flow.tabs}" var="atab" varStatus="status">
 <csmauthz:accesscontrol domainObject="${atab}" authorizationCheckName="tabAuthorizationCheck">
    <c:set var="selected" value="${atab.number == tab.number}"/>
    <c:set var="_isM" value="${fn:contains(mandatoryTabs, atab.shortTitle)}" />
    <c:set var="_isU" value="${fn:contains(unfilledTabs, atab.shortTitle)}" />
    <c:set var="_isF" value="${not _isU}" />

    <li class="tab ${selected ? 'selected' : ''} ${status.last ? 'last' : ''}" id="thirdlevelnav"><div>
     <c:if test="${_isM}"><font style="color: #f66; text-shadow:0 -1px black;">*</font></c:if>
        <a href="#" class="tab${atab.number} ${(flow.name eq 'Edit expedited report' or flow.name eq 'Create expedited report') ? (_isU ? 'incomplete' : 'complete'):''}">
        	<c:if test="${flow.name eq 'Edit expedited report' or flow.name eq 'Create expedited report'}">
				<c:if test="${!status.last}">
					<c:if test="${ _isF }">
		        		<img src="<chrome:imageUrl name="../buttons/button_icons/small/check_icon_small.png" />" alt="Complete" style="vertical-align:top;" />
		        	</c:if>
				</c:if>
			</c:if>
			${atab.number + 1}. ${atab.shortTitle}&nbsp;
			</a>
    </div></li>
	<c:if test="${selected}">
		<tags:pageHelp propertyKey="${tab.class.name}" />
	</c:if>
 </csmauthz:accesscontrol>
</c:forEach>
</ul>
</div>
<a name="skipnav"></a>