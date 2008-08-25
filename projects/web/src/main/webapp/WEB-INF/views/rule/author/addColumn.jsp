<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<div id="rule-${ruleCount}-column-${columnCount}" style="margin-left:50px; display:none" class="lineitem">
	<img src="<c:url value="/images/chrome/spacer.gif"/>" style="width:10px;height:10px" align="absmiddle" />
	<label for="AND">AND</label>		
	<img src="<c:url value="/images/chrome/spacer.gif"/>" style="width:10px;height:10px" align="absmiddle" />

	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType" onchange="handleDomainObjectonChange(this, ${ruleCount})">>
	        <option value="">Please select Domain Object</option>
		<c:forEach items="${ruleUi.condition[0].domainObject}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[optionStatus.index].className}">
				${ruleUi.condition[0].domainObject[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</select>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].identifier" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].identifier" value="${ruleUi.condition[0].domainObject[0].identifier}"/>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].displayUri" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].displayUri" value="${ruleUi.condition[0].domainObject[0].displayUri}"/>

	<img src="<c:url value="/images/chrome/spacer.gif"/>" style="width:10px;height:10px" align="absmiddle" />

	
	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" 
			name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this, ${ruleCount}, ${columnCount})">
		<option value="">Please select Field</option>	
		<%--
		<c:forEach items="${ruleUi.condition[0].domainObject[0].field}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[0].field[optionStatus.index].name}">
				${ruleUi.condition[0].domainObject[0].field[optionStatus.index].displayUri}
			</option>
		</c:forEach>
		--%>
	</select>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].expression" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].expression" value="${ruleUi.condition[0].domainObject[0].field[0].expression}"/>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].grammerPrefix" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].grammerPrefix" value="${ruleUi.condition[0].domainObject[0].field[0].grammer.prefix}"/>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].grammerPostfix" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].grammerPostfix" value="${ruleUi.condition[0].domainObject[0].field[0].grammer.postfix}"/>
	
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].displayUri" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].displayUri" value="${ruleUi.condition[0].domainObject[0].field[0].displayUri}"/>
	<input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].displayUri"  name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].displayUri" value="${ruleUi.condition[0].domainObject[0].field[0].operator[0].readableText}"/>
	
	<img src="<c:url value="/images/chrome/spacer.gif"/>" style="width:10px;height:10px" align="absmiddle" />

	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator" 
				name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator"
				onchange="handleOperatorOnchange(this, ${ruleCount})">
		<option value="">Please select operator</option>
		<%--
		<c:forEach items="${ruleUi.condition[0].domainObject[0].field[0].operator}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[0].field[0].operator[optionStatus.index].name}">
				${ruleUi.condition[0].domainObject[0].field[0].operator[optionStatus.index].displayUri}
			</option>
		</c:forEach>
		--%>
	</select>
	
	
	<img src="<c:url value="/images/chrome/spacer.gif"/>" style="width:10px;height:10px" align="absmiddle" />

	<span id="rule-${ruleCount}-column-${columnCount}-field-value">
	

<span id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value.span">	
	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value" 
			name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value" >
		<option value="">Please select Value</option>		
		<%--
		<c:forEach items="${ruleUi.condition[0].domainObject[0].field[0].validValue}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].value}">
				${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
		--%>
	</select>
</span>	
	
	</span>
	
	<a href="javascript:fetchCondition(${ruleCount})">
		<img id="add-column-${ruleCount}" src="<c:url value="/images/rule/add_condition.gif"/>" align="absmiddle" style="cursor:hand; border:0px"/>
	</a>
	<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
		<img id="remove-column-${ruleCount}" src="<c:url value="/images/rule/remove_condition.gif"/>" align="absmiddle" style="cursor:hand;  border:0px"/>
	</a>
	
</div>