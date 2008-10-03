<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Create Organization</title>
	<tags:stylesheetLink name="tabbedflow"/>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>
 	<tags:includeScriptaculous/>
 	</head>
<body>

<div class="tabpane">

  <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="createOrganization">Create Organization</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="searchOrganization">Search Organization</a>
    </div></li>
  </ul>
    </div>      
  <br />
 
<p>
<tags:instructions code="organizationdetails" />
</p>
<tags:tabForm tab="${tab}" flow="${flow}"  formName="organizationForm">
		 <jsp:attribute name="singleFields">
            <div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		       <c:if test="${(empty command.id) or ( command.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
		
		
    				<c:forEach  items="${fieldGroups.organization.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                	</c:forEach>
             </jsp:attribute>
    
    
</tags:tabForm>
 </body>
</html>
