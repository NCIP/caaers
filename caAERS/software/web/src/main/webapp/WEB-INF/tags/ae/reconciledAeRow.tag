<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae" required="true" type="gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent" %>
<%@attribute name="displayError" type="java.lang.Boolean" %>

  <tr>
  	<td > ${empty ae.externalId ? requestScope.dash : ae.externalId}</td>
  	<td > ${ae.termName}</td>
  	<td> ${empty ae.grade ? '' : ae.grade.shortName}</td>
  	<td >
      <c:if test="${not empty ae.startDate}">
          <tags:formatDate value="${ae.startDate}" />
      </c:if>
  	</td>
  	<td>
      <c:if test="${not empty ae.startDate}">
          <tags:formatDate value="${ae.endDate}" />
      </c:if>
      </td>
  	<td> ${empty ae.verbatim ? requestScope.dash  : ae.verbatim}</td>
  	<td> ${empty ae.whySerious ? requestScope.dash  : ae.whySerious}</td>
  	<td> ${empty ae.attribution ? requestScope.dash : ae.attribution.displayName}</td>
  </tr>
  <c:if test="${not empty displayError && displayError eq 'true'}">
	  <tr class="error">
	  	<td colspan="8" class="error" >${ae.errorMessage}</td>
	  </tr>
  </c:if>
