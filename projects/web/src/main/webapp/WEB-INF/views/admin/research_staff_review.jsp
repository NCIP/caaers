<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Research Staff</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
<style type="text/css">
  div.content {
   height:160px;
  }
</style>

<script>
function submitPage(s){
	document.getElementById("nextView").value=s;
	document.getElementById("command").submit();
}

</script>
</head>
<body>

<div class="tabpane">
    <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
	<li id="thirdlevelnav" class="tab selected">
	<div><a href="createResearchStaff">Create Research Staff</a></div>
	</li>
	<li id="thirdlevelnav" class="tab">
	<div><a href="searchResearchStaff">Search Research Staff</a></div>
	</li>
 </ul>
        </div>
</div>
<div style="height:10px"></div>
<chrome:box title="${researchStaff.lastName}, ${researchStaff.firstName}" >
     <chrome:division title="Research Staff Details">
    	<div class="leftpanel">
    	 <div class="row">
	     	<div class="label">Organization:</div>
	     	<div class="value">${researchStaff.organization.fullName}</div>
	   	  </div>
    	  <div class="row">
	            <div class="label">First Name:</div>
	            <div class="value">${researchStaff.firstName}</div>
	       </div>
	       <div class="row">
	            <div class="label">Last Name:</div>
	            <div class="value">${researchStaff.lastName}</div>
	       </div>
	       <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value">${researchStaff.middleName}</div>
	       </div>
    	</div>
    	<div class="rightpanel">
    	    <div class="row">
	            <div class="label">Researcher ID:</div>
	            <div class="value">${researchStaff.nciIdentifier} </div>
	        </div>
	        <div class="row">
	            <div class="label">Email address:</div>
	            <div class="value">${researchStaff.emailAddress}</div>
	        </div>
	        <div class="row">
	            <div class="label">Phone:</div>
	            <div class="value">${researchStaff.phoneNumber}</div>
			</div>
	        <div class="row">
	            <div class="label">Fax:</div>
	            <div class="value">${researchStaff.faxNumber}</div>
	        </div>
    	</div>
      
	</chrome:division>
	<chrome:division title="User Roles">
        <div style="padding-left:50px;">
        <c:forEach var="role" items="${researchStaff.userGroupTypes}">
			<li>
				${role.csmName eq 'caaers_participant_cd' ? 'Subject coordinator' : ''}
				${role.csmName eq 'caaers_study_cd' ? 'Study coordinator' : ''}
				${role.csmName eq 'caaers_ae_cd' ? 'Adverse event coordinator' : ''}
				${role.csmName eq 'caaers_site_cd' ? 'Site coordinator' : ''}
				${role.csmName eq 'caaers_central_office_sae_cd' ? 'Central Office SAE coordinator' : ''}
				${role.csmName eq 'caaers_data_cd' ? 'Data coordinator' : ''}
			</li>
		</c:forEach>
        </div>
    </chrome:division>
	
</chrome:box>

</body>