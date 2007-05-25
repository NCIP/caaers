<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<div id="rule-${ruleCount}-column-${columnCount}" style="margin-left:200px; display:none" class="lineitem">
	<%--<form:form>--%>
	<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
	<label for="AND">AND</label>		
	<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

	<%--
	<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType">
		<form:options items="${ruleUi.condition[0].domainObject}" itemLabel="displayUri" itemValue="className" />
	</form:select>
	--%>
	
	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType" name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType">
		<c:forEach items="${ruleUi.condition[0].domainObject}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[optionStatus.index].className}">
				${ruleUi.condition[0].domainObject[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</select>
	
	<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

	<%--
	<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this)">
		<form:options items="${ruleUi.condition[0].domainObject[0].field}" itemLabel="displayUri" itemValue="name" />
	</form:select>
	--%>
	
	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" 
			name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this, ${ruleCount})">
		<c:forEach items="${ruleUi.condition[0].domainObject[0].field}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[0].field[optionStatus.index].name}">
				${ruleUi.condition[0].domainObject[0].field[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</select>
	
	<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

	<%--
	<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator">
		<form:options items="${ruleUi.operator}" itemLabel="displayUri" itemValue="name" />
	</form:select>
	--%>

	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator" 
				name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator">
		<c:forEach items="${ruleUi.operator}" varStatus="optionStatus">
			<option value="${ruleUi.operator[optionStatus.index].name}">
				${ruleUi.operator[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</select>
	
	
	<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

	<span id="rule-${ruleCount}-column-${columnCount}-field-value">
	
	<%--
	<form:input path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"/>
	--%>

<span id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value.span">	
	<select id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value" 
			name="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value">
		<c:forEach items="${ruleUi.condition[0].domainObject[0].field[0].validValue}" varStatus="optionStatus">
			<option value="${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].value}">
				${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].displayUri}
			</option>
		</c:forEach>
	</select>
</span>	
	
	<%--
	<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value">
		<option value=""/>Please Select-- </option>
		<form:option value="1"/>
		<form:option value="2"/>
		<form:option value="3"/>
		<form:option value="4"/>
		<form:option value="5"/>
		<form:option value="Yes"/>
		<form:option value="No"/>
		<form:option value="Phase I Trial"/>
	</form:select>
	--%>
	</span>
	
	<a href="javascript:fetchCondition(${ruleCount})">
		<img id="add-column-${ruleCount}" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand; border:0px"/>
	</a>
	<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
		<img id="remove-column-${ruleCount}" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand;  border:0px"/>
	</a>
	
	<%--</form:form>--%>
</div>