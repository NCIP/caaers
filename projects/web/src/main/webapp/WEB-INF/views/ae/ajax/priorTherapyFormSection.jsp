<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="ptIndxSt">
	<c:set var="pt" value="${priorTherapies[index]}" />
	<ae:onePriorTherapy index="${index}" priorTherapy="${pt}" collapsed="${ptIndxSt.index gt 0}"/>
</c:forEach>
</tags:noform>
<%--
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<tags:noform>
    <ae:onePriorTherapy index="${param.index}" style="display: none"/>
</tags:noform>
--%>
