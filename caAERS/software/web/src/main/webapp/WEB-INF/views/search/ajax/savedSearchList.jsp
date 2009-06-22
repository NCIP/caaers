<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>

<c:forEach items="${shortSearchList }" var="search" varStatus="searchStatus">
	<search:oneSavedSearch search="${search}"></search:oneSavedSearch>
</c:forEach>
<c:if test="${fn:length(savedSearchList) > 5}">
	<tr>
		<td><a onclick="javascript:advancedSearchHelper.renderFullSearchList();" href="#">more</a></td><td/>
	</tr>
</c:if>
