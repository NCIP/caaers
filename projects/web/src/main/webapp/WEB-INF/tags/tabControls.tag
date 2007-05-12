<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- must specify either tab & flow or tabNumber and isLast --%>
<%@attribute name="tab" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" %>
<%@attribute name="flow" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" %>
<%@attribute name="tabNumber"%>
<%@attribute name="isLast"%>
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
                <input type="submit" id="flow-prev" class="tab${tabNumber - 1}" value="&laquo; Previous"/>
            </c:if>
        </span>
        <span class="next">
            <input type="reset" value="Reset tab"/>
            <!-- TODO: different display for edit vs. create modes -->
            <input type="submit" id="flow-update" class="tab${tabNumber}" value="Save"/>
            <c:if test="${not isLast}">
                <input type="submit" id="flow-next" value="Save &amp; Continue &raquo;"/>
            </c:if>
        </span>
    </div>
</div>
