<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="style"%>
<%@attribute name="index" required="true" description="The index of the AE for which the outcome needs to be printed"%>
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
			<ui:text path="${fieldGroups[outcomeGroup].fields[len - 1].propertyName}"/>
		</div>
	</div>
	</jsp:attribute>
</ui:row>


<%--
<c:set var="title">Outcome</c:set>
<c:if test="${command.assignment.studySite.study.adeersReporting == false}">
<chrome:division title="${title}" id="outcome">
  
  <script type="text/javascript">
   
      Event.observe(window, "load", function() {
      	disableCheckbox()
      	manageOtherOutcome()
      	manageDeathDate()
    	 $('outcomes[3]1').observe("click" , function(e) { e.stop();})
      	Event.observe("outcomes[6]1", "change", function() { manageOtherOutcome() })
      	Event.observe("outcomes[1]1", "change", function() { manageDeathDate() })
      	
      	$('aeReport.adverseEvents[0].hospitalization').observe("change" ,function(){checkOrUnCheckHospitalization();})
      	checkOrUnCheckHospitalization();
	  })   
	  
	  function checkOrUnCheckHospitalization(){
    	  $('outcomes[3]1').checked = $('aeReport.adverseEvents[0].hospitalization').value == 'YES';
	  }
	  
	  function disableCheckbox(){
      		$('outcomes[3]1').disabled = true;
      }
      
      function manageDeathDate(){
      	if(!$('outcomes[1]1').checked){
	  			$('outcomeDate-calbutton').style.display="none"
	  			$('outcomeDate').value=""
	  			$('outcomeDate').setAttribute('readOnly',true);
	  	}else{
	  			$('outcomeDate-calbutton').style.display=""
	  			$('outcomeDate').removeAttribute('readOnly');
	  	}
      }
      
      function manageOtherOutcome(){
      	if(!$('outcomes[6]1').checked){
	  			$('otherOutcome').value=""
	  			$('otherOutcome').setAttribute('readOnly',true);
	  	}else{
	  		$('otherOutcome').removeAttribute('readOnly');
	  	}
      }
     
    </script>
    	
		 <div class="row">
			<div class="label"><tags:renderInputs field="${fieldGroups['outcomes'].fields[0]}"/></div>
			<div class="value">
				<tags:renderLabel field="${fieldGroups['outcomes'].fields[0]}"/>
				&nbsp;&nbsp;&nbsp;<tags:renderInputs field="${fieldGroups['outcomes'].fields[1]}"/>
			</div>
		 </div>
		
		
		<div id="outcome-list" class="outcome-list">
		  <!-- required reports -->
		  <c:forEach items="${fieldGroups['outcomes'].fields}" begin="2" end="6" var="field">
		   <div class="row">
			<div class="label"><tags:renderInputs field="${field}"/></div>
			<div class="value"><tags:renderLabel field="${field}"/></div>
		   </div>
		  </c:forEach>
		  <div class="row">
			<div class="label"><tags:renderLabel field="${fieldGroups['outcomes'].fields[7]}"/></div>
			<div class="value"><tags:renderInputs field="${fieldGroups['outcomes'].fields[7]}"/></div>
		 </div>
		 <div class="row">
			<div class="label"><tags:renderInputs field="${fieldGroups['outcomes'].fields[8]}"/></div>
			<div class="value"><tags:renderLabel field="${fieldGroups['outcomes'].fields[8]}"/></div>
		 </div>
		</div>

</chrome:division>
		</c:if>
--%>