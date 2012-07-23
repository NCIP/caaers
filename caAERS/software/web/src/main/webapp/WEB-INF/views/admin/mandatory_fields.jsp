<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Mandatory Fields</title>
    <style type="text/css">
	   	div.row div.label { width: 13em; } 
   		div.row div.value { margin-left: 14em; }
   		.half {
   			float:left;
			width:46%;
			margin:0 10px;
   		}

	</style>
     <script type="text/javascript">
      AE.PAGE_HELP_LINK = 'mandatoryFields';
  </script>
</head>
<body>

	    <div class="workflow-tabs2">
    	    <ul id="" class="tabs autoclear">
        	    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:READ || gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
        	    	<li id="thirdlevelnav" class="tab"><div><a href="configure"><caaers:message code="configure.menu.general"/></a></div></li>
        	    </csmauthz:accesscontrol>
            	<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy:READ || gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy:UPDATE">
            		<li id="thirdlevelnav" class="tab"><div><a href="passwordPolicyConfigure"><caaers:message code="configure.menu.passwordPolicy"/></a></div></li>
            	</csmauthz:accesscontrol>
                <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition:READ || gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition:UPDATE">
                	<li id="thirdlevelnav" class="tab selected"><div><a href="mandatoryFields"><caaers:message code="configure.menu.mandatoryFields"/></a></div></li>
                </csmauthz:accesscontrol>
        	</ul>
    	</div>
    	<div class="content">
			<form:form>
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
			    <caaers:message code="configure.menu.aefields.title" var="detailsSectionTitle"/>
                <c:if test="${updated}">
                    <div class="success-box message"><p>Settings saved</p></div>
                </c:if>
        		<chrome:box title="${detailsSectionTitle}">
             			<tags:instructions code="admin.mandatory.fields.instruction" />
						<tags:hasErrorsMessage hideErrorDetails="true"/>
            			<tags:jsErrorsMessage/>
						<div id="captureAdverseEventsFields-id">
			 				<admin:renderCaaersMandatoryFields key="CAPTURE_AE_TAB_SECTION~Adverse events"/>
						</div>
    			</chrome:box>
                
                <caaers:message code="configure.menu.coursefields.title" var="detailsSectionTitle"/>
                <chrome:box title="${detailsSectionTitle}">
             			<tags:instructions code="admin.mandatory.fields.instruction" />
						<tags:hasErrorsMessage hideErrorDetails="true"/>
            			<tags:jsErrorsMessage/>
						<div id="captureAdverseEventsFields-id">
			 				<admin:renderCaaersMandatoryFields key="COURSE_CYCLE_SECTION" />
						</div>
    			</chrome:box>


				<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition:UPDATE">
        			<div class="content buttons autoclear">
          				<div class="flow-buttons">
           					<span class="next">
               					<tags:button type="submit" value="Save" color="green" icon="save" />
           					</span>
          				</div>
        			</div>
        		</csmauthz:accesscontrol>
			</form:form>
		</div>

</body>
</html>
