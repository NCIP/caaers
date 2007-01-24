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
<body>
<chrome:body title="Create a new Participant">
<p id="instructions">
        You are creating a new Participant
    </p>
    
    <chrome:division title="Participant" id="participant">
    <form:form method="post" cssClass="standard">
    <tags:errors path="*"/>
	<tags:tabFields tab="${tab}" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1">
        <tr><td>
        	
            <table width="50%" border="0" cellspacing="0" cellpadding="0" class="contentAreaL">
        	<tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>First Name:</td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Last Name:</td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr valign="top">
                <td class="label">Maiden Name:</td>
                <td><form:input path="maidenName" /></td>
            </tr>
            <tr>
                <td class="label">Middle Name:</td>
                <td><form:input path="middleName" /></td>
            </tr>
            
        </table>
        </td>
        	<td><img src="<chrome:imageUrl name="spacer.gif"/>" width="30" height="1"
                    class="heightControl"></td>
        <td>
        <table width="50%" border="0" cellspacing="0" cellpadding="0" class="contentAreaL">
            <tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
        	<tr valign="top">
            
                <td class="label"><span class="red">*</span>Date of Birth:</td>
                <td><form:input path="dateOfBirth" /></td>
            </tr>
            <tr valign="top">
                <td class="label">Ethnicity:</td>
                <td><form:select path="ethnicity">
					<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label">Race:</td>
                <td><form:select path="race">
					<form:options items="${race}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label">Gender:</td>
                <td><form:select path="gender">
					<form:options items="${genders}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
        </table>
        </td>
        </tr>
        </table>
        <br><br>
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
    </chrome:division>    
    
    
    
    
    
    
    
    
<%--    
    

<chrome:division title="Participant">
<form:form method="post" cssClass="standard">
	 <tags:errors path="*"/>
	<tags:tabFields tab="${tab}" />
	<c:forEach items="${fieldGroups.main.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
					<br><br>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="details">
						<tr>
							<td width="50%" valign="top">
							<table width="308" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="label"><span class="red">*</span><label for="firstName">First Name:</label></td>
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
</chrome:division>

--%>
</chrome:body>
</body>
</html>
