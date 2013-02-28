<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<form:form>
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
					<form:select path="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].value" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].value">
						<option value=""/>Please Select-- </option>
						<form:option value="Unrelated"/>
						<form:option value="Unlikely Related"/>
						<form:option value="Possibly Related"/>
						<form:option value="Probably Related"/>
						<form:option value="Definitely Related"/>
					</form:select>
</form:form>
