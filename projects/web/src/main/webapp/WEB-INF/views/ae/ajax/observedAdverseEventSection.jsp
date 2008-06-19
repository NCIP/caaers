<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tags:noform>
	<c:forEach begin="${index}" end="${fn:length(command.adverseEventReportingPeriod.adverseEvents) - 1}" varStatus="status">
		<ae:oneSaeRow index="${status.index}"  style="display: none" />
	</c:forEach>	
</tags:noform>