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
 <tags:dwrJavascriptLink objects="createAE,createStudy"/>
 <tags:stylesheetLink name="pw_default" />
 <tags:stylesheetLink name="pw_alphacube" />
 <script>
 	var descArray = new Array();
 	var win;
 	var index;
 	var selectElement;
 	
 	function addedReportingPeriod(periodId, periodName){
 		win.hide();
 		var length = selectElement.options.length;

 		// deteremine if a new reporting period was added
 		if(selectElement.options[selectElement.selectedIndex].value != periodId){
 			var index = selectElement.options.length;
 			selectElement.options[index-2] = new Option(periodName, periodId);
			selectElement.selectedIndex = selectElement.options.length - 2;
		}
		loadReportingPeriod();	
 	}
 	
 	//function displayReportingPeriodPopup(participantId, studyId, reportingPeriodId){
 	function displayReportingPeriodPopup(reportingPeriodId){
 		var participantId = ${command.assignment.participant.id}
 		var studyId = ${command.assignment.studySite.study.id}
 		var params = '';
 		if(reportingPeriodId == '')
 			params = "?studyId=" + studyId + "&participantId=" + participantId;
 		else
 			params = "?studyId=" + studyId + "&participantId=" + participantId + "&id=" + reportingPeriodId;
 		var url = "https://localhost:8443/caaers/pages/ae/createReportingPeriod" + params + "&subview=";
 		
		win = new Window({className:"alphacube", destroyOnClose:true, title:"Reporting Period Information",  width:700,  height:525, 
			url: url, top: 0, left: 300});
		win.show(true);
	}
	
	function isTermAgainAdded( termID )
    {
      var listOfTermIds = $$('.eachRowTermID');
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
        if( termID == listOfTermIds[i].value)
        {
          return true;
        }      
      } 
      return false;
    }
	
	function myCallback(selectedTerms){
		var listOfTermIDs = new Array();
	    var listOfTerms = new Array();
	  	
	  		$H(selectedTerms).keys().each( function(termID) {
	  		
	  		var term = $H( selectedTerms ).get(termID);
	  		if( !isTermAgainAdded(termID))
	  		{
	  		  listOfTermIDs.push( termID );
	  		  listOfTerms.push(term );
	        }
	  	   });
	  	   
	  	   createAE.addObservedAE(listOfTermIDs, listOfTerms, function(responseStr)
	  	   {
	  	   	new Insertion.After('observedBlankRow', responseStr);
	  	   });
	}
	
	function loadReportingPeriod(){
		if(selectElement.selectedIndex == selectElement.options.length - 1){
			var reportingPeriodId = '';
			displayReportingPeriodPopup(reportingPeriodId);
		}else{
			document.addRoutineAeForm._action.value = 'selectReportingPeriod';
			document.addRoutineAeForm.submit();
		}
	}
	
	Event.observe(window, "load", function(){
		selectElement = document.getElementById('adverseEventReportingPeriod');
		var optn = document.createElement("OPTION");
		optn.text = 'Create New';
		optn.value = '-1';
		selectElement.options.add(optn);
		
		//Event.observe("new_button","click",function() { 
		//var reportingPeriodId = '';
		//displayReportingPeriodPopup(reportingPeriodId) })
		
		if(${command.adverseEventReportingPeriod.id != null})
			Event.observe("edit_button", "click", function() {
			var reportingPeriodId = ${command.adverseEventReportingPeriod.id}
			displayReportingPeriodPopup(reportingPeriodId);
			 })
		
		Event.observe("adverseEventReportingPeriod", "change", function(){
			loadReportingPeriod();
		})

})           

 </script>
 
 </head>
 <body>
	 <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes" formName="addRoutineAeForm">
        <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
      	
      	<jsp:attribute name="singleFields">
      	<input type="hidden" name="_action" id="_action" value="">
      	
      	
		<%-- <input id="new_button" type="button" value="Add New Reporting Period" /> --%>
		<c:forEach items="${fieldGroups.reportingPeriodFG.fields}" var="field">
      		<tags:renderRow field="${field}" />
      	</c:forEach>
      	<c:if test='${command.adverseEventReportingPeriod != null}'>
      		<input id="edit_button" type="button" value="Edit Reporting Period"/>
      	</c:if>
      		
		<div class="leftpanel">
			<c:if test='${command.adverseEventReportingPeriod != null}'>
				<c:forEach items="${fieldGroups.reportingPeriodDetailsFG.fields}" var="field">
      				<tags:renderRow field="${field}" />
      			</c:forEach>
			</c:if>
		</div>
		<div class="rightpanel">
			<c:if test='${command.adverseEventReportingPeriod != null}'>
				<c:forEach items="${fieldGroups.treatmentAssignmentFG.fields}" var="field">
      				<tags:renderRow field="${field}" />
      			</c:forEach>
      		</c:if>	
		</div>
		</jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        	<chrome:division title="Solicited adverse event(s)">
        		<center>
        			<c:if test='${command.adverseEventReportingPeriod != null}'>
        				<table id="solicitedTable" width="100%" class="tablecontent">
    						<tr>
    							<th scope="col" align="left"><b>Term</b> </th>
    							<th scope="col" align="left"><b>Grade</b> </th>
    							<th scope="col" align="left"><b>Attribution</b> </th>
    							<th scope="col" align="left"><b>Hospitalization</b> </th>
    							<th scope="col" align="left"><b>Expected</b> </th>
    						</tr>
    						<tr id="solicitedBlankRow" />
    					
            				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            					<c:if test="${ae.solicited == true}">
	            					<ae:oneSaeRow index="${status.index}"/>
	            				</c:if>
            				</c:forEach>
            			</table>
            		</c:if>	
            	</center>
            </chrome:division>
            
        
        	
        	<chrome:division title="Observed adverse event(s)">
        		<c:if test='${command.adverseEventReportingPeriod != null}'>
        			<tags:aeTermQuery isMeddra="${not empty command.study.aeTerminology.meddraVersion}"  callbackFunctionName="myCallback" version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}" title="Choose CTC terms">
        			</tags:aeTermQuery>
        			<%-- <c:if test='${hasObservedEvent == true}'> --%>
        				<table id="observedTable" width="100%" class="tablecontent">
    						<tr>
    							<th scope="col" align="left"><b><tags:requiredIndicator/>Term</b> </th>
    							<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    							<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution</b> </th>
    							<th scope="col" align="left"><b><tags:requiredIndicator/>Hospitalization</b> </th>
    							<th scope="col" align="left"><b><tags:requiredIndicator/>Expected</b> </th>
    						</tr>
    						<tr id="observedBlankRow" />
    					
            				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            					<c:if test="${ae.solicited == false}">
	            					<ae:oneSaeRow index="${status.index}"/>
	            				</c:if>
            				</c:forEach>
            			</table>
        			<%-- </c:if> --%>
        		</c:if>
        	</chrome:division>
       </jsp:attribute>
       
    </tags:tabForm>
    
 </body>
</html>