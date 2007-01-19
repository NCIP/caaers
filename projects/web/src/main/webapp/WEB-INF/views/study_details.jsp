<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<script language="JavaScript" type="text/JavaScript">
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
</script>
<script language="JavaScript" type="text/JavaScript">

function validatePage(){
	if(document.getElementById("longTitleText") != null)
		return true;
	else
		return false;	
}

</script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<div class="workArea">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<!-- CURRENT DRIVER/UNIT TITLE STARTS HERE -->

		<td id="current">Site Name-Id: ${sites[0].site.name}</td>
		<!-- CURRENT DRIVER/UNIT TITLE ENDS HERE -->
	</tr>
	<tr>
		<td class="display"><!-- TABS LEFT START HERE -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<!-- LEFT CONTENT STARTS HERE -->

				<td valign="top" class="additionals2"><!-- RIGHT CONTENT STARTS HERE -->
				<form:form name="searchDetailsForm" method="post">
					 <tags:tabFields tab="${tab}"/>
					<div><input type="hidden" name="_page" value="0"></div>

					<br>
					<strong>Step 1. Study Details </strong> (<span class="red">*</span>
					<em>Required Information </em>)<br>
					<br>

					<table width="900" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td width="50%" valign="top">

							<table width="400" border="0" cellspacing="0" cellpadding="0"
								id="table1">
								<div class="row">
						            <div class="label"><form:label path="shortTitleText">Short Title:</form:label></div>
						            <div class="value" align="right"><form:textarea path="shortTitleText" rows="2" cols="30"/></div>
						        </div>
						        <div class="row">
						            <div class="label"><form:label path="longTitleText"><span class="red">*</span><em></em>Long
									Title:</form:label></div>
						            <div class="value" align="right"><form:textarea path="longTitleText" rows="5" cols="30"/></div>
						        </div>
						        <div class="row">
						            <div class="label"><form:label path="precisText">Precis Text:</form:label></div>
						            <div class="value" align="right"><form:textarea path="precisText" rows="2" cols="30"/></div>
						        </div>
						        <div class="row">
						            <div class="label"><form:label path="descriptionText">Description Text:</form:label></div>
						            <div class="value" algin="right"><form:textarea path="descriptionText" rows="5" cols="30"/></div>
						        </div>																								
							</table>
							</td>

							<td width="20%" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

							<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
							<table width="400" border="0" cellspacing="0" cellpadding="0"
								id="table1">

								<div class="row">
						            <div class="label"><form:label path="targetAccrualNumber">Target Accrual Number:</form:label></div>
						            <div class="value" algin="right"><form:input path="targetAccrualNumber"/></div>
						        </div>
						        <div class="row">
						        
           				 		<!--  <div class="label"><label for="status">Status:</label></div>
						            <div class="value" align="right">
					                <form:select path="status">
										<form:options items="${statusRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div> --> 
						        
							  <tr>
									<td class="label"><span class="red">*</span><em></em>Status:</td>
									<td><form:select path="status">
										<form:options items="${statusRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td> 
							 </tr> 
								
							<!-- 	<div class="label"><label for="diseaseCode">Disease
									Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="diseaseCode">
										<form:options items="${diseaseCodeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div> -->
								
							 <tr>
									<td class="label"><span class="red">*</span><em></em><strong>Disease
									Code:</strong>
									<td><form:select path="diseaseCode">
										<form:options items="${diseaseCodeRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td>
							</tr> 
								
							<!-- <div class="label"><label for="monitorCode">Monitor Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="monitorCode">
										<form:options items="${monitorCodeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div> -->
        						
							 <tr>
									<td class="label"><em></em>Monitor Code:</td>
									<td><form:select path="monitorCode">
										<form:options items="${monitorCodeRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td>
							</tr> 

							<!-- 	<div class="label"><label for="phaseCode">Phase
									Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="phaseCode">
										<form:options items="${phaseCodeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div> -->
        						
								<tr>
									<td class="label"><span class="red">*</span><em></em>Phase
									Code:</td>
									<td><form:select path="phaseCode">
										<form:options items="${phaseCodeRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td>
								</tr> 
<!-- 
								<div class="label"><label for="sponsorCode">Sponsor
									Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="sponsorCode">
										<form:options items="${sponsorCodeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div>
     -->   						
								 <tr>
									<td class="label"><span class="red">*</span><em></em>Sponsor
									Code:</td>
									<td><form:select path="sponsorCode">
										<form:options items="${sponsorCodeRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td>
								</tr> 
								
								<div class="row">
						            <div class="label"><form:label path="randomizedIndicator">Randomized
									Indicator</form:label></div>
						            <div class="value"><form:checkbox path="randomizedIndicator" /></div>
						        </div> 
						        
						        <!-- 
								<tr>
									<td class="label"><span class="red">*</span><em></em>Randomized
									Indicator</td>
									<td><form:checkbox path="randomizedIndicator" /></td>
								</tr> --> 
								
								 <div class="row">
						            <div class="label"><form:label path="multiInstitutionIndicator">Multi
									Institution:</form:label></div>
						            <div class="value"><form:checkbox path="multiInstitutionIndicator" /></div>
						        </div> 
						        
						        <!-- 
						        <tr>
									<td class="label"><span class="red">*</span><em></em>Multi
									Institution:</td>
									<td><form:checkbox path="multiInstitutionIndicator" /></td>
								</tr> --> 
								
								<div class="row">
						            <div class="label"><form:label path="blindedIndicator">Blinded Indicator:</form:label></div>
						            <div class="value"><form:checkbox path="blindedIndicator" /></div>
						        </div>
						        
						        <!-- 
								<tr>
									<td class="label">Blinded Indicator:</td>
									<td><form:checkbox path="blindedIndicator" /></td>
								</tr> --> 

<!-- 
								<div class="label"><label for="type">Type Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="type">
										<form:options items="${typeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div>
-->
								
								<tr>
									<td class="label"><span class="red">*</span><em></em>Type Code:</td>
									<td><form:select path="type">
										<form:options items="${typeRefData}" itemLabel="desc"
											itemValue="code" />
									</form:select></td>
								</tr>

							</table>
							</td>
						<tr>
							<td><img src="images/spacer.gif" width="1" height="1"
								class="heightControl"></td>
						</tr>
						<tr>
							<td align="center" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td colspan=2 valign="top"><br>
									<br>
									<input type="image" name="_target1"
										src="/caaers/images/b-next.gif" border="0"
										alt="continue to next page">
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</form:form> <!-- LEFT CONTENT ENDS HERE -->
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="copyright">&copy; 2006 SemanticBits Company. All Rights
Reserved</div>
</div>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
