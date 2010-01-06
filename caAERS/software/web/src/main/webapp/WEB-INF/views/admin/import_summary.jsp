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
				<admin:studyImport/>
			</c:if>
			<c:if test="${command.type eq 'subject'}">
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
			
			</jsp:attribute>
			<jsp:attribute name="tabControls">
		    	<div class="content buttons autoclear">
          			<div class="flow-buttons">
				  		<span class="next">
				  			<c:if test="${command.type eq 'study'}">
								<tags:button type="submit" icon="Study search" color="green" id="flow-next" value="Study search"/>
							</c:if>
							<c:if test="${command.type eq 'subject'}">
								<tags:button type="submit" icon="Subject search" color="green" id="flow-next" value="Subject search"/>
							</c:if>
							<c:if test="${command.type eq 'researchStaff'}">
								<tags:button type="submit" icon="Search research staff" color="green" id="flow-next" value="Search research staff"/>
							</c:if>
							<c:if test="${command.type eq 'investigator'}">
								<tags:button type="submit" icon="Search investigator" color="green" id="flow-next" value="Search investigator"/>
							</c:if>
							<c:if test="${command.type eq 'organization'}">
								<tags:button type="submit" icon="Search organization" color="green" id="flow-next" value="Search organization"/>
							</c:if>
				  		</span>
          			</div>
      			</div>
  			</jsp:attribute>
		</tags:tabForm>
	</div>
	</body>
</html>
