<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="adverseEvents" required="true" type="java.util.List"%>
<%@attribute name="blocks" required="true" type="java.util.List"%>
<%@attribute name="maxAEs" required="true" type="java.lang.Integer"%>
<%@attribute name="offset" required="true" type="java.lang.Integer"%>

<c:set var="remainingAEs" value="${fn:length(adverseEvents) - offset}"/>
<c:set var="aeCols" value="${remainingAEs > maxAEs ? maxAEs : remainingAEs}"/>
<c:set var="cols" value="${aeCols + 1}"/>

<c:if test="${offset > 0}"><hr class="attrib-divider"/></c:if>

<table class="attribution" id="attribution-${offset}">
    <col class="cause"/>
    <colgroup>
        <c:forEach begin="1" end="${aeCols}">
        <col class="ae-attrib"/>
        </c:forEach>
    </colgroup>
    <tr class="top">
        <th class="cause">Possible cause</th>
        <c:forEach begin="${offset}" end="${offset + aeCols - 1}" var="i">
            <c:set var="ae" value="${adverseEvents[i]}"/>
            <th class="ae">
                <div class="index">
                    <c:choose>
                        <c:when test="${i == 0}">Primary AE</c:when>
                        <c:otherwise>AE ${i + 1}</c:otherwise>
                    </c:choose>
                </div>
                <div class="grade">${ae.grade}</div>
                <div class="term">${ae.adverseEventTerm.universalTerm}</div>
            </th>
        </c:forEach>
    </tr>
    <c:forEach items="${blocks}" var="block">
        <tr class="subhead">
            <th colspan="${cols}">${block.displayName}</th>
        </tr>
        <c:if test="${empty block.rows}">
        <td colspan="${cols}">No ${fn:toLowerCase(block.displayName)} for this report.</td>
        </c:if>
        <c:forEach items="${block.rows}" var="row">
        <tr class="fields">
            <th>${row.displayName}</th>
            <c:forEach begin="${offset}" end="${offset + aeCols - 1}" var="i">
                <td><tags:renderInputs field="${row.fields[i]}"/></td>
            </c:forEach>
        </tr>
        </c:forEach>
    </c:forEach>
</table>
