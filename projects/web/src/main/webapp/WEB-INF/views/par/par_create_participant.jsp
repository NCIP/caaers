<%--
    TODO: this entire flow's views need to be refactored.
--%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
</head>
<body>
    <p class="instructions">
        You are creating a new Participant
    </p>
    
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="singleFields">
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
                <td><tags:dateInput path="dateOfBirth"/></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Ethnicity:</td>
                <td><form:select path="ethnicity">
					<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Race:</td>
                <td><form:select path="race">
					<form:options items="${race}" itemLabel="desc" itemValue="code" />
				    </form:select></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Gender:</td>
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
        </jsp:attribute>
    </tags:tabForm>    
</body>
</html>
