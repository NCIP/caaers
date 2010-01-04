<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<tags:noform>
<c:forEach items="${indfields.fields}" var="_indField" varStatus="status">
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
</tags:noform>