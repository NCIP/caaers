<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" %>
<%@attribute name="style"%>
<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<c:set var="title"><c:choose>
    <c:when test="${index == 0}">${command.aeReport.adverseEvents[index].adverseEventMeddraLowLevelTerm.fullName} (Primary)</c:when>
    <c:otherwise>${command.aeReport.adverseEvents[index].adverseEventMeddraLowLevelTerm.fullName}</c:otherwise>
</c:choose></c:set>

<c:set var="v" value="aeReport.adverseEvents[${index}]" />
<chrome:division title="${title}" id="ae-section-${index}" cssClass="ae-section" style="${style}" collapsable="true" collapsed="${!empties[v]}">

    <div id="main-fields-${index}" class="main-fields">
		<c:set var="len" value="${fn:length(fieldGroups[mainGroup].fields)}" />
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="0" end="${len - 2}">
            <tags:renderRow field="${field}"/>
        </c:forEach>
		<ae:oneOutcome index="${index}" />
		<tags:renderRow field="${fieldGroups[mainGroup].fields[len - 1]}"/>
    </div>
</chrome:division>
