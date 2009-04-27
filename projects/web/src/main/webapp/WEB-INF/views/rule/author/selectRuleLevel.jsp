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
			
      Event.observe(window, "load", function(){
				new Autocompleter.DWR("institution-input", "institution-choices",
                institutionPopulator, {
                valueSelector: institutionValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									setInstitution(selectedChoice)
                },
                indicator: "institution-indicator"});

				Event.observe("institution-clear", "click", function() {
		                $("institutionName").value = ""
		                $("institution-input").value = ""
		            })				
			});			
	
	// ....
	
			function postSelect(selectedChoice) 
			{
				$("categoryIdentifier").value = selectedChoice.shortTitle;
			}


			function setSponsor(selectedChoice) 
			{
				$("sponsorName").value = selectedChoice;
				
				
			}
			
			function setInstitution(selectedChoice) 
			{
				
				$("institutionName").value = selectedChoice.name;

			}

			function studyValueSelector(study) {
				return "(" + study.primaryIdentifierValue + ") "+study.shortTitle;
			}

			function studyPopulator(autocompleter, text) 
			{
					
					var institutionInput =  $('institution-input').value;
					if(institutionInput != '') {
						authorRule.matchStudiesByInstitution(text, $('institution-input').value, function(values) {
							autocompleter.setChoices(values)
						})
					}
					
					var sponsorInput =  $('sponsor-input').value;
					if(sponsorInput != '') {
						
						authorRule.matchStudies(text, $('sponsor-input').value, function(values) {
							autocompleter.setChoices(values)
						})
					}
			}
			
			function sponsorPopulator(autocompleter, text) 
			{
					authorRule.matchSponsors(text, function(values) {
							autocompleter.setChoices(values)
					})
			}

			function institutionPopulator(autocompleter, text) 
			{
					authorRule.matchSites(text, function(values) {
							autocompleter.setChoices(values)
					})
			}
			
			function sponsorValueSelector(sponsor) 
			{
				return sponsor;
			}

			function institutionValueSelector(site) 
			{
				
				return site.name;
			}
			
			function displayRuleTypeInput(level)
			{
					 $('sponsor-input').value = '';
					 $('institution-input').value = '';
					 $('study-input').value	 = '';
				
				
				if (level.value == 'Sponsor')
				{
					Effect.Appear("sponsor-details");
					Effect.Fade("study-details");
					Effect.Fade("institution-details");
				}
				

				if (level.value == 'SponsorDefinedStudy')
					{
						Effect.Appear("sponsor-details");
						Effect.Appear("study-details");
						Effect.Fade("institution-details");
				}

				if (level.value == 'Institution')
						{
							
							Effect.Appear("institution-details");
							Effect.Fade("study-details");
							Effect.Fade("sponsor-details");
							
				}

				if (level.value == 'InstitutionDefinedStudy')
						{
							
							Effect.Appear("institution-details");
							Effect.Appear("study-details");
							Effect.Fade("sponsor-details");
							
				}

			}
            

	</script>

</head>

<body onLoad="nextTab();">

    <p>
         <tags:instructions code="rulelevel" />  
    </p>

    <chrome:division >

    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false" >
	<jsp:attribute name="singleFields">

            <tags:errors path="*"/>
            

         <table border="0">
            <tr>
            <td valign="top">
                
            <div class="row">
                <label><form:radiobutton path="level" value="Sponsor" onchange="displayRuleTypeInput(this)"/>&nbsp;Sponsor rules</label>
            </div>

            <div class="row">
                <label><form:radiobutton path="level" value="Institution" onchange="displayRuleTypeInput(this)"/>&nbsp;Institution rules</label>
            </div>
            
            <div class="row">
                <label><form:radiobutton path="level" value="SponsorDefinedStudy" onchange="displayRuleTypeInput(this)"/>&nbsp;Sponsor defined rules for a study</label>
            </div>            

            <div class="row">
                <label><form:radiobutton path="level" value="InstitutionDefinedStudy" onchange="displayRuleTypeInput(this)"/>&nbsp;Institution defined rules for a study</label>
            </div>  

            </td>
       <td valign="top">
                       
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
	<div>		
	<div title="Select Sponsor" id="sponsor-details" style="${sponsorVisibility}" class="pane"> 
			<div class="row">
				<div class="label"><label for="sponsor-input">Select Sponsor</label></div>
				<div class="value">
					<form:hidden path="sponsorName"/>
					<input type="text" id="sponsor-input" value="${command.sponsorName}" size="30"/>
                    			<input type="button" id="sponsor-clear" value="Clear"/>					
					<tags:indicator id="sponsor-indicator"/>
				</div>
			</div>
			<div id="sponsor-choices" class="autocomplete"></div>
	</div>	



	<div title="Select Institution" id="institution-details" style="${studyVisibility}" class="pane">
			<div class="row">
				<div class="label"><label for="institution-input">Select Instiution</label></div>
				<div class="value">
					<form:hidden path="institutionName"/>
					<input type="text" id="institution-input" value="${command.institutionName}" size="30"/>
					<input type="button" id="institution-clear" value="Clear"/>
					<tags:indicator id="institution-indicator"/>
				</div>
			</div>
			<div id="institution-choices" class="autocomplete"></div>
	</div>

	<div title="Select Study" id="study-details" style="${studyVisibility}" class="pane">
			<div class="row">
				<div class="label"><label for="study-input">Select Study</label></div>
				<div class="value">
					<form:hidden path="categoryIdentifier"/>
					<input type="text" id="study-input" value="${command.categoryIdentifier}" size="30"/>
					<input type="button" id="study-clear" value="Clear"/>
					<tags:indicator id="study-indicator"/>
				</div>
			</div>
			<div id="study-choices" class="autocomplete"></div>
	</div>
	
	</div>


                                </td>
		                </tr>
            </table>
    </jsp:attribute>



</tags:tabForm> 

</chrome:division>


</body>
</html>