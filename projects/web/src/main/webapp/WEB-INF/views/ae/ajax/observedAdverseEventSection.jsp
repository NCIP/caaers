<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tags:noform>
	<c:forEach begin="${param.index}" items="${command.adverseEvents}" var="ae" varStatus="status">
		<ae:oneRoutineAdverseEvent index="${status.index}" adverseEvent="${ae}" collapsed="false" enableDelete="true" />
	</c:forEach>	
</tags:noform>