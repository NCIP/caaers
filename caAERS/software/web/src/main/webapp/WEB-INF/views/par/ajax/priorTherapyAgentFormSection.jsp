<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:noform>
<c:forEach items="${indexes}" var="index">
	<par:onePriorTherapyAgent index="${index}" parentIndex="${parentIndex}" agent="${priorTherapyAgents[index] }"/>
</c:forEach>
</tags:noform>

