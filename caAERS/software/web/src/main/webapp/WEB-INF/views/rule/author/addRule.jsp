<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<div id="rule-${ruleCount + 1}" style="display:none">
<chrome:division title="Rule - (${ruleCount + 1})" id="rule-div-${ruleCount + 1 }" collapsable="true" deleteParams="${ruleCount + 1}" enableDelete="true">
<div id="crap-${ruleCount + 1}">
  <%--<form:form>--%>
  <div id="rule-condition-action-container-${ruleCount + 1}">
    <div class="row" id="rule-${ruleCount + 1}-columns">
      <c:forEach varStatus="conditionStatus" begin="0" items="${command.ruleSet.rule[ruleCount].condition.column}">
        <div id="condition-${conditionStatus.index}" class="lineitem one-condition">
          <c:choose>
            <c:when test="${conditionStatus.index == 0}">
              <label for="IF" style="padding-left:9px; margin-right:8px; font-weight:bold;">If</label>
              </c:when>
            <c:otherwise>
              <label for="AND" style="font-weight:bold;">And</label>
            </c:otherwise>
          </c:choose>
          
          <select id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].objectType" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].objectType" onchange="handleDomainObjectonChange(this, ${ruleCount})">
            <option value="">Please select domain object</option>
            <c:forEach items="${ruleUi.condition[0].domainObject}" varStatus="optionStatus">
              <option value="${ruleUi.condition[0].domainObject[optionStatus.index].className}"> ${ruleUi.condition[0].domainObject[optionStatus.index].displayUri} </option>
            </c:forEach>
          </select>
          
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].identifier" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].identifier" value="${ruleUi.condition[0].domainObject[0].identifier}"/>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].displayUri" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].displayUri" value="${ruleUi.condition[0].domainObject[0].displayUri}"/>
          
          <select id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].fieldName" 
						name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this, ${ruleCount}, ${conditionStatus.index})">
            <option value="">Please select field</option>
            <%--
						<c:forEach items="${ruleUi.condition[0].domainObject[0].field}" varStatus="optionStatus">
							<option value="${ruleUi.condition[0].domainObject[0].field[optionStatus.index].name}">
							${ruleUi.condition[0].domainObject[0].field[optionStatus.index].displayUri}
							</option>
						</c:forEach>
						--%>
          </select>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].expression" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].expression" value="${ruleUi.condition[0].domainObject[0].field[0].expression}"/>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].grammerPrefix" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].grammerPrefix" value="${ruleUi.condition[0].domainObject[0].field[0].grammer.prefix}"/>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].grammerPostfix" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].grammerPostfix" value="${ruleUi.condition[0].domainObject[0].field[0].grammer.postfix}"/>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].displayUri" name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].displayUri" value="${ruleUi.condition[0].domainObject[0].field[0].displayUri}"/>
          <input type="hidden" id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].displayUri"  name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].displayUri" value="${ruleUi.condition[0].domainObject[0].field[0].operator[0].readableText}"/>
          
          <select id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].evaluator" 
						name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].evaluator"
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
          <span id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].value.span">
          <select id="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].value" 
						name="ruleSet.rule[${ruleCount}].condition.column[${conditionStatus.index}].fieldConstraint[0].literalRestriction[0].value"
						onchange="onCategoryChange(this, ${ruleCount})">
            <option value="">Please select value</option>
            <%--
						<c:forEach items="${ruleUi.condition[0].domainObject[0].field[0].validValue}" varStatus="optionStatus">
							<option value="${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].value}">
							${ruleUi.condition[0].domainObject[0].field[0].validValue[optionStatus.index].displayUri}
							</option>
						</c:forEach>
						--%>
          </select>
          </span>
          <c:if test="${columnCount > 0}"> <a href="javascript:removeCondition(${ruleCount}, ${columnCount})"> <img id="remove-column-${ruleCount}" src="<c:url value="/images/rule/remove_condition.gif"/>" align="absmiddle" style="cursor:hand;  border:0px"/> </a> </c:if>
        </div>
        
      </c:forEach>
    </div>
	<div class="new_condition">
		<tags:button id="add-condition-${ruleCount }" color="blue" type="button" value="Add condition" size="small" icon="add" onclick="fetchCondition(${ruleCount })"/>
    </div>
	<div class="row">
      <div class="lineitem">
        <label for="action" class="label" style="font-weight:bold;">Actions</label>
      </div>
      
      <div id="action-template"  class="lineitem">
        <select id="ruleSet.rule[${ruleCount}].action" name="ruleSet.rule[${ruleCount}].action" multiple="multiple" size="3">
          <c:choose>
            <c:when test='${command.ruleSetName == "Mandatory Sections Rules"}'>
              <c:forEach var="reportSectionName" items="${command.reportSectionNames}">
                <option value="${reportSectionName}">${reportSectionName.displayName}</option>
              </c:forEach>
            </c:when>
            <c:when test='${command.ruleSetName == "SAE Reporting Rules"}'>
              <c:forEach var="reportDefinition" items="${command.reportDefinitions}">
                <option value="${reportDefinition.name}">${reportDefinition.name}</option>
              </c:forEach>
              <option value="IGNORE">No Report Required (Study Level Exception Rule)</option>
            </c:when>
            <c:when test="${command.ruleSetName eq 'Field Rules'}">
              <c:forEach var="mandatoryness" items="${command.mandatoryOptions}">
                 <option value="${mandatoryness.name}">${mandatoryness.displayName}</option>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <option value="ROUTINE_AE">Assess as Routine AE</option>
              <option value="SERIOUS_ADVERSE_EVENT">Assess as Serious AE</option>
              <c:forEach var="reportDefinition" items="${command.reportDefinitions}">
                <option value="${reportDefinition.name}">${reportDefinition.name}</option>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </select>
      </div>
    </div>
  </div>
  </chrome:division>
  <%--</form:form>--%>
</div>
