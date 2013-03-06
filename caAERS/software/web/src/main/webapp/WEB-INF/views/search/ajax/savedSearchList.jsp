<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>

<c:forEach items="${shortSearchList }" var="search" varStatus="searchStatus">
	<search:oneSavedSearch search="${search}"></search:oneSavedSearch>
</c:forEach>
<c:if test="${fn:length(savedSearchList) > 5}">
	<tr>
		<td><a onclick="javascript:renderFullSearchList();" href="#">more</a></td><td/>
	</tr>
</c:if>
