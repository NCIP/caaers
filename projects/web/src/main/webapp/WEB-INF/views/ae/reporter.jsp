<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
		
		function chooseStaff(){
				
				var id = document.getElementById("staff").value;

				if(id == -1)
				{
					clear();
				}
				else {
					createAE.getResearchStaff(id, fill)
				}
				
		}

		function fill(staff)
		{
			document.getElementsByName('aeReport.reporter.firstName')[0].value=staff.firstName;
			document.getElementsByName('aeReport.reporter.lastName')[0].value=staff.lastName;
			document.getElementsByName('aeReport.reporter.middleName')[0].value=staff.middleName;

			if(document.getElementById("option1").checked)
			{
				selected();
			}
			// call selected()
		}

		function clear()
		{
			document.getElementsByName('aeReport.reporter.firstName')[0].value='';
			document.getElementsByName('aeReport.reporter.lastName')[0].value='';
			document.getElementsByName('aeReport.reporter.middleName')[0].value='';			

			if(document.getElementById("option1").checked)
			{
				selected();
			}
			// call selected()
		}

		function selected(){
			if(document.getElementById("option1").checked)
			{
				document.getElementsByName('aeReport.physician.firstName')[0].value=document.getElementsByName('aeReport.reporter.firstName')[0].value;
				document.getElementsByName('aeReport.physician.lastName')[0].value=document.getElementsByName('aeReport.reporter.lastName')[0].value;
				document.getElementsByName('aeReport.physician.middleName')[0].value=document.getElementsByName('aeReport.reporter.middleName')[0].value;
				document.getElementsByName('aeReport.physician.contactMechanims[0].value')[0].value=document.getElementsByName('aeReport.reporter.contactMechanims[0].value')[0].value;
				document.getElementsByName('aeReport.physician.contactMechanims[1].value')[0].value=document.getElementsByName('aeReport.reporter.contactMechanims[1].value')[0].value;
				document.getElementsByName('aeReport.physician.contactMechanims[2].value')[0].value=document.getElementsByName('aeReport.reporter.contactMechanims[2].value')[0].value;
			}
			else 
			{
				document.getElementsByName('aeReport.physician.firstName')[0].value='';
				document.getElementsByName('aeReport.physician.lastName')[0].value='';
				document.getElementsByName('aeReport.physician.middleName')[0].value='';	
				document.getElementsByName('aeReport.physician.contactMechanims[0].value')[0].value='';	
				document.getElementsByName('aeReport.physician.contactMechanims[1].value')[0].value='';	
				document.getElementsByName('aeReport.physician.contactMechanims[2].value')[0].value='';	
			}
		}
     
    </script>
</head>
<body>
    <form:form cssClass="standard">
        
        <tags:hasErrorsMessage/>

        <tags:tabFields tab="${tab}"/>
		
		<chrome:division title="Reporter Details">
		
		<div class="row">
			<p id="instructions">
            You are entering reporter information. 
			You can select from the existing study personnel or you can enter a new one. </p>
			<div class="label">Study Personnel</div>
			<div class="value"> 
			<%--	<select id="staff" name="staff" onChange="javascript:chooseStaff();">
					<option value=-1>please select--</option>
					<c:forEach var="staff" varStatus="status" items="${staffRefData}"> 																
						<option value=${staff.id}>${staff.firstName}</option>
					</c:forEach>
				</select> --%>

				<select id="staff" name="staff" onChange="javascript:chooseStaff();">
					<option value=-1>please select--</option>
					<c:forEach var="site" varStatus="status" items="${command.study.studySites}">	
						<c:forEach var="personnel" varStatus="status1" items="${site.studyPersonnels}">
							<option value=${personnel.researchStaff.id}>${personnel.researchStaff.firstName}, ${personnel.researchStaff.lastName} </option>
						</c:forEach>
					</c:forEach>
				</select>
		    </div>
        </div> 

		<div class="row">
	        <div class="label">First Name</div>
			<div class="value"><form:input path="aeReport.reporter.firstName"/></div>
		</div>

		<div class="row">
	        <div class="label">Last Name</div>
			<div class="value"><form:input path="aeReport.reporter.lastName"/></div>
		</div>

		<div class="row">
	        <div class="label">Middle Name</div>
			<div class="value"><form:input path="aeReport.reporter.middleName"/></div>
		</div>
		
		<div class="row">
	        <div class="label">Email</div>
			<div class="value"><form:input path="aeReport.reporter.contactMechanims[0].value"/></div>
		</div>

		<div class="row">
	        <div class="label">Fax</div>
			<div class="value"><form:input path="aeReport.reporter.contactMechanims[1].value"/></div>
		</div>

		<div class="row">
	        <div class="label">Phone</div>
			<div class="value"><form:input path="aeReport.reporter.contactMechanims[2].value"/></div>
		</div>

		</chrome:division>

		<chrome:division title="Physician Details">

		<p id="instructions">
            If the physician is the reporter then check this box. 
        
        <input type="checkbox" id="option1" name="option1" onClick="javascript:selected()"> </p>

		<div class="row">
	        <div class="label">First Name</div>
			<div class="value"><form:input path="aeReport.physician.firstName"/></div>
		</div>

		<div class="row">
	        <div class="label">Last Name</div>
			<div class="value"><form:input path="aeReport.physician.lastName"/></div>
		</div>

		<div class="row">
	        <div class="label">Middle Name</div>
			<div class="value"><form:input path="aeReport.physician.middleName"/></div>
		</div>

		<div class="row">
	        <div class="label">Email</div>
			<div class="value"><form:input path="aeReport.physician.contactMechanims[0].value"/></div>
		</div>

		<div class="row">
	        <div class="label">Fax</div>
			<div class="value"><form:input path="aeReport.physician.contactMechanims[1].value"/></div>
		</div>

		<div class="row">
	        <div class="label">Phone</div>
			<div class="value"><form:input path="aeReport.physician.contactMechanims[2].value"/></div>
		</div>
		</chrome:division>
    </form:form>
</body>
</html>
