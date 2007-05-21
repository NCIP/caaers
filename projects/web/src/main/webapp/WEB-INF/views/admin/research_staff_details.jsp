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
    <form:form method="post" cssClass="standard" name="studySiteForm">
    <chrome:box title="Research Staff" id="research_staff" autopad="true">
   	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
	<p id="instructions">
        Add a new Research Staff
    </p>
    <tags:errors path="*"/>
    
    <div class="content">
       <div class="row">
           <div class="label"><span class="red">*</span>First Name:</div>
           <div class="value"><form:input path="firstName" /></div>
       </div>
       <div class="row">
           <div class="label"><span class="red">*</span>Last Name:</div>
           <div class="value"><form:input path="lastName" /></div>
       </div>
    </div>
    <br>
    <br>
    <input type="submit" value="Save"/>
    </chrome:box>    
    </form:form>
   
</body>
</html>
