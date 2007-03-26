<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<div id="rule-${ruleCount + 1}" class="section">
	<h3 style="position:relative; float:left" class="handle"">
	<span style="position:relative; float:left">${command.ruleSet.rule[ruleCount].metaData.name}</span>
	<a href="javascript:deleteRule(${ruleCount + 1})">
		<img id="close-image" src="/caaers/images/rule/window-close.gif"  align="absmiddle"  style="position:relative; float:right; height:18px"/>
	</a>
	<img src="/caaers/images/chrome/spacer.gif" style="position:relative; float:right;width:5px;height:10px" align="absmiddle" />
	<a href="javascript:toggle(${ruleCount + 1})">
		<img id="toggle-image-${ruleCount + 1}" onclick="" src="/caaers/images/rule/window-minimize.gif" valign="top" align="absmiddle"  style="position:relative; float:right; height:18px"/>
	</a>
	</h3>
	<div id="crap-${ruleCount + 1}">
	<form:form>

		<div style="margin-left:50px;">
			<label class="label" for="condition">Condition</label>
		</div>
		<div class="row" id="rule-${ruleCount + 1}-columns">
			<br/>
			<c:forEach varStatus="conditionStatus" begin="0" items="${command.ruleSet.rule[ruleCount].condition.column}">
				<div id="condition-${conditionStatus.index}" style="margin-left:200px;" class="lineitem">
					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
					<c:choose>
						<c:when test="${conditionStatus.index == 0}">
						<label for="IF">IF</label><img src="/caaers/images/chrome/spacer.gif" style="width:15px;height:1px" align="absmiddle" />
						</c:when>
						<c:otherwise><label for="AND">AND</label></c:otherwise>
					</c:choose>													
					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

					<form:select path="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].objectType">
						<form:options items="${ruleUi.condition[0].domainObject}" itemLabel="displayUri" itemValue="className" />
					</form:select>

					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

					<form:select path="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this)">
						<form:options items="${ruleUi.condition[0].domainObject[0].field}" itemLabel="displayUri" itemValue="name" />
					</form:select>

					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

					<form:select path="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].evaluator">
						<form:options items="${ruleUi.operator}" itemLabel="displayUri" itemValue="name" />
					</form:select>

					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

					<form:input path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"/>
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

					<a href="javascript:fetchCondition(${ruleCount})">
						<img id="add-column-${ruleCount}" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand; border:0px"/>
					</a>
					<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
						<img id="remove-column-${ruleCount}" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand;  border:0px"/>
					</a>
				</div>

				<br/>
			</c:forEach>
			</div>

			<div class="row">
				<div  style="margin-left:50px;"><label for="action" class="label">Action</label></div>
				<br/>
				<div id="action-template"  style="margin-left:200px;">
					<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
					<form:select path="ruleSet.rule[${ruleCount}].action.actionId">
						<option value=""/>Please Select-- </option>
						<form:option value="1">Pending AE Report 5 day Notification</form:option>
						<form:option value="2">Pending AE Report 10 day Notification</form:option>
						<form:option value="3">Send Email to Site IRB</form:option>
						<form:option value="4">Send Email to Study Research Nurse</form:option>
					</form:select>
					<a href="javascript:addAction(${ruleCount})">
						<img id="add-action-image" onclick="addAction(${ruleCount})" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand"/>
					</a>
					<a href="javascript:addAction(${ruleCount})">
						<img id="remove-action-image" onclick="deleteAction(${ruleCount})" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand"/>											
					</a>
				</div>
			</div>
		</div>
	</form:form>
</div>