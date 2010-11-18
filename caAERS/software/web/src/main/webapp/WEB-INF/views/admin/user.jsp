<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
    <head>
    	<title>User</title>
    </head>
    
    <body>
    
    	<script type="text/javascript">
    	Event.observe(window, "load", function() {
    		theAccordion = new Accordion("roles-div", 1);
    	});
    	</script>
    
    	<div class="tabpane">
    		
    		<div class="workflow-tabs2">
    			<ul id="" class="tabs autoclear">
                        <li id="thirdlevelnav" class="selected">
                            <div>
                            	<a href="createUser"><caaers:message code="user.menu.createUser"/></a>
                            </div>
                        </li>
                    	<li id="thirdlevelnav" class="">
                    		<div>
                    			<a href="searchUser"><caaers:message code="user.menu.searchUser"/></a>
                    		</div>
                    	</li>
                </ul>
    		</div>
    		
    		<tags:tabForm tab="${tab}" flow="${flow}" formName="userForm" hideErrorDetails="false" hideBox="true">
    			<jsp:attribute name="repeatingFields">
    				<input type="hidden" name="_finish" value="true"/>
					<chrome:box title="Basic Details">
						<tags:instructions code="userdetailsForUA" />
						<chrome:division title="${detailsSectionTitle}" id="details">
							<div style="height:100px;">
								 <div class="leftpanel">
									<div class="row">
	                            		<div class="label"><ui:label path="csmUser.firstName" text="" labelProperty="firstName" required="true"/></div>
	                            		<div class="value"><ui:text path="csmUser.firstName" required="true" title="First name"/></div>
	                        		</div>
	                        		
			                        <div class="row">
			                            <div class="label"><ui:label path="csmUser.lastName" text="" labelProperty="lastName" required="true"/></div>
			                            <div class="value"><ui:text path="csmUser.lastName" required="true" title="Last name"/></div>
			                        </div>
	                        		
								 </div>
								 <div class="rightpanel">
									<div class="row">
										<div class="label"><ui:label path="csmUser.emailAddress" text="" labelProperty="emailAddress" required="true"/></div>
										<div class="value"><ui:text path="csmUser.emailAddress" required="true" title="Primary email"/></div>
									</div>
		                        <div class="row">
		                            <div class="label"><ui:label path="csmUser.loginId" text="" labelProperty="loginId" required="true"/></div>
		                            <div class="value"><ui:text path="csmUser.loginId" required="true" title="Login ID"/></div>
		                        </div>									
								 </div>
							</div>
						</chrome:division>
    				</chrome:box>
    				
    				<chrome:box title="User Roles">
    					<tags:instructions code="userRolesForUA" />
    					<chrome:division title="${detailsSectionTitle}" id="details">
    						<div id="roles-div">
							<c:forEach var="roleMembership" items="${command.roleMembershipHelper}" varStatus="index">
								
								<chrome:accordion id="section-${roleMembership.suiteRole.csmName}" title="${roleMembership.suiteRole.displayName} ${roleMembership.checked ? 'X' : '' }">
									${roleMembership.suiteRole.description}<br>
									<ui:checkbox path="roleMembershipHelper[${index.index}].checked"/>&nbsp;&nbsp;Grant this user the ${roleMembership.suiteRole.displayName} role. 
								</chrome:accordion>
							</c:forEach>
    						</div>
    					</chrome:division>
    				</chrome:box>
    			</jsp:attribute>
    		</tags:tabForm>
    		
    	</div>
	</body>
</html>