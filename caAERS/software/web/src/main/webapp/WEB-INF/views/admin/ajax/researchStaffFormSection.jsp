<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="rs" tagdir="/WEB-INF/tags/researchStaff" %>
<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="cmIdxSt">
	<c:set var="siteRS" value="0" />
	<rs:oneSiteResearchStaff collapsed="false" index="${index}" />
</c:forEach>
</tags:noform>
