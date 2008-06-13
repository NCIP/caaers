<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<!-- <link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>"> -->
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
 <head>
 <tags:includeScriptaculous />
 <tags:includePrototypeWindow />
 <script>
 	var descArray = new Array();
 	function displayReportingPeriodPopup(participantId, studyId, reportingPeriodId){
 		var participantId = ${command.assignment.participant.id}
 		var studyId = ${command.assignment.studySite.study.id}
 		var params = "?studyId=" + studyId + "&participantId=" + participantId;
 		var url = "https://localhost:8443/caaers/pages/ae/createReportingPeriod" + params;
 		var repPeriodWindow = new Window({className: "alphacube",  width:500, height:400, 
		 resizable: true, title: "Reporting period information", draggable: true, closable: true});
		repPeriodWindow.setAjaxContent(url,{method: 'get', type: 'html'}, true, true);
		repPeriodWindow.showCenter(true);
	}
	 
	Event.observe(window, "load", function(){
		Event.observe("new_button","click",function() { 
		var participantId = ${command.assignment.participant.id}
 		var studyId = ${command.assignment.studySite.study.id}
 		var reportingPeriodId = '';
		displayReportingPeriodPopup(participantId, studyId, reportingPeriodId) })
		
		Event.observe("edit_button","click",function() { 
		var participantId = ${command.assignment.participant.id}
 		var studyId = ${command.assignment.studySite.study.id}
 		var reportingPeriodId = '';
		displayReportingPeriodPopup(participantId, studyId, reportingPeriodId) })
		
		//push the description into the array
		<c:forEach items="${command.assignment.studySite.study.treatmentAssignments}" var="ta">
        	descArray.push("${ta.escapedDescription}");
        </c:forEach>			
			
		// treatment dropdown.
		$('adverseEventReportingPeriod.treatmentAssignment').observe("change", function(event){
			selIndex = $('adverseEventReportingPeriod.treatmentAssignment').selectedIndex;
			if(selIndex > 0){
				$('tacDescription').value = descArray[selIndex-1];
			}else{
				$('tacDescription').clear();
			}
		});
		
	})           

 </script>
 <tags:stylesheetLink name="pw_default" />
  <tags:stylesheetLink name="pw_alphacube" />
 </head>
 <body>
	 <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes">
        <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
      		
      	<jsp:attribute name="singleFields">
      		<c:forEach items="${fieldGroups.reportingPeriod.fields}" var="field">
      			<tags:renderRow field="${field}" />
      			<c:if test="${field.propertyName eq 'adverseEventReportingPeriod'}">
      				<input id="edit_button" type="button" value="Edit" />
      				<input id="new_button" type="button" value="Add New" />
      			</c:if>
      		</c:forEach>
		</jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        	<chrome:division title="Solicited adverse event(s)">
        		<center>
        			<table id="test" width="100%" class="tablecontent">
    					<tr>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Term</b> </th>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution</b> </th>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Hospitalization</b> </th>
    						<th scope="col" align="left"><b><tags:requiredIndicator/>Expected</b> </th>
    					</tr>
    					<tr id="koi" />
    				
            			<c:forEach items="${command.saeList}" varStatus="status">
            				<ae:oneSaeRow index="${status.index}"/>
            			</c:forEach>
            		</table>
            	</center>
        	</chrome:division>
        
        	<chrome:division title="Observed adverse event(s)">
        		<tags:aeTermQuery version="3" isMeddra="false" callbackFunctionName="abcd">
        		</tags:aeTermQuery>
        	</chrome:division>
        </jsp:attribute>

    </tags:tabForm>
 </body>
</html>