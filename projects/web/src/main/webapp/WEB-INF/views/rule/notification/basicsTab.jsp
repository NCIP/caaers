<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruletags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="reportDef"/>
    <script>
     
      Event.observe(window, "load", function() {
    	//Calls ReportDefinitionAjaxFacade:matchOrganization(..)
    	AE.createStandardAutocompleter(
    		'organization', function(autocompleter, text) {
         		reportDef.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	}, function(organization) { return organization.name });
        
        //populate the name of the associated organization in 'organization-input' field	
      	$('organization-input').value = '${command.reportDefinition.organization.name}';
      });
      
    </script>
    
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false">
		<jsp:attribute name="singleFields">
			<div>
			<tags:renderRow field="${fieldGroups.reportDefinitionOrganization.fields[0]}" />
			<hr />
			</div>
            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.reportDefinitionFieldGroup.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
               <input type="hidden" name="lastPointOnScale" value=""/>
               <input type="hidden" name="notificationType" value="EMAIL_NOTIFICATION" />
            </div>
		</jsp:attribute>
	</tags:tabForm> 
    
</body>


</html>