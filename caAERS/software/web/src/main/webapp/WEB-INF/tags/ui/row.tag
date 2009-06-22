<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="path" description="Path to property for data binding on the associated field" required="true"%>
<%@attribute name="cssClass" description="Equivalent to 'class' - HTML Optional Attribute"%>
<%@attribute name="style" description="Equivalent to 'style' html attribute"%>
<%@attribute name="label" fragment="true" description="The label to be printed for the row" %>
<%@attribute name="value" fragment="true" required="true" description="The label to be printed for the row" %>
<%@attribute name="extra" description="Extra text that needs to be included in the row, eg:- help text, other messages"  %>
<%@attribute name="embededJS" description="The attribute expects javascript code, which needs to be embeded inside the row (rarely used)" %>
<caaers:renderFilter elementID="${path}">
<div class="row ${cssClass}" id="${path}-row" <tags:attribute name="style" value="${style}" />>
	<c:if test="${not empty label}">
		<div class="label">
			<jsp:invoke fragment="label"/>
		</div>
	</c:if>
    <c:if test="${not empty value}">
    	<div class="value">
    		<jsp:invoke fragment="value" />
		</div>
    </c:if>
    <c:if test="${not empty extra}">
        <div class="extra">${extra}</div>
    </c:if>
<c:if test="${not empty embededJS}"><script>${embededJS}</script></c:if>
</div>
</caaers:renderFilter>
