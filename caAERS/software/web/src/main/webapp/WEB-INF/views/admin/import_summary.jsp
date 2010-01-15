<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
	<head>
		<style type="text/css">
			div.row div.label { width: 28em; } 
			div.row div.value, div.row div.extra { margin-left: 30em; }
		</style>
		<tags:dwrJavascriptLink objects="importRoutineAe"/>
		<script language="JavaScript" type="text/JavaScript">
		</script>
	</head>
	<body>
		<tags:tabForm tab="${tab}" flow="${flow}" title="Import summary">
			<jsp:attribute name="singleFields">
			<div>
			<input type="hidden" name="_finish" value="">
			</div>
			
			<c:if test="${command.type eq 'study'}">
				<admin:studyImportSummary/>
			</c:if>
			<c:if test="${command.type eq 'subject'}">
				<admin:subjectImportSummary/>
			</c:if>
			<c:if test="${command.type eq 'researchStaff'}">
				<admin:researchStaffImport/>
			</c:if>
			<c:if test="${command.type eq 'investigator'}">
				<admin:investigatorImportSummary/>
			</c:if>
			<c:if test="${command.type eq 'organization'}">
				<admin:organizationImport/>
			</c:if>
			<c:if test="${command.type eq 'agent'}">
				<admin:agentImport/>
			</c:if>
			<c:if test="${command.type eq 'medDRA'}">
				<admin:meddraImportSummary />
			</c:if>
			
			</jsp:attribute>
			<jsp:attribute name="tabControls">
		    	<div class="content buttons autoclear">
          			<div class="flow-buttons">
				  		<span class="next">

								<tags:button type="submit" icon="Search organization" color="green" id="flow-next" value="Done"/>

				  		</span>
          			</div>
      			</div>
  			</jsp:attribute>
		</tags:tabForm>
	</div>
	</body>
</html>
