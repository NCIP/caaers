<%@tag import="gov.nih.nci.cabig.caaers.domain.INDType" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"  %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="studyAgent" type="gov.nih.nci.cabig.caaers.domain.StudyAgent" required="true" description="The study agent being rendered"%>

<c:set var="deleteParams">'removeStudyAgent',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="indGroup">ind${index}</c:set>

<c:set var="_agentName" value="${studyAgent.agentName}" />
<c:if test="${not empty studyAgent.agent}">
   <c:set var="_agentName" value="${studyAgent.agent.nscNumber}${studyAgent.agent.name ne null ? '::':''}${studyAgent.agent.name}" />
</c:if>

<c:set var="readOnly" value="${_agentName ne 'no-agent-name'}" />

<chrome:division id="sa-section-${index}" title="${_agentName}" cssClass="sa-section" 
	collapsable="true" collapsed="${_agentName ne 'no-agent-name'}" enableDelete="true" deleteParams="${deleteParams}">

<c:set var="_agentField" value="${fieldGroups[mainGroup].fields[0]}" />
<c:set var="_otherField" value="${fieldGroups[mainGroup].fields[1]}" />
<c:set var="_indInfoField" value="${fieldGroups[mainGroup].fields[2]}" />
<c:set var="_leadINDField" value="${fieldGroups[mainGroup].fields[3]}" />


<ui:row path="${_agentField.propertyName}">
	<jsp:attribute name="label">
		<c:if test="${not readOnly}">
		<input id="select-agent-${index}" name="agentOrOther${index}" type="radio" onclick="toggleAgentOrOther(${index})" ${(addAgentFlow || (_agentName eq 'no-agent-name')) ? 'checked':'' } />
		</c:if>
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
			initialDisplayValue="Begin typing here..." 
			displayNamePath="study.studyAgents[${index}].agentName" >
			<jsp:attribute name="populatorJS"> 
			 function(autocompleter, text) {
         		createStudy.matchAgents(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	 }
            </jsp:attribute>
            <jsp:attribute name="selectorJS"> 
             function(agent) { 
        		return agent.nscNumber + "<b> ::</b> " + agent.name 
        	 }
            </jsp:attribute>
			<jsp:attribute name="embededJS">
			  if(${not readOnly}){
			  	Event.observe('${_agentField.propertyName}-input', 'blur', function(){
					$('titleOf_sa-section-${index}').innerHTML = $('${_agentField.propertyName}-input').value;
				})
			  }
			</jsp:attribute>
		</ui:autocompleter>
	</jsp:attribute>
</ui:row>


<ui:row path="${_otherField.propertyName}">
	<jsp:attribute name="label">
		<c:if test="${not readOnly}">
		<input id="select-other-${index}" name="agentOrOther${index}" type="radio"  onclick="toggleAgentOrOther(${index})"/>
		</c:if>
    	${_otherField.displayName}
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:text path="${_otherField.propertyName}" 
			required="${_otherField.required}" 
			validationJSClass="${_otherField.validatorClassName}" 
			readonly="${readOnly}"
			disabled="${addAgentFlow || (_agentName eq 'no-agent-name')}"
			size="${_otherField.attributes.size}">
			<jsp:attribute name="embededJS">
			if(${not readOnly}){
				Event.observe('${_otherField.propertyName}', 'keyup', function(){
					$('titleOf_sa-section-${index}').innerHTML = $('${_otherField.propertyName}').value;
				})
			}
			</jsp:attribute>
		</ui:text>
	</jsp:attribute>
</ui:row>


<ui:row path="${_indInfoField.propertyName}">
	<jsp:attribute name="label">
		<ui:label path="${_indInfoField.propertyName}" text="${_indInfoField.displayName}" required="${_indInfoField.required}" />
	</jsp:attribute>
	<jsp:attribute name="value">
		<ui:select options="${_indInfoField.attributes.options}" path="${_indInfoField.propertyName}" readonly="false" displayNamePath="${_indInfoField.propertyName}.displayName" 
		validationJSClass="${_indInfoField.validatorClassName}" required="${_indInfoField.required}">
		 	<jsp:attribute name="embededJS">
		 		Event.observe('${_indInfoField.propertyName}', 'change', function(evt){
		 			var leadIndIndicatorEl = $('${_leadINDField.propertyName}');
					if(evt.target.value == 'NA' || evt.target.value == 'NA_COMMERCIAL' || evt.target.value == 'IND_EXEMPT'){
						if(leadIndIndicatorEl){
							$('${_leadINDField.propertyName}-row').hide();
							leadIndIndicatorEl.clear();
						}
					}else{
						if(leadIndIndicatorEl){
							$('${_leadINDField.propertyName}-row').show();
						}
					}
					
					//OTHER - then show the IND fields
					if(evt.target.value == 'OTHER'){
						fireAction('addIND', ${index});
					}else{
						fireAction('removeIND', ${index});
					}
		 		});
		 	</jsp:attribute>
		 	</ui:select>
		    <tags:indicator id="_SA-IND-${index}_indicator" />
	</jsp:attribute>
</ui:row>
<!--  Beg of IND fields -->
<span id="_SA-IND-${index}"></span>
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
			displayNamePath="${_indField.propertyName}.numberAndHolderName">
		<jsp:attribute name="populatorJS">
			function(autocompleter, text) {
         		createStudy.matchINDs(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	}
		</jsp:attribute>
		<jsp:attribute name="selectorJS"> 
             function(ind) { 
        		return ind.strINDNo + '::' + ind.holderName; 
        	}
        </jsp:attribute>
		</ui:autocompleter>	
	</jsp:attribute>
</ui:row>
</c:forEach>

<!--  end of IND fields -->

<c:set var="_leadINDStyle" value="${(not(sa.indType eq INDType.NA || sa.indType eq INDType.NA_COMMERCIAL || sa.indType eq INDType.IND_EXEMPT)) ? '' : 'display:none;'}" />
<ui:row path="${_leadINDField.propertyName}" style="_leadINDStyle">
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