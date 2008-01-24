<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
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
  <ul id="workflow-tabs" class="tabs autoclear">
	<li class="tab selected">
	<div><a href="createResearchStaff">Create Research Staff</a></div>
	</li>
	<li class="tab">
	<div><a href="searchResearchStaff">Search Research Staff</a></div>
	</li>
 </ul>
</div>
<div style="height:10px"></div>
<chrome:flashMessage key="statusMessage"></chrome:flashMessage>
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
</chrome:box>

</body>