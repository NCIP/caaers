<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="index" required="true" type="java.lang.Integer"%>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true"%>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<tr id="${sectionClass}-${listEditorIndex}" class="${sectionClass}" style="${not empty style? style : ''}" >
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
	  <td><tags:renderInputs field="${field}" /></td>
	</c:forEach>
            <td align="right">
            <c:if test="${enableDelete}">
        	<a href="javascript:fireAction(${deleteParams},'${sectionClass}-${index}','${cssClass}');"><img 
	   			src="/caaers/images/checkno.gif" border="0" alt="delete"></a>
	   		</c:if></td>
            </tr>
