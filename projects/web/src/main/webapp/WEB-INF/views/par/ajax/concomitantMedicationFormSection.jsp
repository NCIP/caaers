<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="cmIdxSt">
	<c:set var="cMed" value="${concomitantMedications[index]}" />
	<par:oneConcomitantMedication index="${index}" concomitantMedication="${cMed}" collapsed="${cmIdxSt.index gt 0}"/>
</c:forEach>
</tags:noform>