<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<tags:noform>
<c:set var="size" value="${fn:length(indexes)}" />
<c:forEach items="${indexes}" var="index" varStatus="ptIndxSt">
	<c:set var="pt" value="${priorTherapies[index]}" />
	<par:onePriorTherapy index="${index}" 
		priorTherapy="${pt}" 
		collapsed="${ptIndxSt.index gt 0}" 
		showNoPriorTherapy="${index eq 0  and empty pt.priorTherapy}"/>
</c:forEach>
<c:if test="${size eq 0}">
<script type="text/javascript">
$('priortherapy-btn').disabled = false;
</script>
</c:if>
</tags:noform>
