<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="id"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<%@attribute name="deleteParams" %>
<%@attribute name="collapsable" required="false" %>
<%@attribute name="header" fragment="true" required="true" description="The header row, which will always be displayed"%>
<%@attribute name="content" fragment="true" description="The body content, that will be minimized" %>
<table border="1" width="100%" id="${id}" >
	<tr id="${id}-header">
		<td width="1%">V</td>
		<td width="98%"><jsp:invoke fragment="header" /></td>
		<td width="1%">X</td>
	</tr>
	<tr id="${id}-content">
		<td></td>
		<td colspan="2"><jsp:invoke fragment="content" /></td>
	</tr>
</table>