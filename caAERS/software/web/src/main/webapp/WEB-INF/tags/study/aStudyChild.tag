<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true" %>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean" %>
<%@attribute name="localButtons" fragment="true" %>
<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<chrome:division title="${title}" id="${sectionClass}-${index}" 
cssClass="${sectionClass}" style="${style}" enableDelete="${enableDelete}" deleteParams="${deleteParams}">
 <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="status">
    <tags:renderRow field="${field}" deleteParams="${index},${status.index-1}" cssClass="ind${index}"/>
 </c:forEach>
 <div id="local-buttons-${index}" class="local-buttons">
    <jsp:invoke fragment="localButtons"/>
 </div>
 <br />
</chrome:division>