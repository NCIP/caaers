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

<c:forEach items="${flow.tabs}" var="atab" varStatus="status">
 <csmauthz:accesscontrol domainObject="${atab}" authorizationCheckName="tabAuthorizationCheck">
    <c:set var="selected" value="${atab.number == tab.number}"/>
    <li class="tab ${selected ? 'selected' : ''} ${status.last ? 'last' : ''}" id="thirdlevelnav"><div>
     <c:if test="${fn:contains(mandatoryTabs, atab.shortTitle)}"><span class="required-indicator">*</span></c:if><c:if test="${fn:contains(unfilledTabs, atab.shortTitle)}"><span class="required-for-submit">$</span></c:if>
        <a href="#" class="tab${atab.number}">${atab.number + 1}. ${atab.shortTitle}</a>
    </div></li>
	<c:if test="${selected}">
		<tags:pageHelp propertyKey="${tab.class.name}" />
	</c:if>
 </csmauthz:accesscontrol>	
</c:forEach>
</ul>
</div>
<a name="skipnav"></a>