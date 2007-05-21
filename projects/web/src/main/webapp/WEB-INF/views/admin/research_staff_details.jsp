<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
    <title>Add research staff</title>
    <tags:stylesheetLink name="participant"/>

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
    
        <input type="submit" value="Save"/>
        </form:form>
    </chrome:box>    
</body>
</html>
