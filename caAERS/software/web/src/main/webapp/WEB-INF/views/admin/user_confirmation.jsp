<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<tags:dwrJavascriptLink objects="createStudy"/>
<title>User Confirmation</title>
</head>
	<body>
		<div class="summary">
			<chrome:division title="User Details">
				<div style="height:100px;">
					 <div class="leftpanel">
						<div class="row">
                          		<div class="label">First name</div>
                          		<div class="value">${command.csmUser.firstName}</div>
                      		</div>
                      		
                        <div class="row">
                            <div class="label">Last name</div>
                            <div class="value">${command.csmUser.lastName}</div>
                        </div>
                      		
					 </div>
					 <div class="rightpanel">
						<div class="row">
							<div class="label">Email address</div>
							<div class="value">${command.csmUser.emailAddress}</div>
						</div>
                       <div class="row">
                           <div class="label">Username</div>
                           <div class="value">${command.csmUser.loginId}</div>
                       </div>									
					 </div>
				</div>
			</chrome:division>
			<chrome:division title="RoleMemberships">
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
			</chrome:division>
		</div>
	</body>
</html>