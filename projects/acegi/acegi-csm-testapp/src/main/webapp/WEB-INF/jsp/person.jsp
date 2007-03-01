<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<P>
<H2><c:if test="${person.id == null}">New </c:if>Person:</H2>
<spring:bind path="person">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
</spring:bind>
<P>
<FORM method="POST" action="<c:url value="/person.htm"/>">
  <spring:nestedPath path="person">
    <jsp:include page="/WEB-INF/jsp/fields/name.jsp"/>
  </spring:nestedPath>
  <c:if test="${person.id == null}">
    <INPUT type = "submit" value="Add Person"  />
  </c:if>
  <c:if test="${person.id != null}">
    <INPUT type = "submit" value="Update Person"  />
  </c:if>
</FORM>
<P>
<c:if test="${ !empty person.statements }">
<table border="true">
<th>Date</th><th>Statement</th>
<c:forEach var="statement" items="${person.statements}">
<tr>
 <td><fmt:formatDate value="${statement.date}" pattern="yyyy-MM-dd"/></td>
 <td><c:out value="${statement.value}"/><br/>
 <form method="GET" action="<c:url value="/statement.htm"/>">
		<input type="hidden" name="statementId" value="${statement.id}"/>
		<input type="submit" value="Edit"/>
		
	</form>
 </td>
</tr>
</c:forEach>
</table>
</c:if>


<c:if test="${person.id != null}">
<FORM method="GET" action="<c:url value="/statement.htm"/>">
	<input type="hidden" name="personId" value="${person.id}"/>
	<INPUT type="submit" value="New Statement"/>
</FORM>
</c:if>
<BR>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>