<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach varStatus="optionStatus" items="${ruleUi.condition[0].domainObject[0].field[fieldIndex].validValue}">
	<option value="${ruleUi.condition[0].domainObject[0].field[fieldIndex].validValue[optionStatus.index].value}">
		${ruleUi.condition[0].domainObject[0].field[fieldIndex].validValue[optionStatus.index].displayUri}
	</option>
</c:forEach>
