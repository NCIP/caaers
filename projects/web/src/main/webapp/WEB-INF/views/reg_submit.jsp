<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>caAERS</title>
<link href="resources/styles.css" rel="stylesheet" type="text/css">
<link href="resources/search.css" rel="stylesheet" type="text/css">
<script>
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function getPage(s){
	parent.window.location="reg_patient_search.htm";
}
function add(){
var action = confirm ("You have not completed adding this protocol.\r\rStarting over will lose current protocol data?")
if (action){
	parent.window.location="reg_enroll_patient.htm";
}}
function validatePage(){
	return true;
}
function doNothing(){
}

function submitPage(s){
	document.getElementById("nextView").value=s;
	document.reviewForm.submit();
}

function updateTargetPage(s){
	//if(validatePage()){
		document.getElementById("nextView").value=s;
		document.reviewForm.submit();
	//}
}
</script>
</head>
<body>
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="topNav">

	<tr valign="middle">
		<td class="right"><img src="images/topDivider.gif" width="2"
			height="20" align="absmiddle" class="divider"><a href="logOff">Log
		Off</a></td>
	</tr>
</table>
<!-- TOP NAVIGATION ENDS HERE -->
<!-- SUB NAV STARTS HERE -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="subNav">
	<tr>
		<td valign="middle" class="right"><a href="help">Help</a></td>
	</tr>
