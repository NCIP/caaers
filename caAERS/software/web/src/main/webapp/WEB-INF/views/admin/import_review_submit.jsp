<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<style type="text/css">
        
		div.row div.label { width: 28em; } 
		div.row div.value, div.row div.extra { margin-left: 30em; }
		
    	.graph { 
        position: relative; /* IE is dumb */
        width: 200px; 
        border: 1px solid #3876C1; 
        padding: 2px; 
    	}
    	.graph .bar { 
        display: block;
        position: relative;
        background: #3876C1; 
        text-align: center; 
        color: #333; 
        height: 1em; 
        line-height: 1em;            
    	}
    	.graph .bar span { position: absolute; left: 1em; }
</style>

<tags:dwrJavascriptLink objects="importRoutineAe"/>
<script language="JavaScript" type="text/JavaScript">
	
	function validatePage(){
		return true;
	}
	function fireAction(action, selected){
		if(validatePage()){
							
			document.getElementsByName('_finish')[0].name='xyz';				            
			document.studySiteForm._action.value=action;
			document.studySiteForm._selected.value=selected;		
			document.studySiteForm.submit();
		}
	}
	function submitForm(action){
		var form = document.getElementById('command');
		form._action.value = action;
		form.submit();
	}
	
</script>

</head>
<body>

    <tags:tabForm tab="${tab}" flow="${flow}" title="Review & Submit" formName="ImportReviewForm" enctype="multipart/form-data">
    <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
	</div>

	<c:if test="${command.type eq 'study'}">
		<admin:studyImport/>
	</c:if>
	<c:if test="${command.type eq 'participant'}">
		<admin:subjectImport/>
	</c:if>
	<c:if test="${command.type eq 'researchStaff'}">
		<admin:researchStaffImport/>
	</c:if>
	<c:if test="${command.type eq 'investigator'}">
		<admin:investigatorImport/>
	</c:if>
	<c:if test="${command.type eq 'organization'}">
		<admin:organizationImport/>
	</c:if>
	<c:if test="${command.type eq 'agent'}">
		<admin:agentImport/>
	</c:if>
	<c:if test="${command.type eq 'medDRA'}">
		<admin:meddraImportReview/>
	</c:if>
		
	<c:if test='${command.schemaValidationResult != null  }'>
		<div class="red-means-stop">
			The provided xml is invalid, Fix the errors and try again.
			<p><c:out value="${command.schemaValidationResult}" /></p>
		</div>
		<c:set var="importDisabled" value="disabled" scope="request"/>
   	</c:if>
		
</jsp:attribute>
<jsp:attribute name="tabControls">
      <div class="content buttons autoclear">
          <div class="flow-buttons">
              <span class="prev">
              	<tags:button type="submit" color="blue" icon="Back" id="flow-prev" cssClass="tab0" value="Back" onclick="javascript:submitForm('back');"/>
			  </span>
				  <span class="next">
					<tags:button type="submit"  disabled="${importDisabled}" icon="check" color="green" id="flow-next" value="Import" onclick="javascript:submitForm('continue');"/>
				  </span>
          </div>
      </div>
  </jsp:attribute>
</tags:tabForm>
</body>
</html>