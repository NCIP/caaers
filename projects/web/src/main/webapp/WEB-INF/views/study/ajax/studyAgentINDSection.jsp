<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:noform>
<c:set var="mainGroup">ind${index}</c:set>
<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="status">
    <tags:renderRow field="${field}" deleteParams="${index},${status.index-1}" cssClass="ind${index}" style="display:none"/>
 </c:forEach>
</tags:noform>