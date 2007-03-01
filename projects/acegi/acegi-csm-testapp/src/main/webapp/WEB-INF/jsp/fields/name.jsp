<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<B>Name:</B>
<spring:bind path="name">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
  <BR><INPUT type="text" maxlength="30" size="30" name="name" value="<c:out value="${status.value}"/>" >
</spring:bind>
<P>