<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="tab" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" required="true" %>
<%@attribute name="flow" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" required="true" %>
<div class="workflow-tabs">
<ul id="" class="tabs autoclear">
<c:set var="unfilledTabs">${UNFILLED_TABS}</c:set>
<c:set var="mandatoryTabs">${MANDATORY_TABS}</c:set>

<c:forEach items="${flow.tabs}" var="atab" varStatus="status">
    <c:set var="selected" value="${atab.number == tab.number}"/>
    <li class="tab ${selected ? 'selected' : ''} ${status.last ? 'last' : ''}" id="thirdlevelnav"><div>
     <c:if test="${fn:contains(mandatoryTabs,atab.shortTitle)}"><span class="required-indicator">*</span></c:if><c:if test="${fn:contains(unfilledTabs,atab.shortTitle)}"><span class="required-for-submit">&#167;</span></c:if>
        <a href="#" class="tab${atab.number}">${atab.number + 1}. ${atab.shortTitle}</a>
    </div></li>
</c:forEach>
</ul>
</div>
