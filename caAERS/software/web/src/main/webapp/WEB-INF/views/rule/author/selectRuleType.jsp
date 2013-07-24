<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
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

	</head>
	<body>

    <script type="text/javascript">



        Event.observe(window, "load", function(){


            //remove the query string from form url
            removeQueryStringFromForm('command');

            hideRow('ruleLevelDiv');
            hideRow('sponsorName-details');
            hideRow('categoryIdentifier-details');
            var _rtName = $F('caaersRuleSet.ruleTypeName');

            if(_rtName == 'SAE Reporting Rules' || _rtName == 'Mandatory Sections Rules' ){
                showRow('ruleLevelDiv');
            }

            if(_rtName == 'Safety Signalling Rules') {
                showRow('categoryIdentifier-details')
            }

            var _rlName = $F('caaersRuleSet.ruleLevelName');
            if(_rlName){
                showRow('sponsorName-details');
                if(_rlName == 'InstitutionDefinedStudy'  || _rlName == 'SponsorDefinedStudy' )  {
                    showRow('categoryIdentifier-details')
                }

            }


        });

    </script>
		<tags:tabForm tab="${tab}" flow="${flow}" willSave="false" title="Select Rule Type">
    		<jsp:attribute name="singleFields">
    			<tags:instructions code="ruletype" /> 
    			<div class="row"  id="ruleSetDiv">
            			<div class="label"><tags:requiredIndicator/>&nbsp;<label for="caaersRuleSet.ruleTypeName">Type</label></div>
            			<div class="value">
                            <ui:select path="caaersRuleSet.ruleTypeName" options="${command.ruleTypeOptions}" cssClass="required">
                                <jsp:attribute name="embededJS">
                                    $('caaersRuleSet.ruleTypeName').observe('change', function(evt){
                                         var el = $(evt.element());
								         var optionText = el.options[el.selectedIndex].text;
                                        $('caaersRuleSet.ruleLevelName').selectedIndex = 0;
                                         AE.resetAutocompleter('caaersRuleSet.sponsor')
                                         AE.resetAutocompleter('caaersRuleSet.study')
                                         hideRow('ruleLevelDiv');
                                         hideRow('sponsorName-details');
                                         hideRow('categoryIdentifier-details');

                                         if(optionText == 'SAE Reporting Rules' || optionText == 'Mandatory Sections Rules' ){
                                             showRow('ruleLevelDiv');
                                         }

                                         //select the institution defined study
                                         if(optionText == 'Safety Signalling Rules') {
                                            $('caaersRuleSet.ruleLevelName').selectedIndex = 0;
                                            showRow('categoryIdentifier-details')
                                         }
                                    });
                                </jsp:attribute>
                            </ui:select>
        				</div>
        			</div>
				
    				<div class="row"  id="ruleLevelDiv" style="${empty command.caaersRuleSet.ruleLevelName ? 'display:none' : ''};">
            			<div class="label"><tags:requiredIndicator/>&nbsp;<label for="caaersRuleSet.ruleLevelName">Level</label></div>
            			<div class="value">
                            <ui:select path="caaersRuleSet.ruleLevelName" options="${command.ruleLevelOptions}">
                                <jsp:attribute name="embededJS">
                                      $('caaersRuleSet.ruleLevelName').observe('change', function(evt){
                                         var el = $(evt.element());
								         var optionText = el.options[el.selectedIndex].value;
                                         AE.resetAutocompleter('caaersRuleSet.sponsor')
                                         AE.resetAutocompleter('caaersRuleSet.study')
                                         hideRow('sponsorName-details');
                                         hideRow('categoryIdentifier-details');
                                         if(optionText){
                                            showRow('sponsorName-details');
                                            if(optionText == 'InstitutionDefinedStudy'  || optionText == 'SponsorDefinedStudy' )  {
                                                showRow('categoryIdentifier-details')
                                            }

                                         }
                                    });
                                </jsp:attribute>
                            </ui:select>

        				</div>
        			</div>

        			
        			<div class="autoclear" id="criteria-div">		

						<div title="Select sponsor" id="sponsorName-details"  style="${empty command.caaersRuleSet.ruleLevelName ? 'display:none' : ''}" >

                            <ui:row path="sponsor">
                                <jsp:attribute name="label">
                                    <ui:label required="true" text="Organization" path="caaersRuleSet.sponsor"/>
                                </jsp:attribute>
                                <jsp:attribute name="value">
                                    <ui:autocompleter path="caaersRuleSet.sponsor" enableClearButton="true" required="false" title="Sponsor"
                                                      initialDisplayValue="${empty command.caaersRuleSet.sponsor ? 'Begin typing here' : command.caaersRuleSet.sponsor.fullName}">

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

                                    </ui:autocompleter>
                                </jsp:attribute>
                            </ui:row>
						</div>


						<div title="Select study" id="categoryIdentifier-details" style="${ ( ('SponsorDefinedStudy' eq command.caaersRuleSet.ruleLevelName) or ('InstitutionDefinedStudy' eq command.caaersRuleSet.ruleLevelName)) ? '' : 'display:none'  } " class="pane">
							<ui:row path="caaersRuleSet.study">
                                <jsp:attribute name="label">
                                    <ui:label required="true" text="Study" path="caaersRuleSet.study"/>
                                </jsp:attribute>
                                <jsp:attribute name="value">

                                    <ui:autocompleter path="caaersRuleSet.study" enableClearButton="true" required="false" title="Study"
                                    	initialDisplayValue="${empty command.caaersRuleSet.study ? 'Begin typing here' : caaers:escapeJS(command.caaersRuleSet.study.shortTitle)}">
                                    <jsp:attribute name="populatorJS">
                                    	function(autocompleter, text){
                                           if($F('caaersRuleSet.ruleTypeName')  == 'Safety Signalling Rules'){
                                                authorRule.matchAllStudies(text, function(values) {
                                                        autocompleter.setChoices(values)
                                                    })
                                           } else {
                                               var sponsorId = $F('caaersRuleSet.sponsor');


                                               if('InstitutionDefinedStudy' == $F('caaersRuleSet.ruleLevelName')) {
                                                    authorRule.matchStudiesByInstitution(text, sponsorId, function(values) {
                                                        autocompleter.setChoices(values)
                                                    })
                                                } else  {
                                                    authorRule.matchStudies(text,sponsorId, function(values) {
                                                        autocompleter.setChoices(values)
                                                    })
                                                }
                                           }

        								}
                                    </jsp:attribute>
                                    <jsp:attribute name="selectorJS">
                                    	function(study){
        									if(study.primaryIdentifierValue) return "(" + study.primaryIdentifierValue + ") "+study.shortTitle;
											return study.shortTitle;
        								}
                                    </jsp:attribute>

                                    </ui:autocompleter>
                                </jsp:attribute>
                            </ui:row>
						</div>
					</div>
					
    		</jsp:attribute>
    	</tags:tabForm>
	</body>
</html>
