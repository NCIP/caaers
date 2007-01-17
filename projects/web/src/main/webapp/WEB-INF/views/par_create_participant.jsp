<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${action} Participant</title>
<style type="text/css">
        div.label {
            width: 35%;
        }
        div.submit {
            text-align: right;
        }
        form {
            width: 20em;
        }
    </style>
</head>
<body>
<h1>${action} Participant</h1>
<%--<c:url value="/pages/createParticipant?id=${studyId}" var="formAction"/>
<c:url value="/pages/createParticipant?id=1" var="formAction"/>--%>
<form:form method="post">
    <tags:tabFields tab="${tab}"/>
    <div class="row">
	<div class="label"><form:label path="instituitionalPatientNumber">Institutional Patient Number</form:label>
	</div>
	<div class="value"><form:input path="instituitionalPatientNumber" />
	</div>
	</div>
	<div class="row">
	<div class="label"><form:label path="institution">Institution</form:label>
	</div>
	<div class="value"><form:input path="institution" /></div>
	</div>
	<div class="row">
	<div class="label"><form:label path="firstName">First Name</form:label>
	</div>
	<div class="value"><form:input path="firstName" /></div>
	</div>
	<div class="row">
	<div class="label"><form:label path="middleName">Middle Name</form:label>
	</div>
	<div class="value"><form:input path="middleName" /></div>
	</div>
	<div class="row">
	<div class="label"><form:label path="maidenName">Maiden Name</form:label>
	</div>
	<div class="value"><form:input path="maidenName" /></div>
	</div>
	<div class="row">
	<div class="label"><form:label path="dateOfBirth">Date of Birth (mm/dd/yyyy)</form:label>
	</div>
	<div class="value"><form:input path="dateOfBirth" /></div>
	</div>

	<div class="row">
	<div class="label"><form:label path="gender">Gender</form:label>
	</div>
	<div class="value"><form:select path="gender">
		<form:options items="${genders}" />
	</form:select></div>
	</div>

	<div class="row">
	<div class="label"><form:label path="race">Race</form:label></div>
	<div class="value"><form:input path="race" /></div>
	</div>

	<div class="row">
	<div class="label"><form:label path="ethnicity">Ethnicity</form:label>
	</div>
	<div class="value"><form:input path="ethnicity" /></div>
	</div>
	<form:input path="identifiers[0].source" />
	<c:forEach var="index" begin="0" end="4">
		<form:input path="identifiers[${index}].type" />
	</c:forEach>


	<table width="700" border="0" cellspacing="0" cellpadding="0"
		id="table1">
		<tr>
			<td align="center"><span class="red">*</span><em></em><B>Other
			Type:</td>
			<td align="center"><span class="red">*</span><em></em><B>Other
			Value:</td>
			<td align="center"><span class="red">*</span><em></em><B>Other
			Source:</td>
			<td align="center"><B>Is Primary:</td>
		</tr>

		<c:forEach var="index" begin="0" end="4">
			<tr>
				<td align="center"><form:input
					path="identifiers[${index}].type" /></td>
				<td align="center"><form:input
					path="identifiers[${index}].value" /></td>
				<td align="center"><form:select
					path="identifiers[${index}].source">
					<form:options items="${sources}" />
				</form:select></td>

				<td align="center"><form:radiobutton
					path="identifiers[${index}].primaryIndicator" /></td>
			</tr>

		</c:forEach>

	</table>

	<hr align="left" width="95%">
	<table width="700" border="0" cellspacing="0" cellpadding="0"
		id="details">
		<tr align="center">
			<td colspan="4"><br>
			<br>
		<tr>
			<td align="center" colspan="3"><!-- action buttons begins -->
			<table cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><input type="image" name="_target1"
						src="images/b-continue.gif" border="0" alt="continue to next page"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

</form:form>
</body>
</html>
