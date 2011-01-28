<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<tags:dwrJavascriptLink objects="createStudy"/>
<title>Person/User Confirmation</title>
</head>
	<body>
	
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
    			
		<div class="summary">
			<chrome:flashMessage/>
			<chrome:box title="Basic Details">
				<div style="height:100px;">
					 <div class="leftpanel">
						<div class="row">
                      		<div class="label"><caaers:message code="LBL_firstName" />/div>
                      		<div class="value">${command.firstName}</div>
						</div>
						<c:if test="${command.createAsPerson}">
						<div class="row">
                            <div class="label"><caaers:message code="LBL_middleName" /></div>
                            <div class="value">${command.middleName}</div>
                        </div>
						</c:if>	                      		
                        <div class="row">
                            <div class="label"><caaers:message code="LBL_lastName" /></div>
                            <div class="value">${command.lastName}</div>
                        </div>
					 </div>
					 <div class="rightpanel">
						<div class="row">
							<div class="label"><caaers:message code="LBL_emailAddress" /></div>
							<div class="value">${command.emailAddress}</div>
						</div>
						<c:if test="${command.createAsUser}">
							<div class="row">
                           		<div class="label"><caaers:message code="LBL_loginId" /></div>
                           		<div class="value">${command.userName}</div>
                       		</div>									
						</c:if>
					 </div>
				</div>
			</chrome:box>
			
			<c:if test="${command.createAsPerson}">
                <caaers:message code="LBL_researchStaff.review.associated.organziation" var="assOrgs" text="Associated Organizations" />
				<chrome:box title="${assOrgs}">
					<table class="tablecontent" width="100%" >
						<tr>
	                		<th scope="col"><tags:message key="LBL_researchStaff.review.organziation"/></th>
	                		<th scope="col"><tags:message key="LBL_researchStaff.review.organziation.id"/></th>
	            		</tr>
	            		<c:forEach items="${command.sitePersonnel}" varStatus="status" var="srs">
	            			<tr>
								<td>${srs.organization.name}</td>
								<td>${srs.organization.nciInstituteCode}</td>										                         
	            			</tr>
	            		</c:forEach>
	            	</table>
				</chrome:box>
			</c:if>
			
			<c:if test="${command.createAsUser}">
				<chrome:box title="RoleMemberships">
					<table class="tablecontent" width="100%" >
						 <tr>
	                		<th scope="col">Role</th>
	                		<th scope="col">Sites</th>
	                		<th scope="col">Studies</th>
	                		<th scope="col">Scope</th>
	            		</tr>
	            		<c:forEach var="roleMembershipHelper" items="${command.roleMembershipHelper}" varStatus="index">
	            			<c:if test="${roleMembershipHelper.checked}">
	            					<c:if test="${!roleMembershipHelper.scoped}">
										<c:set var="_scope" value="Global"/>
									</c:if>
									<c:if test="${roleMembershipHelper.scoped}">
										<c:set var="_scope" value="Site"/>
										<c:if test="${roleMembershipHelper.studyScoped}">
											<c:set var="_scope" value="Site & Study"/>
										</c:if>
									</c:if>
									
		            			<tr class="results">
		            				<td>${roleMembershipHelper.suiteRole.displayName}</td>
		            				<td>
										<c:if test="${roleMembershipHelper.allSiteAccess}">
											All Sites
										</c:if>
		            					<c:forEach var="site" items="${roleMembershipHelper.sites}" varStatus="siteIndex">
		            						${site}<c:if test="${! siteIndex.last}">,</c:if>
		            					</c:forEach>
		            				</td>
									<td>
										<c:if test="${roleMembershipHelper.allStudyAccess}">
											All Studies
										</c:if>
		            					<c:forEach var="study" items="${roleMembershipHelper.studies}" varStatus="studyIndex">
		            						${study}<c:if test="${! studyIndex.last}">,</c:if>
		            					</c:forEach>
		            				</td>
		            				<td>
		            					${_scope}
		            				</td>
		            			</tr>
	            			</c:if>
	            		</c:forEach>
					</table>
				</chrome:box>			
			</c:if>
		</div>
	</body>
</html>