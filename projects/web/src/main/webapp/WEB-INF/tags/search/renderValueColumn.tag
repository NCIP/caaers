<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="criteriaParameter" type="gov.nih.nci.cabig.caaers.web.search.AdvancedSearchCriteriaParameter" required="false" description="This is the criteria parameter row in command that needs to be rendered" %>
<%@attribute name="uiAttribute" type="gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute" required="true" description="This is the UiAttribute for which the value column needs to be rendered" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>

<%-- This is for handling text-field type input --%>
<c:if test="${uiAttribute.fieldType eq 'text-field'}">
	<c:if test="${criteriaParameter == null}">
		<input type="text" name="criteriaParameters[${index}].value" />
	</c:if>

	<c:if test="${criteriaParameter != null}">
		<input type="text" name="criteriaParameters[${index}].value" value="${criteriaParameter.value }"></input>
	</c:if>
</c:if>
<%-- Done with handling text-field type input --%>

<%-- This is for handling drop-down type input --%>
<c:if test="${uiAttribute.fieldType eq 'drop-down'}">
	<SELECT style="width:200px;" id="value-${index }" name="criteriaParameters[${index }].value">
		<OPTION selected value="none">Please select</OPTION>
		<c:if test="${criteriaParameter == null }">
			<c:forEach items="${uiAttribute.validValue}" var="validValue" varStatus="validValueStatus">
				<OPTION value="${validValue.value }">${validValue.displayUri }</OPTION>
			</c:forEach>
		</c:if>
		<c:if test="${criteriaParameter != null }">
			<c:forEach items="${uiAttribute.validValue }" var="validValue" varStatus="validValueStatus">
				<c:if test="${validValue.value ne criteriaParameter.value}">
					<OPTION value="${validValue.value }">${validValue.displayUri }</OPTION>
				</c:if>
				<c:if test="${validValue.value eq criteriaParameter.value}">
					<OPTION value="${validValue.value }" selected>${validValue.displayUri }</OPTION>
				</c:if>
			</c:forEach>
		</c:if>
			
			
	</SELECT> 
</c:if>
<%-- Done with handling drop-down type input --%>

<%-- This is for handling Autocompleter type input  --%>
<c:if test="${uiAttribute.fieldType eq 'Autocompleter'}">
	<c:if test="${criteriaParameter == null }">
		<tags:autocompleter displayName="abcd" propertyName="criteriaParameters[${index}].value" size="70" initialDisplayValue="Begin typing here" enableClear="true"/>
		<input type="hidden" name="criteriaParameters[${index }].displayValue" id="criteriaParameters[${index }].displayValue" value=""/>
	</c:if>
	<c:if test="${criteriaParameter != null }">
		<tags:autocompleter displayName="abcd" propertyName="criteriaParameters[${index}].value" size="70" initialDisplayValue="${criteriaParameter.displayValue}" initialValue="${criteriaParameter.value}" enableClear="true"/>
		<input type="hidden" name="criteriaParameters[${index }].displayValue" id="criteriaParameters[${index }].displayValue" value="${criteriaParameter.displayValue }"/>
	</c:if>
	<script>
        
        var ${uiAttribute.label}AutocompleterProps = {
        	basename: "criteriaParameters[${index}].value",
        	displayName: "criteriaParameters[${index}].displayValue",
        	populator: function(autocompleter, text){
        		advSearch.${uiAttribute.ajaxMethod}(text, function(values) {
					autocompleter.setChoices(values);
				})        	
        	},
        	valueSelector: function(obj) {
        		return obj.displayName;
        	}
        }
        acCreate(${uiAttribute.label}AutocompleterProps);
	</script>
</c:if>
<%-- Done with handling Autocompleter type input --%>