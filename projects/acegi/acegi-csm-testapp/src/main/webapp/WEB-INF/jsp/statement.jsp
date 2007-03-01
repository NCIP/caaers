<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<P>
<H2><c:if test="${statement.id == null}">New </c:if>Statement:</H2>
<spring:bind path="statement">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
	</FONT>
</spring:bind>
<P>
<FORM method="POST" action="<c:url value="/statement.htm"/>">

<spring:bind
	path="statement.value">
	
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B>
	</FONT>
	<BR>
	<INPUT type="text" maxlength="30" size="30" name="value"
		value="<c:out value="${status.value}"/>">
</spring:bind> 

<c:if test="${statement.id == null}">
	<INPUT type="submit" value="Add Statement" />
</c:if> 

<c:if test="${statement.id != null}">
	<INPUT type="submit" value="Update Statement" />
</c:if>
</FORM>
<P><BR>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>