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
		<td class="display"><!-- TABS LEFT START HERE -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>

				<!-- LEFT CONTENT STARTS HERE -->

				<td valign="top" class="additionals2"><!-- RIGHT CONTENT STARTS HERE -->
				<form:form name="searchDetailsForm" method="post">
    				 <tags:tabFields tab="${tab}"/>
					<div><input type="hidden" name="_page" value="1"></div>
					
					<br>
					<strong>Step 2. Identifiers : Add Identifiers associated with the Study </strong> (<span class="red">*</span>
					<em>Required Information </em>)<br>
					<br>

					<table width="700" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>											
							<td width="50%" valign="top">							
								<table width="308" border="0" cellspacing="0" cellpadding="0"
									id="table1">
									
								<tr>
									<td class="label">Primary Indicator:</td>
									<td><form:checkbox path="identifiers[0].primaryIndicator"/></td>
								</tr>
								<tr>
									<td><img src="images/spacer.gif" width="1" height="1"
										class="heightControl"></td>
								</tr>
								<tr>
									<td><img src="images/spacer.gif" width="1" height="1"
										class="heightControl"></td>
								</tr>
								<tr>
									<td class="label">Source:</td>
									<td><form:input path="identifiers[0].source"/></td>
								</tr>
								<tr>
									<td><img src="images/spacer.gif" width="1" height="1"
										class="heightControl"></td>
								</tr>
								<tr>
									<td class="label"><span class="red">*</span><em></em>Type:</td>
									<td><form:input path="identifiers[0].type"/></td>
									<td><form:errors path="identifiers[0].type" /></td>
								</tr>
									<tr>
									<td><img src="images/spacer.gif" width="1" height="1"
										class="heightControl"></td>
								</tr>
								<tr>
									<td class="label">Value:</td>
									<td><form:input path="identifiers[0].value"/></td>
								</tr>								
								</table>
							</td>
							
							<td width="20%" valign="top">														
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
										<input type="image" name="_target0" src="/caaers/images/b-prev.gif" border="0"
											alt="goto previous page">									
										<input type="image" name="_target2" src="/caaers/images/b-next.gif" border="0"
											alt="continue to next page">
										<input type="image" name="_target0" src="/caaers/images/b-cancel.gif" border="0"
											alt="start over from start page">	
									</td>						
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
