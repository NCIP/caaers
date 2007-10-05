<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="ctms" uri="http://gforge.nci.nih.gov/projects/ctmscommons/taglibs/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
    </style>
    <tags:includeScriptaculous/>
    <script type="text/javascript">
      Event.observe(window, "load", function() {
      	setUpEventObserving();
       	disableCheckboxes();
        captureSelectedCheckboxNames();
         
		Event.observe('command', "reset", function() {
			disableCheckboxes();
			$('manualselect').disabled = false;
      	});
      	
      	if($('manualselect')){
      	 Event.observe('manualselect', "click", function() {
      		enableManualSelection();
      	 });
      	}
      	
      	if($('manualselect2')){
      	 Event.observe('manualselect2', "click", function() {
      	 	 $('manualselect2').disabled=true
      	 	 $('report-list').hide();
      		 $('report-list').innerHTML = $('report-list-full').innerHTML;
      		 $('report-list-full').innerHTML='';
 			 AE.slideAndShow($('report-list'));  
 			 setUpEventObserving();		
      	 });
      	}
        
      });
      
      function enableCheckboxes(){
      	$('command').getElements().each(function(element){
        		if(element.type == 'checkbox' && element.name.indexOf('optionalReportDefinitionsMap')> -1){
        			element.disabled = false;
        		}
        });
        
      }
      
      function disableCheckboxes(){
      	 $('command').getElements().each(function(element){
        	if(element.type == 'checkbox' && element.name.indexOf('optionalReportDefinitionsMap')> -1){
        		element.disabled = element.checked;
        	}
         });
      }
      
      function setUpEventObserving(){
      	$('command').getElements().each(function(element){
        		if(element.type == 'checkbox' && element.name.indexOf('optionalReportDefinitionsMap')> -1){
        		  Event.observe(element.id, "click", function() {
      				captureSelectedCheckboxNames();
      			  });
        			
        		}
        });
      	
      }
	  function captureSelectedCheckboxNames(){
	    var nameInput = $('selectedReportDefinitionNames');
	    nameInput.value='';
	  	$('command').getElements().each(function(element){
        		if(element.type == 'checkbox' && element.checked && element.name.indexOf('optionalReportDefinitionsMap')> -1){
        			if(nameInput.value.length > 0) nameInput.value = nameInput.value + ',';
        			nameInput.value = nameInput.value + element.name;
        		}
        });
	  }      
      function enableManualSelection(){
      	enableCheckboxes();
      	$('manualselect').disabled = true;
      }
    </script>
</head>
<body>
<div id="report-list-full" style="display:none;">
<tags:noform>
<c:if test="${empty command.aeReport.id && not empty command.requiredReportDeifnitions}">
<c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
    <div class="row">
    <div class="label"><tags:renderInputs field="${field}"/></div>
    <div class="value"><tags:renderLabel field="${field}"/></div>
    </div>
</c:forEach>
</c:if>
</tags:noform>			
</div>
<tags:tabForm tab="${tab}" flow="${flow}">
	<jsp:attribute name="instructions">
	 <tags:instructions code="instruction_ae_checkpoint" />
	</jsp:attribute>
    <jsp:attribute name="singleFields">
  
        <c:choose>
            <c:when test="${empty command.aeReport.id && not empty command.requiredReportDeifnitions}">
              <p><strong>Reports Identified by caAERS</strong></p>
               <tags:instructions code="instruction_ae_checkpointReports" heading=" "/>
              	<div id="report-list" class="report-list">
            	  <!-- required reports -->
            	  <c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
            	   <c:if test="${fn:contains(command.requiredReportDefinitionNames, field.propertyName)}">
                   <div class="row">
                    <div class="label"><tags:renderInputs field="${field}"/></div>
                    <div class="value"><tags:renderLabel field="${field}"/></div>
                   </div>
                   </c:if>
            	  </c:forEach>
        		</div> 
        		<p>
        		If you agree with this assessment and wish to proceed, click Continue. 
        		Once you click this button, the report will be initiated and the countdown to the due date will begin.
        		</p>		
        		
        		<p>
        		At your discretion, you may elect to bypass the caAERS-based report selection above and 
        		instead manually select from the list of all reports defined for this study the expedited 
        		reports you wish to complete and submit. To do so, click the Manually Select Reports button below.
        		</p>
				<div class="autoclear" align="center" ><input type="button" id="manualselect2" value="Manually select reports" /></div>
        		  
            </c:when>
            <c:otherwise>
            	<c:if test="${empty command.aeReport.id}">
            	<p>The AEs you have entered <strong>do not</strong> seem to require any expedited reporting. 
            	If you wish to override this decision, please choose the notification and reporting schedule below.</p>
            	</c:if>
 				<c:if test="${not empty command.aeReport.id}">
 				<p>This adverse event report is associated to the following notification or reporting schedule </p>
 				</c:if>
 				
 				<div class="report-list">
            	<!-- optional reports -->
            	<c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
                 <div class="row">
                    <div class="label"><tags:renderInputs field="${field}"/></div>
                    <div class="value"><tags:renderLabel field="${field}"/></div>
                 </div>
				</c:forEach>
        	</div>   
        	<c:if test="${not empty command.aeReport.id}">
        	 <p>
        	  If you agree with this assessment and wish to proceed, click Save & Continue. 
        	  If needed, you can add additional notification and reporting schedule by selecting them in the above list. 
        	  In case if you need to de-select an already associated notification and reporting schedule,click the Modify Selected Reports button below.
        	 </p>
        	 <div class="autoclear" align="center" ><input type="button" id="manualselect" value="Modify selected reports" /></div>
        	</c:if>
            </c:otherwise>
        </c:choose>
        <input type="hidden" id="selectedReportDefinitionNames" name="selectedReportDefinitionNames" />
        
        <p>
            Every report you select has a minimum set of fields which the target agency needs
            you to provide.  Once you pass this page, all those fields will be tagged with
            <tags:requiredIndicator />.  The tabs along the top of the flow will be similarly
            tagged with <tags:requiredIndicator /> if they are mandatory and <ae:requiredToSubmit />, 
            when there are unfilled fields that are required for submission.
        </p>

    </jsp:attribute>
</tags:tabForm>
</body>
</html>