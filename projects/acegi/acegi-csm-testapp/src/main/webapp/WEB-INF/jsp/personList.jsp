<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<P>
<H2>Persons:</H2>

<TABLE border="1">
	<TH>Name</TH>
	<TH>ID</TH>
	<c:forEach var="person" items="${model.persons}">
		<csmauthz:accesscontrol domainObject="${person}" hasPrivileges="READ">
		<TR>
			<TD>
			<FORM method="GET" action="<c:url value="/person.htm"/>"><INPUT
				type="hidden" name="personId" value="<c:out value="${person.id}"/>" />
			<INPUT type="submit" value="<c:out value="${person.name}"/>" /></FORM>
			</TD>
			<TD><c:out value="${person.id}" /> <authz:authorize
				ifAllGranted="ROLE_admin_user">
				Delete Button
			</authz:authorize></TD>
		</TR>
		</csmauthz:accesscontrol>
	</c:forEach>
</TABLE>
<P><BR>
<FORM method="GET" action="<c:url value="/person.htm"/>"><INPUT
	type="submit" value="New Person" /></FORM>



<%@ include file="/WEB-INF/jsp/footer.jsp"%>
