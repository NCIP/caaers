<%@ attribute name="bgColor" required="true" %>
<%@ attribute name="contentID" required="true" %>

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<td bgcolor="${bgColor}" id="termsDiv" id="${contentID}">
     <jsp:doBody/>
</td>
</tr>
</table>