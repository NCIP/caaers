

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="ae" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>
<%@attribute name="width" required="true" type="java.lang.String" %>

<% String currClass=index%2==0? "odd":"even"; %>
<tr align="center" id="${index}" class="<%= currClass %>" onMouseOver="this.className='highlight'"
			onMouseOut="this.className='<%= currClass %>'">
	<td width="${width}">${ae.adverseEventTerm.universalTerm}</td>
	<td width="${width}">${ae.grade.code}</td>
	<td width="${width}">${ae.startDate}</td>
	<c:if test="${ae.requiresReporting == true}">
		<td width="${width}"><img src="/caaers/images/redexclamation.gif" id="check-image"/></td>
	</c:if>
	<c:if test="${ae.requiresReporting == false}">
		<td width="${width}"></td>
	</c:if>	
</tr>