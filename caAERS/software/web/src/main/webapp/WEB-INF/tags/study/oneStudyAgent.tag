<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true" %>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>


<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="indGroup">ind${index}</c:set>

<chrome:division title="${title}" id="${sectionClass}-${index}" 
	cssClass="${sectionClass}" style="${style}" enableDelete="${enableDelete}" deleteParams="${deleteParams}"
	collapsable="true" collapsed="${collapsed}">

<c:set var="_agentField" value="${fieldGroups[mainGroup].fields[0]}" />
<ui:row path="${_agentField.propertyName}">
	<jsp:attribute name="label">
		<input id="select-agent-${index}" name="agentOrOther${index}" type="radio" style="${not readOnly ? '':'display:none;' }"/>
        ${_agentField.displayName}
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:autocompleter path="${_agentField.propertyName}" 
			required="${_agentField.required}" 
			validationJSClass="${_agentField.validatorClassName}" 
			readonly="${readOnly}" 
			size="${_agentField.attributes.size}"
			title="${field.displayName}"
			enableClearButton="${_agentField.attributes.enableClear}" 
			initialDisplayValue="Begin typing here..." >
			<jsp:attribute name="embededJS">
					Event.observe('${_agentField.propertyName}-input', 'blur', function(){
						$('titleOf_${sectionClass}-${index}').innerHTML = $('${_agentField.propertyName}-input').value;
					})
			</jsp:attribute>
		</ui:autocompleter>
	</jsp:attribute>
</ui:row>

<c:set var="_otherField" value="${fieldGroups[mainGroup].fields[1]}" />
<ui:row path="${_otherField.propertyName}">
	<jsp:attribute name="label">
		<input id="select-other-${index}" name="agentOrOther${index}" type="radio" style="${not readOnly ? '':'display:none;' }"/>
    	${_otherField.displayName}
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:text path="${_otherField.propertyName}" 
			required="${_otherField.required}" 
			validationJSClass="${_otherField.validatorClassName}" 
			readonly="${readOnly}" 
			size="${_otherField.attributes.size}">
			<jsp:attribute name="embededJS">
					Event.observe('${_otherField.propertyName}', 'keyup', function(){
						$('titleOf_${sectionClass}-${index}').innerHTML = $('${_otherField.propertyName}').value;
					})
			</jsp:attribute>
		</ui:text>
	</jsp:attribute>
</ui:row>

<c:set var="_indInfoField" value="${fieldGroups[mainGroup].fields[2]}" />
<ui:row path="${_indInfoField.propertyName}">
	<jsp:attribute name="label">
		<ui:label path="${_indInfoField.propertyName}" text="${_indInfoField.displayName}" required="${_indInfoField.required}" />
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:select options="${_indInfoField.attributes.options}" path="${_indInfoField.propertyName}" 
			readonly="false" displayNamePath="${_indInfoField.propertyName}.displayName"
		 	validationJSClass="${_indInfoField.validatorClassName}" required="${_indInfoField.required}"/>
	</jsp:attribute>
</ui:row>
<!--  Beg of IND fields -->
<c:forEach items="${fieldGroups[indGroup].fields}" var="_indField" varStatus="status">
  <ui:row path="${_indField.propertyName}">
	<jsp:attribute name="label">
		<ui:label path="${_indField.propertyName}" text="${_indField.displayName}"></ui:label>
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:autocompleter path="${_indField.propertyName}" 
			required="${_indField.required}" 
			validationJSClass="${_indField.validatorClassName}" 
			readonly="false" 
			size="${_indField.attributes.size}"
			title="${_indField.displayName}"
			enableClearButton="${_indField.attributes.enableClear}" 
			initialDisplayValue="Begin typing here..." 
			displayNamePath="${_indField.propertyName}.numberAndHolderName"/>
	</jsp:attribute>
</ui:row>
</c:forEach>

<!--  end of IND fields -->

<c:set var="_leadINDField" value="${fieldGroups[mainGroup].fields[3]}" />
<ui:row path="${_leadINDField.propertyName}">
	<jsp:attribute name="label">
		<ui:label path="${_leadINDField.propertyName}" text="${_leadINDField.displayName}" 
			required="${_leadINDField.required}" />
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:select options="${_leadINDField.attributes.options}" path="${_leadINDField.propertyName}" readonly="false"
		 	validationJSClass="${_leadINDField.validatorClassName}" required="${_leadINDField.required}" displayNamePath="${_leadINDField.propertyName}AsString"/>
	</jsp:attribute>
</ui:row>

 <br />
</chrome:division>