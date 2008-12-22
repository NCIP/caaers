<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="ae" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>
<%@attribute name="isDCPStudy" type="java.lang.Boolean" required="true" description="True, if it is a DCP study" %>
<%@attribute name="width" required="true" type="java.lang.String" %>

<% String currClass=index%2==0? "odd":"even"; %>
<tr align="center"  class="<%= currClass %>">
	<td width="35%" align="left">${ae.adverseEventTerm.universalTerm}
		<div class="divNotes">
			<c:if test="${not empty ae.detailsForOther}">
			Verbatim : ${ae.detailsForOther} <br>
			</c:if>
			<c:if test="${not empty ae.lowLevelTerm}">
			Other(MedDRA) : ${ae.lowLevelTerm.meddraTerm}
			</c:if>
		</div>
	</td>
	<td width="10%">${ae.grade.code}</td>
	<td width="20%" >${ae.attributionSummary.displayName }</td>
	<c:if test="${isDCPStudy}"><td width="10%" >${ae.attributionSummary.displayName }</td></c:if>
	<td width="20%" >${ae.hospitalization.code eq 0 ? 'None' : ae.hospitalization.displayName}</td>
		
</tr>