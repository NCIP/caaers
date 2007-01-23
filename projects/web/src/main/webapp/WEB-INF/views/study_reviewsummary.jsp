<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
        .label { width: 12em; padding: 1px; }
</style>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">
	<form:form method="post">
		<tags:tabFields tab="${tab}"/>
			
		<h3> <strong> Study Details </strong> </h3>		
		<div class="row">
			<div class="label" align="right"><form:label path="shortTitle">Short Title:</form:label></div>
		    <div class="value" align="left"><form:label path="shortTitle">${command.shortTitle}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label path="longTitle">Long Title:</form:label></div>
		    <div class="value" align="left"><form:label path="longTitle">${command.longTitle}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label path="precis">Precis Text:</form:label></div>
		    <div class="value" align="left"><form:label path="precis">${command.precis}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label path="description">Description Text:</form:label></div>
		    <div class="value" align="left"><form:label path="description">${command.description}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label path="status">Status:</form:label></div>
			<div class="value" align="left"><form:label path="status">${command.status}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label path="diseaseCode">Disease Code:</form:label></div>
			<div class="value" align="left"><form:label path="diseaseCode">${command.diseaseCode}</form:label></div>
		</div>
		
		<div class="row">
			<div class="label" align="right"><form:label path="monitorCode">Monitor Code:</form:label></div>
			<div class="value" align="left"><form:label path="monitorCode">${command.monitorCode}</form:label></div>
		</div>
	
		<div class="row">
			<div class="label" align="right"><form:label path="phaseCode">Phase Code:</form:label></div>
			<div class="value" align="left"><form:label path="phaseCode">${command.phaseCode}</form:label></div>
		</div>
	
		<div class="row">
			<div class="label" align="right"><form:label
					path="multiInstitutionIndicator">Multi Institution:</form:label></div>
			<div class="value" align="left"><form:checkbox
					path="multiInstitutionIndicator" /></div>
		</div>
				
		<br> <br>
				<input type="image" name="_target0" src="images/b-edit.gif"
					border="0" alt="edit this page">
			
		<h3><strong>Study Identifiers</strong></h3>

		<div class="row">
			<div class="label" align="right"><form:label
					path="identifiers[0].source">Source:</form:label></div>
			<div class="value" align="left"><form:label
					path="identifiers[0].source">${command.identifiers[0].source}</form:label></div>
		</div>
				
		<div class="row">
			<div class="label" align="right"><form:label
					path="identifiers[0].type">Type:</form:label></div>
			<div class="value" align="left"><form:label
					path="identifiers[0].type">${command.identifiers[0].type}</form:label></div>
		</div>
		
		<div class="row">
			<div class="label" align="right"><form:label
					path="identifiers[0].value">value:</form:label></div>
			<div class="value" align="left"><form:label
					path="identifiers[0].value">${command.identifiers[0].value}</form:label></div>
		</div>

		<br> <br>
		<input type="image" name="_target1" src="images/b-edit.gif"
					border="0" alt="edit this page">
			
		<h3><strong>Study Site</strong></h3>

		<div class="row">
			<div class="label" align="right"><form:label
					path="studySites[0].statusCode">Status:</form:label></div>
			<div class="value" align="left"><form:label
					path="studySites[0].statusCode">${command.studySites[0].statusCode}</form:label></div>
		</div>
		
		<div class="row">
			<div class="label" align="right"><form:label
					path="studySites[0].roleCode">Role:</form:label></div>
			<div class="value" align="left"><form:label
					path="studySites[0].roleCode">${command.studySites[0].roleCode}</form:label></div>
		</div>
		
		<div class="row">
			<div class="label" align="right"><form:label
					path="studySites[0].startDate">Start Date:</form:label></div>
			<div class="value" align="left"><form:label
					path="studySites[0].startDate">${command.studySites[0].startDate}</form:label></div>
		</div>

		<div class="row">
			<div class="label" align="right"><form:label
					path="studySites[0].startDate">Start Date:</form:label></div>
			<div class="value" align="left"><form:label
					path="studySites[0].startDate">${command.studySites[0].startDate}</form:label></div>
		</div>
		
		<div class="row">
			<div class="label" align="right"><form:label
					path="studySites[0].irbApprovalDate">IRB Approval Date:</form:label></div>
			<div class="value" align="left"><form:label
					path="studySites[0].irbApprovalDate">${command.studySites[0].irbApprovalDate}</form:label></div>
		</div>
		
		<br><br>
		
		<input type="image" name="_target2" src="images/b-edit.gif"
			border="0" alt="edit this page">
				
				<input type="image" name="_finish"
						src="/caaers/images/b-save.gif" border="0"
						alt="Save"> 
				<input type="image"
						name="_target0" src="/caaers/images/b-cancel.gif" border="0"
						alt="Cancel">						

	</form:form>
</chrome:body>
</body>
</html>
