<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae" required="true" type="gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent" %>
<%@attribute name="displayError" type="java.lang.Boolean" %>
<%@attribute name="showExternalID" type="java.lang.Boolean" %>
<%@attribute name="cssClass" %>

  <tr class="${cssClass}">
    <c:if test="${showExternalID}"><td  class="${cssClass}"> ${empty caaers:escapeJS(ae.externalId) ? requestScope.dash : caaers:escapeJS(ae.externalId)}</td></c:if>
    <td  class="${cssClass}"> ${caaers:escapeJS(ae.termName)}</td>
    <td  class="${cssClass}"> ${empty caaers:escapeJS(ae.grade) ? '' : caaers:escapeJS(ae.grade.shortName)}</td>
    <td  class="${cssClass}">
        <c:if test="${not empty ae.startDate}">
            <tags:formatDate value="${ae.startDate}" />
        </c:if>
    </td>
    <td>
        <c:if test="${not empty ae.startDate}">
            <tags:formatDate value="${ae.endDate}" />
        </c:if>
    </td>
    <td  class="${cssClass}"> ${empty caaers:escapeJS(ae.verbatim) ? requestScope.dash  : caaers:escapeJS(ae.verbatim)}</td>
    <td  class="${cssClass}"> ${empty caaers:escapeJS(ae.whySerious) ? requestScope.dash  : caaers:escapeJS(ae.whySerious)}</td>
    <td  class="${cssClass}"> ${empty caaers:escapeJS(ae.attribution) ? requestScope.dash : caaers:escapeJS(ae.attribution.displayName)}</td>
</tr>
<c:if test="${not empty displayError && displayError eq 'true'}">
    <tr class="error">
        <td colspan="${showExternalID ? '8' : '7'}" class="error" >${ae.errorMessage}</td>
    </tr>
</c:if>