</table>
<!-- SUB NAV ENDS HERE -->
<form:form name="reviewForm" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="display">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="tabs">
					<tr>
						<td width="100%" id="tabDisplay"><span class="tab"> <img
							src="images/tabWhiteL.gif" width="3" height="16"
							align="absmiddle"><a href="searchRegisterStudy?participantId=${command.participant.id}"> 1. Select Study</a>&nbsp&nbsp<img
							src="images/tabWhiteR.gif" width="3" height="16"
							align="absmiddle"><img src="images/tabGrayL.gif" width="3"
							height="16" align="absmiddle"><a href="searchRegisterParticipant?studySiteId=${command.studySite.id}"> 2. Select Participant</a>&nbsp&nbsp<img
							src="images/tabGrayR.gif" width="3" height="16" align="absmiddle"><img
							src="images/tabGrayL.gif" width="3" height="16" align="absmiddle">
						3. Review and Submit <img src="images/tabGrayR.gif" width="3"
							height="16" align="absmiddle"></span></td>
						<td><img src="images/spacer.gif" width="7" height="1"></td>
					</tr>
					<tr>
						<td colspan="2" class="tabBotL"><img src="images/spacer.gif"
							width="1" height="7"></td>
					</tr>
				</table>
				<br>
				</td>
			</tr>
			<!-- MAIN BODY STARTS HERE -->
			<tr>
				<td>
				<div class="workArea">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					
						<tr>
							<!-- CURRENT DRIVER/UNIT TITLE STARTS HERE -->

							<td id="current">Confirm Registration for 
							${command.participant.firstName} ${command.participant.lastName} on
							${command.studySite.study.shortTitle}</td>
							<!-- CURRENT DRIVER/UNIT TITLE ENDS HERE -->
						</tr>
						<tr>
							<td class="display"><!-- TABS LEFT START HERE -->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<br>
									<!-- LEFT CONTENT STARTS HERE -->
									<td valign="top" class="additionals2"><!-- LEFT FORM STARTS HERE -->
									<!-- RIGHT CONTENT STARTS HERE --> <input type="hidden"
										id="nextView" name="nextView"> <strong>Step 1. Participant
									Information </strong><br>
									<table width="700" border="0" cellspacing="0" cellpadding="0"
										id="details">
										<tr>
											<td width="50%" valign="top">
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
												<tr>
													<td><img src="images/spacer.gif" width="1" height="1"
														class="heightControl"></td>
													<td><img src="images/spacer.gif" width="1" height="1"
														class="heightControl"></td>
												</tr>
												<tr>
													<td class="label"><span class="red">*</span><em></em>First
													Name:</td>
													<td>${command.participant.firstName}</td>
												</tr>
												<tr>
													<td class="label"><span class="red">*</span><em></em>Last
													Name:</td>
													<td>${command.participant.lastName}</td>
												</tr>
												<tr>
													<td class="label"><span class="red">*</span><em></em>Gender:
													</td>
													<td>${command.participant.gender}</td>
													
												</tr>
											</table>
											</td>
											<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
												<tr>
													<td class="label"><span class="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span><em></em>Birth
													Date:</td>
													<td valign="top">${command.participant.dateOfBirth}</td>
												</tr>
												<tr>
													<td class="label"><span class="red">*</span><em></em>Ethnicity:
													</td>
													<td>${command.participant.ethnicity}</td>
												</tr>
												<tr>
													<td class="label"><span class="red">*</span><em></em>Race:</td>
													<td>${command.participant.race}</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									<a href="searchRegisterParticipant?studySiteId=${command.studySite.id}"><img
										src="images/b-edit.gif" alt="Choose Different Participant" width="39" height="16"
										border="0"></a>

									<hr align="left" width="95%">
									<strong><br>
									Step 2. Study Information </strong> <br>
									<br>
									<div class="review"><strong>Study Details:</strong><br>
									<table width="700" border="0" cellspacing="0" cellpadding="0"
										id="details">
										<tr>
											<td width="50%" valign="top">
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
												<tr>
													<td class="label">Short Title:</td>
													<td>${command.studySite.study.shortTitle}</td>
												</tr>
												<tr>
													<td class="label"><em></em> Investigator Code:</td>
													<td>${command.studySite.study.principalInvestigatorCode}</td>
												</tr>
												<tr>
													<td class="label"><em></em> Investigator Name:</td>
													<td>${command.studySite.study.principalInvestigatorName}</td>
												</tr>
												<tr>
													<td class="label"><em></em><em></em> Phase Code:</td>
													<td>${command.studySite.study.phaseCode}</td>
												</tr>
											</table>
											</td>
											<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
												<tr>
													<td class="label"><em></em>Sponsor Code:</td>
													<td>${command.studySite.study.primarySponsorCode}</td>
												</tr>
												<tr>
													<td class="label"><em></em>Sponsor Name:</td>
													<td>${command.studySite.study.primarySponsorName}</td>
												</tr>
												<tr>
													<td class="label">Review Date:</td>
													<td>${command.studySite.study.reviewDate}</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									<br>
									<strong>Study Site Information:</strong><br>
									<table width="700" border="0" cellspacing="0" cellpadding="0"
										id="details">
										
										<tr>
											<td width="50%" valign="top">
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
												<tr>
													<td class="label"><em></em> Name:</td>
													<td>${command.studySite.site.name}</td>
												</tr>
											</table>
											</td>
											<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
											<table width="308" border="0" cellspacing="0" cellpadding="0"
												id="table1">
											</table>
											</td>
										</tr>
			
									</table>
									<br>
									<a href="searchRegisterStudy?participantId=${command.participant.id}"><img
										src="images/b-edit.gif" alt="Choose Different Study" width="39" height="16"
										border="0"></a>
									<hr align="left" width="95%">
									<br>
							
									
									
									<br>
									<table width="700" border="0" cellspacing="0" cellpadding="0"
										id="details">
										<tr align="center">
											<td colspan=2 valign="top"><br>
											<br>
											<a href="javascript:updateTargetPage('processFinish')"><img
												src="images/b-submit.gif" alt="Submit" width="59"
												height="16" border="0"></a> <a href="home"
												<img src="images/b-startOver.gif" alt="Start Over" width="67"
												height="16" border="0"></a></td>
										</tr>
									</table>
									</div>
									</td>

									<!-- LEFT CONTENT ENDS HERE -->
								</tr>
							</table>
							</td>
						</tr>
					</form:form>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
