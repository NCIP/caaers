<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="cmIdxSt">
	<c:set var="cMed" value="${concomitantMedications[index]}" />
	<ae:oneConMed index="${index}" concomitantMedication="${cMed}" collapsed="false"/>
</c:forEach>
</tags:noform>
<%-- 
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<tags:noform>
    <ae:oneConMed index="${param.index}" style="display: none"/>
</tags:noform>
--%>
