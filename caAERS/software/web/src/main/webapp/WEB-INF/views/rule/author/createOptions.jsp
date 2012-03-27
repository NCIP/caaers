<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test='${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].fieldValue.inputType == "multiselect"}'>
		<c:forEach varStatus="optionStatus" items="${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}">
			<option value="${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].value}">
				${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<option value="">Please select Value</option>
		<c:forEach varStatus="optionStatus" items="${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}">
			<option value="${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].value}">
				${command.ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</c:otherwise>
</c:choose>
