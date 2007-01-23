<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="tabNumber"%>
<%@attribute name="isLast"%>
<div class="tabcontrols autoclear">
    <c:if test="${tab.number > 0}"><a id="flow-prev" class="tab${tab.number - 1}">&laquo; Previous</a></c:if>
    <a id="flow-next">Save<c:if test="${isLast}"> &amp; Continue &raquo;</c:if></a>
</div>
