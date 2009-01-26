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
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
	<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="aeReport"/>
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>  
    <script type="text/javascript">
   
      Event.observe(window, "load", function() {
      	disableCheckbox()
      	manageOtherOutcome()
      	Event.observe("outcomes[6]1", "change", function() { manageOtherOutcome() })
	  })   
	  
	  function disableCheckbox(){
	  		if(!$('outcomes[1]1').checked){
	  			$('outcomeDate-calbutton').style.display="none"
	  			$('outcomeDate').value=""
	  			$('outcomeDate').setAttribute('readOnly',true);
	  		}
	  		
      		$('outcomes[1]1').disabled="true";
      		$('outcomes[2]1').disabled="true";
      		$('outcomes[3]1').disabled="true";
      }
      
      function manageOtherOutcome(){
      	console.debug("gg");
      	if(!$('outcomes[6]1').checked){
	  			$('otherOutcome').value=""
	  			$('otherOutcome').setAttribute('readOnly',true);
	  	}else{
	  		$('otherOutcome').removeAttribute('readOnly');
	  	}
      }
     
     
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section4selectreport">
	<jsp:attribute name="instructions">
	 <tags:instructions code="instruction_ae_outcome" />
	</jsp:attribute>
    <jsp:attribute name="singleFields">
    
     <div class="row">
		<div class="label"><tags:renderInputs field="${fieldGroups['optionalReports'].fields[0]}"/></div>
		<div class="value">
			<tags:renderLabel field="${fieldGroups['optionalReports'].fields[0]}"/>
			&nbsp;&nbsp;&nbsp;<tags:renderInputs field="${fieldGroups['optionalReports'].fields[1]}"/>
		</div>
	 </div>
    
    
    <div id="outcome-list" class="outcome-list">
	  <!-- required reports -->
	  <c:forEach items="${fieldGroups['optionalReports'].fields}" begin="2" end="6" var="field">
	   <div class="row">
		<div class="label"><tags:renderInputs field="${field}"/></div>
		<div class="value"><tags:renderLabel field="${field}"/></div>
	   </div>
	  </c:forEach>
	  <div class="row">
		<div class="label"><tags:renderLabel field="${fieldGroups['optionalReports'].fields[7]}"/></div>
		<div class="value"><tags:renderInputs field="${fieldGroups['optionalReports'].fields[7]}"/></div>
	 </div>
	 <div class="row">
		<div class="label"><tags:renderInputs field="${fieldGroups['optionalReports'].fields[8]}"/></div>
		<div class="value"><tags:renderLabel field="${fieldGroups['optionalReports'].fields[8]}"/></div>
	 </div>
	</div> 
    </jsp:attribute>
</tags:tabForm>
</body>
</html>