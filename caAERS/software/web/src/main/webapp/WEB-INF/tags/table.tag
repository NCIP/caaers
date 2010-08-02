<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="bgColor" required="false" %>
<%@ attribute name="contentID" required="true" %>
<%@ attribute name="width" required="false" %>

<c:if test="${empty bgColor}"><c:set var="bgColor" value="#aaaaaa" /></c:if>
<c:if test="${empty width}"><c:set var="width" value="95%" /></c:if>

<table width="${width}" cellpadding="0" cellspacing="0">
<tr>
<td bgcolor="${bgColor}" id="${contentID}">
     <jsp:doBody/>
</td>
</tr>
</table>