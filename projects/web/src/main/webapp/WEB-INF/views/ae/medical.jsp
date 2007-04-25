<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>

	<style type="text/css">
        .instructions { margin-left: 17.5em; }
    </style>

    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
		
		function chooseDisease(){
				var term = document.getElementById('aeReport.diseaseHistory.studyDisease').value;
				if(term == -1)
				{
					document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseCode').disabled=false;
				}
				else {
					document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseCode').disabled=true;
				}
				
		}

        var anatomicAutocompleterProps = {
            basename: "aeReport.diseaseHistory.anatomicSite",
            populator: function(autocompleter, text) {
                createAE.matchAnatomicSite(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.name
            }
        }

        function acPostSelect(mode, selectedChoice) {
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected")
			document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode').disabled=true;
        }

        function updateSelectedDisplay(mode) {
            if ($(mode.basename).value) {
                Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
                $(mode.basename + '-selected').show()
            } 
			
        }

        function acCreate(mode) {
            new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
                mode.populator, {
                valueSelector: mode.valueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    acPostSelect(mode, selectedChoice)
                },
                indicator: mode.basename + "-indicator"
            })
            Event.observe(mode.basename + "-clear", "click", function() {
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
				document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode').disabled=false;
            })
        }

        Event.observe(window, "load", function() {
            acCreate(anatomicAutocompleterProps)
            updateSelectedDisplay(anatomicAutocompleterProps)
            // Element.update("flow-next", "Continue &raquo;")
        })

    </script>
</head>
<body>
    <form:form cssClass="standard">
        
        <tags:hasErrorsMessage/>

        <tags:tabFields tab="${tab}"/>
		
		<chrome:division title="Patient Demographic Information">
		
		<div class="row"> 
	        <div class="medical-label">Patient ID</div>
			<div class="text"> ${command.participant.id} </div>
		</div> 

		<div class="row">
	        <div class="medical-label">Birth Date</div>
			<div class="text"><tags:formatDate value="${command.participant.dateOfBirth}"/></div>
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
	        <div class="medical-label">Height</div>
			<div class="value"> <input path="aeReport.participantHistory.height"/> &nbsp;

				<form:select path="aeReport.participantHistory.heightUnitOfMeasure">
					<form:options items="${heightUnitsRefData}" itemLabel="desc"
							itemValue="code"/>
				</form:select>
				</div>
		</div>

		<div class="row">
	        <div class="medical-label">Weight</div>
			<div class="value"> <input path="aeReport.participantHistory.weight"/> &nbsp;
				<form:select path="aeReport.participantHistory.weightUnitOfMeasure">
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
			<div class="medical-label">Disease Name</div>
			<div class="value"> 		
			
				<%-- <form:select path="aeReport.diseaseHistory.studyDisease">
					<form:option value="" label="Please Select--"/>
					<form:options items="${command.study.studyDiseases}" itemValue="id" itemLabel="diseaseTerm.term"/>
				</form:select> --%>

				<%-- <form:select path="aeReport.diseaseHistory.studyDisease" onChange="javascript:chooseDisease();">
					<option value=-1>please select--</option>
					<c:forEach var="studyDisease" varStatus="status" items="${command.study.studyDiseases}">	
							<option value=${studyDisease.id}>${studyDisease.diseaseTerm.term} </option>
					</c:forEach> 
				</form:select> --%>
				<select id="aeReport.diseaseHistory.studyDisease" name="aeReport.diseaseHistory.studyDisease" onChange="javascript:chooseDisease();">
					<option value=-1>please select--</option>
					<c:forEach var="studyDisease" varStatus="status" items="${command.study.studyDiseases}">	
							<option value=${studyDisease.id}>${studyDisease.diseaseTerm.term} </option>
					</c:forEach>
				</select>
		    </div>
        </div> 

		<p id="instructions" class="instructions">
			If appropriate Disease Name is not on the list above, provide appropriate Disease Name in the "Disease Name Not Listed" field.</p>
		<div class="row">
	        <div class="medical-label">Disease Name Not Listed</div>
			<div class="value"> <form:input size="38" path="aeReport.diseaseHistory.otherPrimaryDiseaseCode"/> </div>
		</div>

		<div class="row">
			<div class="medical-label">Primary Site of Disease</div>
			<div class="value"> 			
					<form:hidden path="aeReport.diseaseHistory.anatomicSite"/>
					<input type="text" size="38" id="aeReport.diseaseHistory.anatomicSite-input" value="${command.aeReport.diseaseHistory.anatomicSite.name}"/>
					<input type="button" id="aeReport.diseaseHistory.anatomicSite-clear" value="Clear"/>
                    <tags:indicator id="aeReport.diseaseHistory.anatomicSite-indicator"/>     
					<tags:errors path="aeReport.diseaseHistory.anatomicSite"/>
                    <div id="aeReport.diseaseHistory.anatomicSite-choices" class="autocomplete"></div>
					<p id="aeReport.diseaseHistory.anatomicSite-selected" style="display:none">
                        You've selected the anatomic <span id="aeReport.diseaseHistory.anatomicSite-selected-name"></span>.
                    </p>
		    </div>
        </div> 

		<p id="instructions" class="instructions">
				If the appropriate site is not listed, type the specific site in the "Other Primary Site of Disease" field.
		</p>

		<div class="row">
	        <div class="medical-label">Other Primary Site of Disease</div>
			<div class="value"> <form:input size="38" path="aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode"/> </div>
		</div>

		<div class="row">
	        <div class="medical-label">Date of initial diagnosis</div>
			<div class="value"> <tags:dateInput path="aeReport.diseaseHistory.dateOfInitialPathologicDiagnosis"/> </div>
		</div>

		</chrome:division>
    </form:form>
</body>
</html>
