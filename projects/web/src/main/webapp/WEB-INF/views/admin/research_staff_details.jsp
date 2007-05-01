<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
    <title>Add research staff</title>
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
<p id="instructions">
        You are creating a new Research Staff
    </p>
    
    <chrome:box title="Research Staff" id="research_staff" autopad="true">
    <form:form method="post" cssClass="standard" name="studySiteForm">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
    <tags:errors path="*"/>
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
        <input type="submit" value="Save"/>
        </form:form>
    </chrome:box>    
</body>
</html>
