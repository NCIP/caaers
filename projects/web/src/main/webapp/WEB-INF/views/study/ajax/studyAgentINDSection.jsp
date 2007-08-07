<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:noform>
<c:set var="mainGroup">main${index}</c:set>
<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="status">
	<c:if test="${status.index gt indIndex }">
    <tags:renderRow field="${field}" deleteParams="${index},${status.index-1}" cssClass="ind${index}" style="display:none"/>
    </c:if>
 </c:forEach>
</tags:noform>