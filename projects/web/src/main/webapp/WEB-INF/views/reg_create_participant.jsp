<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
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
<script>

function submitPage(){
	document.getElementById("searchParticipant").submit();
}
</script>
<body>
<chrome:body title="Search for a Participant">
    <form:form id="searchParticipant" name="searchParticipant" action="/caaers/pages/searchRegisterParticipant?studySiteId=${studySiteId }"  method="post">
        <a href="/caaers/pages/participant/create?studySiteId=${studySiteId }">Create
						new Participant for Registration</a>
    	<table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
            </tr>
            <tr>
                <td class="searchType">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td><form:select path="searchType">
						<form:options items="${searchType}" itemLabel="desc"itemValue="code" />
					</form:select></td>
                <td><form:input path="searchText" size="25" /></td>
                <td><input name="imageField" type="image" class="button"
					onClick="submitPage();return false;" src="/images/chrome/b-go.gif" alt="GO"
					align="middle" width="22" height="10" border="0"></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4" class="notation">
                    <span class="labels">(<span class="red">*</span><em>Required Information</em>)</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ^ Minimum two characters for search.
                </td>
            </tr>
        </table>
    </form:form>
</chrome:body>

<br>
<chrome:body title="Create a new Participant">
<form:form method="post">
	<tags:tabFields tab="${tab}" />
					<br><br>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td width="50%" valign="top">
							<table width="308" border="0" cellspacing="0" cellpadding="0"
								id="table1">
								<tr>
									<td class="label"><span class="red">*</span><em></em>First
									Name:</td>
									<td><form:input path="firstName" /></td>
								</tr>
								<tr>
									<td class="label"><span class="red">*</span><em></em>Last
									Name:</td>
									<td><form:input path="lastName" /></td>
								</tr>
								<tr>
									<td class="label">Middle
									Name:</td>
									<td><form:input path="middleName" /></td>
								</tr>
								<tr>
									<td class="label"><em></em>Maiden
									Name:</td>
									<td><form:input path="maidenName" /></td>
								</tr>
							</table>
							</td>
							<td width="50%" valign="top" class="contentAreaR"><strong><strong><strong></strong></strong></strong>
							<table width="308" border="0" cellspacing="0" cellpadding="0"
								id="table1">
								<tr>
									<td class="label"><span class="red">*</span><em></em>Birth
									Date:(mm/dd/yyyy)</td>
									<td valign="top"><form:input path="dateOfBirth" /></td>
								</tr>
								<tr>
									<td class="label"><em></em>Ethnic Group Code:
									</td>
									<td><form:select path="ethnicity">
					  						<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
				    					</form:select></td>
								</tr>
								<tr>
									<td class="label"><em></em>Race Code:</td>
									<td><form:select path="race">
					  						<form:options items="${race}" itemLabel="desc" itemValue="code" />
				    					</form:select></td>
								</tr>
								<tr>
									<td class="label"><em></em>Administrative Gender Code:
									</td>
									<td><form:select path="gender">
					  						<form:options items="${genders}" itemLabel="desc" itemValue="code" />
				    					</form:select></td>

								</tr>
							</table>
							</td>
						</tr>
					</table><br><br><br>
	<table width="700" border="0" cellspacing="0" cellpadding="0"
		id="table1">
		<tr>
			<td align="center"><B>
			Type:</td>
			<td align="center"><em></em><B>
			Value:</td>
			<td align="center"><em></em><B>
			Source:</td>
			<td align="center"><B>Is Primary:</td>
		</tr>

		<c:forEach var="index" begin="0" end="4">
			<tr>
				<td align="center"><form:input
					path="identifiers[${index}].type" /></td>
				<td align="center"><form:input
					path="identifiers[${index}].value" /></td>
				<td align="center">
				<form:select path="identifiers[${index}].source">
					  						<form:options items="${sources}" itemLabel="desc" itemValue="code" />
				    					</form:select></td>

				<td align="center"><form:radiobutton
					path="identifiers[${index}].primaryIndicator" /></td>
			</tr>

		</c:forEach>

	</table>


</form:form>
</chrome:body>
</body>
</html>
