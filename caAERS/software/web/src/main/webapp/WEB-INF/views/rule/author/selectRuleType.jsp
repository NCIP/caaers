<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
	<head>
		<style type="text/css">
        	input.autocomplete {
            	width: 450px;
            	font-style: normal;
            	background-color: #CCE6FF;
        	}

        	input.pending-search {
            	width: 450px;
            	color: gray;
            	font-style: italic;
            	background-color: #CCE6FF;
        	}
	    </style>
	
    	<tags:dwrJavascriptLink objects="authorRule"/>
    	<title>Select Rule Level</title>

	    <script type="text/javascript">
	    	
			function setRuleSetName(ruleSetElement)
    		{
	    		$("hiddenRuleSetName").value=ruleSetElement.options[ruleSetElement.selectedIndex].value;    		
    		}
    		
    		function displayRuleTypeInput(level)
			{
				$("level").value = level.value;
				
				if(level.value == 'Please select a Rule level')
				{
					$("level").value = '';
					Effect.Fade("sponsorName-details");
					Effect.Fade("institutionName-details");
					Effect.Fade("categoryIdentifier-details");
				}
					 
				if (level.value == 'Sponsor')
				{
					Effect.Appear("sponsorName-details");
					Effect.Fade("categoryIdentifier-details");
					Effect.Fade("institutionName-details");
				}
				
				if (level.value == 'SponsorDefinedStudy')
				{
					Effect.Appear("sponsorName-details");
					Effect.Appear("categoryIdentifier-details");
					Effect.Fade("institutionName-details");
				}

				if (level.value == 'Institution')
				{
					Effect.Appear("institutionName-details");
					Effect.Fade("categoryIdentifier-details");
					Effect.Fade("sponsorName-details");
				}

				if (level.value == 'InstitutionDefinedStudy')
				{
					Effect.Appear("institutionName-details");
					Effect.Appear("categoryIdentifier-details");
					Effect.Fade("sponsorName-details");
				}	 
			}
			
			function initializeSelectElements(){
				if(${initializeLevelSelect}){
					var sel = $('ruleLevel');
					for(var i = 0; i < sel.options.length; i++){
						if(sel.options[i].value == '${initialLevel}'){
							sel.selectedIndex = i;
						}
					}
				}
				if(${initializeRuleSetNameSelect}){
					var sel = $('ruleSetName');
					for(var i = 0; i < sel.options.length; i++){
						if(sel.options[i].value == '${initialRuleSet}')
							sel.selectedIndex = i;
					}
				}
			}
			
			Event.observe(window, "load", function(){
				initSearchField();
				initializeSelectElements();
				displayRuleTypeInput($('ruleLevel'));
				
				//remove the query string from form url
	    		removeQueryStringFromForm('command');	            				
			});
    		
    	</script>
	</head>
	<body onLoad="nextTab();">
		<tags:tabForm tab="${tab}" flow="${flow}" willSave="false" title="Select Rule Type">
    		<jsp:attribute name="singleFields">
    			<tags:instructions code="ruletype" /> 
    			<div class="row"  id="ruleSetDiv">
            			<div class="label"><tags:requiredIndicator/>&nbsp;<label for="ruleSetName">Type</label></div>
            			<div class="value">
                			<select id="ruleSetName" onChange="setRuleSetName(this)">
                    			<option value="">Please select</option>
				                <option value="SAE Reporting Rules">SAE Reporting Rules </option>
                				<option value="Mandatory Sections Rules">Mandatory Sections Rules</option>
				            </select>
				            <tags:errors path="ruleSetName"/>
        				</div>
        			</div>
				
    				<div class="row"  id="ruleLevelDiv">
            			<div class="label"><tags:requiredIndicator/>&nbsp;<label for="ruleLevelName">Level</label></div>
            			<div class="value">
            				<form:hidden path="level"/>
                			<select id="ruleLevel" onChange="displayRuleTypeInput(this)" value="${level }">
                    			<option value="Please select a Rule level">Please select</option>
				                <option value="Sponsor">Sponsor rules </option>
                				<option value="Institution">Institution rules</option>
                				<option value="SponsorDefinedStudy">Sponsor defined rules for a study</option>
                				<option value="InstitutionDefinedStudy">Institution defined rules for a study</option>
				            </select>
				            <tags:errors path="level"/>
        				</div>
        			</div>
        			
        			
        			<div class="autoclear" id="criteria-div">		
						
						<div title="Select sponsor" id="sponsorName-details" style="display:none">
							<form:hidden path="sponsorNameInitialValue"/>
                            <ui:row path="sponsorName">
                                <jsp:attribute name="label">
                                    <ui:label required="true" text="Sponsor" path="sponsorName"/>
                                </jsp:attribute>
                                <jsp:attribute name="value">
                                    <ui:autocompleter path="sponsorName" enableClearButton="true" required="false" title="Sponsor"
                                    	initialDisplayValue="${empty command.sponsorNameInitialValue ? 'Begin typing here...' : command.sponsorNameInitialValue}">
                                    <jsp:attribute name="populatorJS">
                                    	function(autocompleter, text) {
                							authorRule.matchOrganization(text, function(values) {
												autocompleter.setChoices(values)
											})
            							}
                                    </jsp:attribute>
                                    <jsp:attribute name="selectorJS">
                                    	function(organization) {
            								var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
						                	return organization.name + nciInstituteCode;
            							}
                                    </jsp:attribute>
                                    <jsp:attribute name="optionsJS">
                                    	{
                                    		afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                                    			$("sponsorName").value = selectedChoice.name;
                                    			var nciInstituteCode = selectedChoice.nciInstituteCode == null ? "" : " ( " + selectedChoice.nciInstituteCode + " ) ";
                                    			$("sponsorNameInitialValue").value = selectedChoice.name + nciInstituteCode;
                                    		}
                                    	}
                                    </jsp:attribute>
                                    </ui:autocompleter>
                                </jsp:attribute>
                            </ui:row>
						</div>
						
						<div title="Select institution" id="institutionName-details" style="display:none" class="pane">
							<form:hidden path="institutionNameInitialValue"/>
							<ui:row path="institutionName">
                                <jsp:attribute name="label">
                                    <ui:label required="true" text="Institution" path="institutionName"/>
                                </jsp:attribute>
                                <jsp:attribute name="value">
                                    <ui:autocompleter path="institutionName" enableClearButton="true" required="false" title="Institution"
                                    	initialDisplayValue="${empty command.institutionNameInitialValue ? 'Begin typing here...' : command.institutionNameInitialValue}">
                                    <jsp:attribute name="populatorJS">
                                    	function(autocompleter, text) {
        									authorRule.matchSites(text, function(values) {
												autocompleter.setChoices(values)
        									})
        								}
                                    </jsp:attribute>
                                    <jsp:attribute name="selectorJS">
                                    	function(organization) {
	        								var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
        									return organization.name + nciInstituteCode;
        								}
                                    </jsp:attribute>
                                    <jsp:attribute name="optionsJS">
                                    	{
                                    		afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                                    			$("institutionName").value = selectedChoice.name;
                                    			var nciInstituteCode = selectedChoice.nciInstituteCode == null ? "" : " ( " + selectedChoice.nciInstituteCode + " ) ";
                                    			$("institutionNameInitialValue").value = selectedChoice.name + nciInstituteCode;
                                    		}
                                    	}
                                    </jsp:attribute>
                                    </ui:autocompleter>
                                </jsp:attribute>
                            </ui:row>
						</div>

						<div title="Select study" id="categoryIdentifier-details" style="display:none" class="pane">
							<ui:row path="categoryIdentifier">
                                <jsp:attribute name="label">
                                    <ui:label required="true" text="Study" path="categoryIdentifier"/>
                                </jsp:attribute>
                                <jsp:attribute name="value">
                                    <ui:autocompleter path="categoryIdentifier" enableClearButton="true" required="false" title="Study"
                                    	initialDisplayValue="${empty command.categoryIdentifier ? 'Begin typing here...' : command.categoryIdentifier}">
                                    <jsp:attribute name="populatorJS">
                                    	function(autocompleter, text){
						        			var institutionNameInput =  $('institutionName').value;
											if(institutionNameInput != '') {
												authorRule.matchStudiesByInstitution(text, $('institutionName').value, function(values) {
													autocompleter.setChoices(values)
												})
											}
											var sponsorNameInput =  $('sponsorName').value;
											if(sponsorNameInput != '') {
												authorRule.matchStudies(text, $('sponsorName').value, function(values) {
													autocompleter.setChoices(values)
												})
											}
        								}
                                    </jsp:attribute>
                                    <jsp:attribute name="selectorJS">
                                    	function(study){
        									if(study.primaryIdentifierValue) return "(" + study.primaryIdentifierValue + ") "+study.shortTitle;
											return study.shortTitle;
        								}
                                    </jsp:attribute>
                                    <jsp:attribute name="optionsJS">
                                    	{
                                    		afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                                    			$("categoryIdentifier").value = selectedChoice.shortTitle;
                                    		}
                                    	}
                                    </jsp:attribute>
                                    </ui:autocompleter>
                                </jsp:attribute>
                            </ui:row>
						</div>
					</div>
					
					
    			<form:hidden id="hiddenRuleSetName" path="ruleSetName"/>
    		</jsp:attribute>
    	</tags:tabForm>
	</body>
</html>