<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageTitle}</title>
    <style type="text/css">
        input.autocomplete {
            width: 75%;
            font-style: normal;
            background-color: #CCE6FF;
        }

        input.pending-search {
            width: 75%;
            color: gray;
            font-style: italic;
            background-color: #CCE6FF;
        }

    </style>
   
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:dwrJavascriptLink objects="createStudy"/>
    <script type="text/javascript">
        var participantAutocompleterProps = {
            basename: "participant",
            populator: function(autocompleter, text) {
                createAE.matchParticipants(text, $('study').value, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.displayName
            }
        }

        var studyAutocompleterProps = {
            basename: "study",
            populator: function(autocompleter, text) {
                createAE.matchStudies(text, $('participant').value, ${command.ignoreCompletedStudy}, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.displayName;
            }
        }
        
        var siteAutocompleterProps = {
        	basename: "studySite",
        	populator: function(autocompleter, text){
        		createAE.matchSites(text, $('study').value, function(values) {
        			autocompleter.setChoices(values)
        		})
        	},
        	valueSelector: function(obj) {
        		return obj.displayName;
        	}
        }

        function acPostSelect(mode, selectedChoice) {
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected")
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
            })
        }
/*
        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps)
            acCreate(studyAutocompleterProps)
            acCreate(siteAutocompleterProps)
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            updateSelectedDisplay(siteAutocompleterProps)
            initSearchField()
        })
*/  
	</script>
</head>
<body>

<tags:standardForm title="Choose Search Criteria">
	<jsp:attribute name="instructions"><tags:instructions code="instruction_ae_assignment"/></jsp:attribute>
	<jsp:attribute name="singleFields">
			<ui:row path="participant">
				<jsp:attribute name="label">Subject
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:autocompleter path="participant"  initialDisplayValue="${empty command.participant  ? 'Begin typing here...' : command.participant.fullName}" 
						enableClearButton="true" size="50">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text) {
                					createAE.matchParticipants(text, $('study').value, function(values) {
                    					autocompleter.setChoices(values)
                					})
           						}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(obj) {
                					return obj.displayName
            					}
							</jsp:attribute>
						</ui:autocompleter>
				</jsp:attribute>
			</ui:row>
			<ui:row path="Study">
				<jsp:attribute name="label">Study
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:autocompleter path="study"  initialDisplayValue="${empty command.study  ? 'Begin typing here...' : command.study.shortTitle}" enableClearButton="true" size="50">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text) {
                					createAE.matchStudies(text, $('participant').value, ${command.ignoreCompletedStudy}, function(values) {
                    					autocompleter.setChoices(values)
                					})
            					}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(obj) {
               						 return obj.displayName;
            					}
							</jsp:attribute>
					</ui:autocompleter>	
				</jsp:attribute>
			</ui:row>
			<ui:row path="Study site">
				<jsp:attribute name="label">Study site
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:autocompleter path="studySite"  initialDisplayValue="${empty command.studySite  ? 'Begin typing here...' : command.studySite.organization.fullName}" enableClearButton="true" size="50">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text){
        							createStudy.matchSites(text, $('study').value, function(values) {
        								autocompleter.setChoices(values)
        							})
        						}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(obj) {
        							return obj.name;
        						}
							</jsp:attribute>
					</ui:autocompleter>			
				</jsp:attribute>
			</ui:row>
			<ui:row path="Review status">
				<jsp:attribute name="label">Review status
				</jsp:attribute>
				<jsp:attribute name="value">
				<ui:select options="${command.reviewStatusOptionsMap}" path="reviewStatus"></ui:select>
				</jsp:attribute>
			</ui:row>
    <c:choose>
        <c:when test="${empty tab}">
            <tags:tabControls tabNumber="${0}" isLast="${false}" willSave="${false}"/>
        </c:when>
        <c:otherwise>
            <tags:tabControls tab="${tab}" flow="${flow}" willSave="${false}"/>
        </c:otherwise>
    </c:choose>

	</jsp:attribute>
</tags:standardForm>

</body>
</html>