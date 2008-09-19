<!-- BEGIN tags\tabControls.tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <input type="image" src="/caaers/images/blue/${willSave ? 'save' : ''}back_btn.png" id="flow-prev" class="tab${tabNumber - 1}" value="&laquo; ${willSave ? 'save' : ''}back" alt="&laquo; ${willSave ? 'Save &amp; ' : ''}Back"/>
            </c:if>
        </span>
        <span class="next">

            <c:if test="${not isLast  and willSave}">
                <input type="image" src="/caaers/images/blue/save_btn.png" id="flow-update" class="tab${tabNumber}" value="Save" alt="Save"/>
            </c:if>
			<c:set var="saveText" value="save" />
            <c:set var="continueLabel" value="${isLast || willSave ? saveText : ''}"/>
            <c:if test="${not empty continueLabel && not isLast}">
                <c:set var="continueLabel" value="${continueLabel}"/>
            </c:if>
            <c:if test="${not isLast}">
                <c:set var="continueLabel" value="${continueLabel}continue"/>
            </c:if>
            <input type="image" src="/caaers/images/blue/${continueLabel}_btn.png" id="flow-next" value="${continueLabel} &raquo;" alt="${continueLabel} &raquo;"/>
        </span>
    </div>
</div>
<!-- END tags\tabControls.tag -->