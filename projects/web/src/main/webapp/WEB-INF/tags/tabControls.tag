<!-- BEGIN tags\tabControls.tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%-- must specify either tab & flow or tabNumber and isLast --%>
<%@attribute name="tab" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" %>
<%@attribute name="flow" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" %>
<%@attribute name="tabNumber"%>
<%@attribute name="isLast"%>
<%@attribute name="willSave"%>
<%@attribute name="saveButtonLabel" required="false"%>
<%@attribute name="localButtons" fragment="true" %>
<c:set var="tabNumber" value="${empty tabNumber ? tab.number : tabNumber}"/>
<c:set var="isLast" value="${empty isLast ? not (tab.number < flow.tabCount - 1) : isLast}"/>
<div class="content buttons autoclear">
    <div class="local-buttons">
        <jsp:invoke fragment="localButtons"/>
    </div>
    <div class="flow-buttons">
        <span class="prev">
            <c:if test="${tabNumber > 0}">
                <tags:button color="blue" icon="${willSave ? 'Save &amp; ' : ''}Back" id="flow-prev" cssClass="tab${tabNumber - 1}" value="${willSave ? 'Save &amp; ' : ''}Back"/>
            </c:if>
        </span>
        <span class="next">

            <c:if test="${not isLast  and willSave}">
                <tags:button color="blue" icon="save" id="flow-update" cssClass="tab${tabNumber}" value="Save"/>
            </c:if>
			<c:set var="saveText" value="${not empty saveButtonLabel ? saveButtonLabel : 'Save'}" />
            <c:set var="continueLabel" value="${isLast || willSave ? saveText : ''}"/>
            <c:if test="${not empty continueLabel && not isLast}">
                <c:set var="continueLabel" value="${continueLabel} &amp; "/>
            </c:if>
            <c:if test="${not isLast}">
                <c:set var="continueLabel" value="${continueLabel}Continue"/>
            </c:if>
            <tags:button type="submit" icon="${continueLabel}" color="green" id="flow-next" value="${continueLabel}"/>
        </span>
    </div>
</div>
<!-- END tags\tabControls.tag -->