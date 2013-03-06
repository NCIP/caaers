<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:noform>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow displayOnly="false" index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
</tags:noform>
