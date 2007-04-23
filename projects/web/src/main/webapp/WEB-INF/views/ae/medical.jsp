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
		
		function chooseDisease(){
				var term = document.getElementById('disease').value;
				if(term == -1)
				{
					document.getElementById('dis').value = ''; 
				}
				else {
					document.getElementById('dis').value = term; 
				}
				
		}

		function chooseDiseaseSite(){
				var term = document.getElementById('disease_site').value;
				if(term == -1)
				{
					document.getElementById('dis_site').value = ''; 
				}
				else {
					document.getElementById('dis_site').value = term; 
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
			<div class="value"> <form:input path="aeReport.participantHistory.height" /> &nbsp;

				<form:select path="aeReport.participantHistory.heightUnitOfMeasure">
					<option value="please select --">please select --</option>
					<form:options items="${heightUnitsRefData}" itemLabel="desc"
							itemValue="code"/>
				</form:select>
				</div>
		</div>

		<div class="row">
	        <div class="medical-label">Weight (kg)</div>
			<div class="value"> <form:input path="aeReport.participantHistory.weight" /> &nbsp;
				<form:select path="aeReport.participantHistory.weightUnitOfMeasure">
					<option value="please select --">please select --</option>
					<form:options items="${weightUnitsRefData}" itemLabel="desc"
							itemValue="code"/>
				</form:select>
			</div>
		</div>

		<div class="row">
	        <div class="medical-label">Baseline performance status</div>
			<div class="value"> <form:input path="aeReport.participantHistory.baselinePerformanceStatus" /> </div>
		</div>

		</chrome:division>

		<chrome:division title="Patient Disease Information">
        		
		<div class="row">
			<div class="medical-label">Select Disease</div>
			<div class="value"> 			
				<select id="disease" name="disease" onChange="javascript:chooseDisease();">
					<option value=-1>please select--</option>
					<c:forEach var="studyDisease" varStatus="status" items="${command.study.studyDiseases}">	
							<option value=${studyDisease.diseaseTerm.term}>${studyDisease.diseaseTerm.term} </option>
					</c:forEach>
				</select>
		    </div>
        </div> 

		<p id="instructions">
            If appropriate Disease is not in the list above then provide appropriate Disease
        </p>
		<div class="row">
	        <div class="medical-label"> Disease Name</div>
			<div class="value"> <input id="dis" type="text"/> </div>
		</div>

		<div class="row">
			<div class="medical-label">Select Disease Site</div>
			<div class="value"> 			
				<select id="disease_site" name="disease_site" onChange="javascript:chooseDiseaseSite();">
					<option value=-1>please select--</option>
					<option value=Ankle>Ankle</option>
					<option value=Ears>Ears</option>
					<option value=Legs>Legs</option>
					<option value=Hand>Hand</option>
				</select>
		    </div>
        </div> 

		<p id="instructions">
            If appropriate Disease Site is not in the list above then provide appropriate Disease Site
        </p>

		<div class="row">
	        <div class="medical-label"> Disease Site </div>
			<div class="value"> <input id="dis_site" type="text"/> </div>
		</div>

		<div class="row">
	        <div class="medical-label"> Initial Diagnosis date </div>
			<div class="value">  </div>
		</div>

		</chrome:division>
    </form:form>
</body>
</html>
