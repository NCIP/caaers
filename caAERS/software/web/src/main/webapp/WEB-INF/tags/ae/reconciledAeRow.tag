<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae" required="true" type="gov.nih.nci.cabig.caaers.domain.ReconciledAdverseEvent" %>
<%@attribute name="displayError" type="java.lang.Boolean" %>

  <tr>
  	<td class="fillerRow"> ${ae.itemId}</td>
  	<td class="fillerRow"> ${ae.termName}</td>
  	<td class="fillerRow"> ${ae.grade.shortName}</td>
  	<td class="fillerRow"> ${ae.startDate}</td>
  	<td class="fillerRow"> ${ae.endDate}</td>
  	<td class="fillerRow"> ${ae.verbatim}</td>
  	<td class="fillerRow"> ${ae.whySerious}</td>
  	<td class="fillerRow"> ${ae.attribution}</td>
  </tr>
  <c:if test="${not empty displayError && displayError eq 'true'}">
	  <tr>
	  	<td colspan="8" class="errorMessage" >${ae.errorMessage}</td>
	  </tr>
  </c:if>
