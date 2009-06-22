<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:noform>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow displayOnly="false" index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
</tags:noform>