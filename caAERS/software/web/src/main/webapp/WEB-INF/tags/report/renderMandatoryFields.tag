<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="key" required="true" %>
<%@attribute name="tabular" type="java.lang.Boolean" %>
<%@attribute name="singleRow" type="java.lang.Boolean" %>
<%@attribute name="startIndex" type="java.lang.Integer"%>
<%@attribute name="endIndex" type="java.lang.Integer"%>
<c:set var="len">${fn:length(fieldGroups[key].fields)}</c:set>
<c:set var="st">${empty startIndex ? 0 : startIndex }</c:set>
<c:set var="en">${empty endIndex ? (len-1) : endIndex}</c:set>
<%@attribute name="heading" %>
<c:if test="${not tabular}">
	<c:forEach var="field" begin="${st}" end="${en}" items="${fieldGroups[key].fields}">
	<tags:renderRow field="${field}" />
	</c:forEach>
</c:if>
<c:if test="${tabular}">
<c:if test="${singleRow}">
 <table>
	<tr>
	<td><div class="row"><div class="label">${heading}</div></div></td>
	<c:forEach var="field" items="${fieldGroups[key].fields}">
	<td><tags:renderLabel field="${field}" /></td>
	<td><tags:renderInputs field="${field}" /></td>
	</c:forEach>
	</tr>	
 </table>
</c:if>
<c:if test="${not singleRow}">
<ui:row path="${fieldGroups[key].fields[0].propertyName}">
	<jsp:attribute name="label">${heading}</jsp:attribute>
	<jsp:attribute name="value">
		<table>
	  	<c:forEach var="field" items="${fieldGroups[key].fields}">
		<tr>
		  <td><tags:renderLabel field="${field}" /></td>
		  <td><tags:renderInputs field="${field}" /></td>
		</tr>
	  </c:forEach>
 	</table>
	</jsp:attribute>
</ui:row>

</c:if>
</c:if>