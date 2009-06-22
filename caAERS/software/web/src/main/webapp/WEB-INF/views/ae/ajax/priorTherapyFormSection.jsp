<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<tags:noform>
<c:set var="size" value="${fn:length(indexes)}" />
<c:forEach items="${indexes}" var="index" varStatus="ptIndxSt">
	<c:set var="pt" value="${priorTherapies[index]}" />
	<ae:onePriorTherapy index="${index}" priorTherapy="${pt}" collapsed="${ptIndxSt.index gt 0}" showNoPriorTherapy="${index eq 0  and empty pt.priorTherapy}"/>
</c:forEach>
<c:if test="${size eq 0}">
<script type="text/javascript">
$('priortherapy-btn').disabled = false;
</script>
</c:if>
</tags:noform>
<%--
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<tags:noform>
    <ae:onePriorTherapy index="${param.index}" style="display: none"/>
</tags:noform>
--%>
