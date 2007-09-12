<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:stylesheetLink name="participant" />
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
<style type="text/css">
        div.content {
            padding: 5px 15px;
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
<br />

<chrome:box title="${researchStaff.lastName}, ${researchStaff.firstName}" >

    <chrome:division title="Organization">
		<div class="leftpane">
	        <div class="row">
	            <div class="label">Organization:</div>
	            <div class="value">${researchStaff.organization.name}</div>
	        </div>
	     	
</chrome:division>    
    
     <chrome:division title="Research Staff Details">
    
        <br>
        
		<table id="test2" class="single-fields" >
        	<tr>
    	<td>
         <div class="leftpane">
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
	       </td> <td>
	         <div class="row">
	            <div class="label">NCI Identifier:</div>
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
	              
	     </td>
	    </tr>
	    </table> 
	      </chrome:division>
	
	        
</chrome:box>

</body>