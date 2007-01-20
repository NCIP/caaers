<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
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

				<td valign="top" class="additionals2"><!-- LEFT FORM STARTS HERE -->
				<!-- RIGHT CONTENT STARTS HERE --> <form:form method="post">
					<tags:tabFields tab="${tab}" />
					<div><input type="hidden" name="_page" value="3"></div>
					<strong>Step 5. Review and Submit</strong>
					<br>
					<br>
					<hr>
					<strong>Study Details </strong>
					<br>
					<br>
					<div class="review">
					<table width="250" border="0" cellspacing="0" cellpadding="0"
						id="table1">
						<tr>
							<td class="label">Short Title:</td>
							<td class="label">${command.shortTitle}</td>
						</tr>
						<tr>
							<td class="label">Target Accrual Number:</td>
							<td>${command.targetAccrualNumber}</td>
						</tr>
						<tr>
							<td class="label">Status:</td>
							<td>${command.status}</td>
						</tr>
						<tr>
							<td class="label">Disease Code:</td>
							<td>${command.diseaseCode}</td>
						</tr>
						<%-- 	<tr>
							<td class="label">Disease Type:</td>
							<td>${command.type}</td>
						</tr> --%>
						<tr>
							<td class="label">Monitor Code:</td>
							<td>${command.monitorCode}</td>
						</tr>
						<tr>
							<td class="label">Phase Code:</td>
							<td>${command.phaseCode}</td>
						</tr>
						<tr>
							<td class="label">Multi Institution:</td>
							<td><form:checkbox path="multiInstitutionIndicator" /></td>
							<td class="label">Blinded:</td>
							<td><form:checkbox path="blindedIndicator" /></td>
							<td class="label">Randomized:</td>
							<td><form:checkbox path="randomizedIndicator" /></td>
						</tr>
						<tr>
							<td><br>
							<br>
							<input type="image" name="_target0" src="images/b-edit.gif"
								border="0" alt="edit this page">
						</tr>

					</table>
					</div>
					<br>

					<hr>
					<strong>Study Identifiers</strong>
					<br>
					<br>
					<div class="review">

					<table width="250" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td class="label">Source:</td>
							<td>${command.identifiers[0].source}</td>
						</tr>
						<tr>
							<td class="label">Type:</td>
							<td>${command.identifiers[0].type}</td>
						</tr>
						<tr>
							<td class="label">Value:</td>
							<td>${command.identifiers[0].value}</td>
						</tr>
						<tr>
							<td><br>
							<br>
							<input type="image" name="_target1" src="images/b-edit.gif"
								border="0" alt="edit this page"></td>
						</tr>

					</table>
					</div>
					<br>
					<br>
					<hr>
					<strong>Study Site</strong>
					<br>
					<br>
					<div class="review">

					<table width="250" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td class="label">Status:</td>
							<td>${command.studySites[0].statusCode}</td>
						</tr>
						<tr>
							<td class="label">Role:</td>
							<td>${command.studySites[0].roleCode}</td>
						</tr>
						<tr>
							<td class="label">Start Date:</td>
							<td>${command.studySites[0].startDate}</td>
						</tr>
						<tr>
							<td class="label">IRB Approval Date::</td>
							<td>${command.studySites[0].irbApprovalDate}</td>
						</tr>
						<tr>
							<td><br>
							<br>
							<input type="image" name="_target2" src="images/b-edit.gif"
								border="0" alt="edit this page"></td>
						</tr>

					</table>
					</div>
					<br>


					<hr>

					<br>
					<br>
					<div class="review">

					<table width="250" border="0" cellspacing="0" cellpadding="0"
						id="details">

						<tr>
							<td align="center" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td colspan=2 valign="top"><br>
									<br>
									<input type="image" name="_target0"
										src="/caaers/images/b-prev.gif" border="0"
										alt="goto previous page"> <input type="image" name="_finish"
										src="/caaers/images/b-save.gif" border="0"
										alt="continue to next page"> <input type="image"
										name="_target0" src="/caaers/images/b-cancel.gif" border="0"
										alt="start over from start page"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<br>
				</form:form></td>
				<!-- LEFT CONTENT ENDS HERE -->
		</table>
		</td>
	</tr>
</table>
<div id="copyright">&copy; 2006 SemanticBits. All Rights Reserved</div>
</div>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
