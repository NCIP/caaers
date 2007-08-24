<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
    <title>Add organization</title>
	<tags:stylesheetLink name="participant"/>
	</head>
<body>
    <form:form method="post" action="createOrganization" name="form" id="form">
    <chrome:box title="Organization Details" id="organization" autopad="true">
	
    <tags:errors path="*"/>
      
     <div class="content">
        <div class="row">
            <div class="label"><span class="red">*</span>Name:</div>
            <div class="value"><form:input path="name" size="90"/></div>
        </div>
        <div class="row">
            <div class="label">Description:</div>
            <div class="value"><form:textarea path="descriptionText" cssStyle="width:560px;height:80px;"  /></div>
        </div>
       <div class="row">
            <div class="label"><span class="red">*</span>NCI Identifier:</div>
            <div class="value"><form:input path="nciInstituteCode" size="40" /></div>
        </div>   
     </div>
     <div align = "right">
     <input type="submit" value="Save"/>
    </chrome:box>
   </form:form>
 </body>
</html>
