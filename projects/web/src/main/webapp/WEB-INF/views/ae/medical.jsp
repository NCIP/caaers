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
		
		<chrome:division title="Patient Demographic Information">
		
		<div class="row"> 
	        <div class="medical-label">Patient ID</div>
			<div class="text"> ${command.participant.id} </div
		</div> 

		<div class="row">
	        <div class="medical-label">Birth Date</div>
			<div class="text"> ${command.participant.dateOfBirth}</div>
		</div>

		<div class="row">
	        <div class="medical-label">Race</div>
			<div class="text">${command.participant.race}</div>
		</div>
		
		<div class="row">
	        <div class="medical-label">Ethnicity</div>
			<div class="text">${command.participant.ethnicity}</div>
		</div>

		<div class="row">
	        <div class="medical-label">Gender</div>
			<div class="text">${command.participant.gender}</div>
		</div>

		<div class="row">
	        <div class="medical-label">Height (cm)</div>
			<div class="value"> <input type="text"/> </div>
		</div>

		<div class="row">
	        <div class="medical-label">Weight (kg)</div>
			<div class="value"> <input type="text"/> </div>
		</div>

		<div class="row">
	        <div class="medical-label">Baseline performance status</div>
			<div class="value"> <input type="text"/> </div>
		</div>

		</chrome:division>

		<chrome:division title="Patient Disease Information">

		<p id="instructions">
            If appropriate Disease Name is not on the list above, provide appropriate Disease
        </p>
        		
		<div class="row">
			<div class="label">Select Disease</div>
			<div class="value"> 			
				<select id="disease" name="disease" onChange="javascript:chooseDisease();">
					<option value=-1>please select--</option>
					<c:forEach var="studyDisease " varStatus="status" items="${command.study.studyDiseases}">	
							<option value=${studyDisease.diseaseTerm.id}>${studyDisease.diseaseTerm.term} </option>
					</c:forEach>
				</select>
		    </div>
        </div> 

		<div class="row">
	        <div class="medical-label"> Disease Entered </div>
			<div class="value"> <input type="text"/> </div>
		</div>

		</chrome:division>
    </form:form>
</body>
</html>
