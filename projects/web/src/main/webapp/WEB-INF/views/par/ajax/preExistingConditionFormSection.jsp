<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<tags:noform>
<c:forEach items="${indexes}" var="index">
	<c:set var="preCond" value="${preExistingConditions[index]}" />
	<par:onePreExistingCondition index="${index}" preExistingCondition="${preCond.preExistingCondition}" otherValue="${preCond.other}"/>
</c:forEach>
</tags:noform>