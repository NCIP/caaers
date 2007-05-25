<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="authorRule"/>
    <title>Select Rule Level</title>

    <script type="text/javascript">

      Event.observe(window, "load", function(){
				new Autocompleter.DWR("study-input", "study-choices",
                studyPopulator, {
                valueSelector: studyValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									postSelect(selectedChoice)
                },
                indicator: "study-indicator"});
				
				Event.observe("study-clear", "click", function() {
		                $("categoryIdentifier").value = ""
		                $("study-input").value = ""
		            })				
			});
	

      Event.observe(window, "load", function(){
				new Autocompleter.DWR("sponsor-input", "sponsor-choices",
                sponsorPopulator, {
                valueSelector: sponsorValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									setSponsor(selectedChoice)
                },
                indicator: "sponsor-indicator"});

				Event.observe("sponsor-clear", "click", function() {
		                $("sponsorName").value = ""
		                $("sponsor-input").value = ""
		            })				
			});
	
			function postSelect(selectedChoice) 
			{
				$("categoryIdentifier").value = selectedChoice.shortTitle;
			}


			function setSponsor(selectedChoice) 
			{
				$("sponsorName").value = selectedChoice;
			}

			function studyValueSelector(study) {
				return study.shortTitle;
			}

			function studyPopulator(autocompleter, text) 
			{
					authorRule.matchStudies(text, $('sponsor-input').value, function(values) {
							autocompleter.setChoices(values)
					})
			}
			
			function sponsorPopulator(autocompleter, text) 
			{
					authorRule.matchSponsors(text, function(values) {
							autocompleter.setChoices(values)
					})
			}

			function sponsorValueSelector(sponsor) 
			{
				return sponsor;
			}

			function displaySponsorOrStudyInput(level)
			{
				if (level.value == 'Sponsor')
				{
					//document.getElementById("sponsor-details").setAttribute("style", "visibility: visible")
					//document.getElementById("study-details").setAttribute("style", "visibility: hidden")					
					Effect.Appear("sponsor-details");
					Effect.Fade("study-details");
				}
				else
				{
					if (level.value == 'Study')
					{
						//document.getElementById("sponsor-details").setAttribute("style", "visibility: visible")
						//document.getElementById("study-details").setAttribute("style", "visibility: visible")					
						Effect.Appear("sponsor-details");
						Effect.Appear("study-details");
					}
					else
					{
						//document.getElementById("sponsor-details").setAttribute("style", "visibility: hidden")
						//document.getElementById("study-details").setAttribute("style", "visibility: hidden")					
						Effect.Fade("sponsor-details");
						Effect.Fade("study-details");
					}
				}
			}

            

	</script>

</head>

<body onload="nextTab();">

    <p id="instructions">
        You are creating Rules. You can create one or more rules at Sponsor, Institution or Study level.   
    </p>

    <chrome:division>

    <tags:tabForm tab="${tab}" flow="${flow}" >
	<jsp:attribute name="singleFields">

            <tags:errors path="*"/>
            

            <div class="row">
                <label><form:radiobutton path="level" value="Sponsor" onchange="displaySponsorOrStudyInput(this)"/>Create Rules at <b>Sponsor</b> Level</label>
            </div>

            <div class="row">
                <label><form:radiobutton path="level" value="Institution" onchange="displaySponsorOrStudyInput(this)"/>Create Rules at <b>Institution</b> Level</label>
            </div>
            
            <div class="row">
                <label><form:radiobutton path="level" value="Study" onchange="displaySponsorOrStudyInput(this)"/>Create Rules at <b>Study</b> Level</label>
            </div>            

			<c:choose>
				<c:when test='${command.level eq "Study"}'>
					<c:set var="sponsorVisibility" value=""/>
					<c:set var="studyVisibility" value=""/>
				</c:when>
				<c:when test='${command.level eq "Sponsor"}'>
					<c:set var="sponsorVisibility" value=""/>
					<c:set var="studyVisibility" value="display: none"/>
				</c:when>
				<c:otherwise>
					<c:set var="sponsorVisibility" value="display: none"/>
					<c:set var="studyVisibility" value="display: none"/>
				</c:otherwise>
			</c:choose>
	<div title="Select Sponsor" id="sponsor-details" style="${sponsorVisibility}"> 
			<div class="row">
				<div class="label"><label for="sponsor-input">Select Sponsor</label></div>
				<div class="value">
					<form:hidden path="sponsorName"/>
					<input type="text" id="sponsor-input" value="${command.sponsorName}" size="40"/>
                    			<input type="button" id="sponsor-clear" value="Clear"/>					
					<tags:indicator id="sponsor-indicator"/>
				</div>
			</div>
			<div id="sponsor-choices" class="autocomplete"></div>
	</div>	

	<div title="Select Study" id="study-details" style="${studyVisibility}">
			<div class="row">
				<div class="label"><label for="study-input">Select Study</label></div>
				<div class="value">
					<form:hidden path="categoryIdentifier"/>
					<input type="text" id="study-input" value="${command.categoryIdentifier}" size="40"/>
					<input type="button" id="study-clear" value="Clear"/>
					<tags:indicator id="study-indicator"/>
				</div>
			</div>
			<div id="study-choices" class="autocomplete"></div>
	</div>

	</jsp:attribute>



</tags:tabForm> 

</chrome:division>


</body>
</html>