<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test='${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].fieldValue.inputType == "multiselect"}'>
		<c:forEach varStatus="optionStatus" items="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}">
			<option value="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].value}">
				${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<option value="">Please select Value</option>
		<c:forEach varStatus="optionStatus" items="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}">
			<option value="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].value}">
				${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</c:otherwise>
</c:choose>
