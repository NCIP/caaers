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
<script>
function navRollOver(obj, state) {
  document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
}
function validatePage(){
	return true;
}
</script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<div class="workArea">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
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
				<!-- RIGHT CONTENT STARTS HERE --> 
				<form:form method="post">
				<tags:tabFields tab="${tab}"/>
				<div><input type="hidden" name="_page" value="2"></div>
					<strong>Step 2. Study Site </strong> (<span class="red">*</span>
					<em>Required Information </em>)<br>

					<br>
					<table width="600" border="0" cellspacing="0" cellpadding="0"
						id="table1">
						<tr>
							<td class="label"><span class="red">*</span><em></em>Choose HealthCare Site:</td>
							<td><form:select path="studySites[0].site">
								<form:options items="${sitesRefData}" itemLabel="name" itemValue="id" />
								</form:select></td>
						</tr>
						<tr>
							<td><img src="images/spacer.gif" width="1" height="3"
								class="heightControl"></td>
						</tr>						
						
						<tr>
							<td><img src="images/spacer.gif" width="1" height="3"
								class="heightControl"></td>
						</tr>
						<tr>
							<td class="label"><span class="red">*</span><em></em>Status
							Code:</td>
							<td><form:input path="studySites[0].statusCode" /></td>
						</tr>
							<tr>
							<td><img src="images/spacer.gif" width="1" height="3"
								class="heightControl"></td>
						</tr>
						<tr>
							<td class="label"><span class="red">*</span><em></em>Role
							Code:</td>
							<td><form:input path="studySites[0].roleCode" /></td>
						</tr>
						<tr>
							<td><img src="images/spacer.gif" width="1" height="3"
								class="heightControl"></td>
						</tr>
						<tr>
							<td class="label"><span class="red">*</span><em></em>Start Date:</td>
							<td valign="top" align="left"><form:input
								path="studySites[0].startDate" />&nbsp;<a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></td>
						</tr>
						<tr>
							<td class="label">End Date:</td>
							<td valign="top" align="left"><form:input
								path="studySites[0].endDate" />&nbsp;<a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></td>
						<tr>
						<tr>
							<td class="label"><span class="red">*</span><em></em>IRB
								Approval Date:</td>
							<td valign="top" align="left"><form:input
								path="studySites[0].irbApprovalDate" />&nbsp;<a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></td>
						</tr>
						<tr>
							<td><img src="images/spacer.gif" width="1" height="3"
								class="heightControl"></td>
						</tr>
						<tr>
							<td align="center" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td colspan=2 valign="top"><br>
										<br>
										<input type="image" name="_target1" src="/caaers/images/b-prev.gif" border="0"
											alt="goto previous page">									
										<input type="image" name="_target3" src="/caaers/images/b-next.gif" border="0"
											alt="continue to next page">
										<input type="image" name="_target0" src="/caaers/images/b-cancel.gif" border="0"
											alt="start over from start page">	
									</td>						
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</form:form></td>
			</tr>
		</table>
		<br>
		</td>
		<!-- LEFT CONTENT ENDS HERE -->
	</tr>
</table>
<div id="copyright">&copy; 2006 SemanticBits. All Rights Reserved</div>
</div>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
