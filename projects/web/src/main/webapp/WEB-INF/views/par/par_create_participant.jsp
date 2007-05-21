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
<tags:stylesheetLink name="participant"/>
</head>
<body>
    <p class="instructions">
        You are creating a new Participant
    </p>
    
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="singleFields">
        
        <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>First Name:</div>
	            <div class="value"><form:input path="firstName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Last Name:</div>
	            <div class="value"><form:input path="lastName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Maiden Name:</div>
	            <div class="value"><form:input path="maidenName" /></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value"><form:input path="middleName" /></div>
	        </div>
        </div>
        
        <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>Date of Birth:</div>
	            <div class="value"><tags:dateInput path="dateOfBirth"/></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Ethnicity:</div>
	            <div class="value">
	            		<form:select path="ethnicity">
							<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Race:</div>
	            <div class="value">
	            		<form:select path="race">
						<form:options items="${race}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Gender:</div>
	            <div class="value">
	            		<form:select path="gender">
						<form:options items="${genders}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
        </div>
        
        <div class="endpanes">&nbsp;</div>

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
