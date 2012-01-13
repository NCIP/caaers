<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="ta" required="true" type="gov.nih.nci.cabig.caaers.domain.TreatmentAssignment"%>
<%@attribute name="studyInterventionsHelper" required="true" type="java.util.Collection"%>
<%@attribute name="sectionTitle" required="true" type="java.lang.String"%>
<%@attribute name="property" required="true" type="java.lang.String"%>

<c:if test="${fn:length(studyInterventionsHelper) > 0}">
    <h4>${sectionTitle}</h4>

    <table width="100%" border="0" cellspacing="1" cellpadding="4" class="tablecontent">
    <tr>
        <th align="LEFT" width="70%">Name</th>
        <th align="LEFT" width="20%">Type</th>
        <th align="RIGHT" width="10%">Include</th>
    </tr>

    <c:forEach items="${studyInterventionsHelper}" var="siHelper" varStatus="i">
    <c:if test="${siHelper.treatmentAssignment == ta}">
        <tr>
            <td align="LEFT">${siHelper.studyIntervention.interventionName}</td>
            <td align="LEFT">${siHelper.studyIntervention.studyTherapyType.displayName}</td>
            <td align="RIGHT"><ui:checkbox path="${property}[${i.index}].selected" /></td>
        </tr>
    </c:if>
    </c:forEach>

    </table>

    <br>
</c:if>

