<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="style"%>
<%@attribute name="index" required="true" description="The index of the AE for which the outcome needs to be printed"%>
<%@attribute name="isRoutineFlow" type="java.lang.Boolean" description="Will be true, if this tag is used in context of routine flow" %>
<c:set var="outcomeGroup" value="outcomes${index}" />
<ui:row path="outcomes" >
	<jsp:attribute name="label">
		Outcomes
	</jsp:attribute>
	<jsp:attribute name="value">
	<div class="longselect" >
		<div style="clear:right;">
		<ui:checkbox path="${fieldGroups[outcomeGroup].fields[0].propertyName}">
			<jsp:attribute name="embededJS">
				$('${fieldGroups[outcomeGroup].fields[0].propertyName}').observe('click' , function(e){
					Event.stop(e);
				});
				
			</jsp:attribute>
		</ui:checkbox>
		${fieldGroups[outcomeGroup].fields[0].displayName}
		</div>
		<div style="clear:right;">
		<ui:checkbox path="${fieldGroups[outcomeGroup].fields[1].propertyName}">
			<jsp:attribute name="embededJS">
				$('${fieldGroups[outcomeGroup].fields[1].propertyName}').observe('click' , function(e){
					Event.stop(e);
				});
			</jsp:attribute>
		</ui:checkbox>
		${fieldGroups[outcomeGroup].fields[1].displayName}
		</div>
		<c:set var="len" value="${fn:length(fieldGroups[outcomeGroup].fields)}" />
		<c:forEach items="${fieldGroups[outcomeGroup].fields}" var="field" begin="2" end="${len - 3}">
		  <div style="clear:right;">
			<ui:checkbox path="${field.propertyName}" />${field.displayName}
		  </div>
		</c:forEach>
		<div style="clear:right;">
			<ui:checkbox path="${fieldGroups[outcomeGroup].fields[len - 2].propertyName}">
				<jsp:attribute name="embededJS">
					$('${fieldGroups[outcomeGroup].fields[len - 2].propertyName}').observe('click' , function(e){
						var otherTxtBox = $('${fieldGroups[outcomeGroup].fields[len - 1].propertyName}');
						if(e.element().checked){
						 	otherTxtBox.readOnly = false;
						}
						else {
							otherTxtBox.readOnly = true;
							otherTxtBox.value = '';
						}
					});
				</jsp:attribute>
			</ui:checkbox>${fieldGroups[outcomeGroup].fields[len - 2].displayName}
			<ui:text path="${fieldGroups[outcomeGroup].fields[len - 1].propertyName}">
				<jsp:attribute name="embededJS">
					var otherTxtBox = $('${fieldGroups[outcomeGroup].fields[len - 1].propertyName}');
					if(otherTxtBox.value == ''){
						otherTxtBox.readOnly = true;
					}
				</jsp:attribute>
			</ui:text>
		</div>
	</div>
	</jsp:attribute>
	<jsp:attribute name="embededJS">
		<%-- Script to tackle hospitalization --%>
			$('${not isRoutineFlow ? 'aeReport.': ''}adverseEvents[${index}].hospitalization').observe('change', function(e){
				if(e.element().value == 'YES'){
					$('${fieldGroups[outcomeGroup].fields[1].propertyName}').checked = true;
				}else {
					$('${fieldGroups[outcomeGroup].fields[1].propertyName}').checked = false;
				}
			});
		<%-- Script to tackle death  --%>	
	</jsp:attribute>
</ui:row>

