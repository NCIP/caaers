<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <tags:dwrJavascriptLink objects="authorRule"/>

    <script type="text/javascript">
        var triggerLevel;


      Event.observe(window, "load", function() {
      	// Event.observe("trigger-level", "change", function() { triggerLevelSelector(false) });

				new Autocompleter.DWR("study-input", "study-choices",
                studyPopulator, {
                valueSelector: studyValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									postSelect(selectedChoice)
                },
                indicator: "study-indicator",
                minChars : AE.autocompleterChars , 
                frequency : AE.autocompleterDelay});
				
				
			});
			
			function postSelect(selectedChoice) {
				// Invoked when user selects the study.
				$("categoryIdentifier").value = selectedChoice.shortTitle;
				// Fetch the study details
//				alert("Going to fetch the details of this study");
			}


			function studyValueSelector(study) {
				return study.shortTitle;
			}

			function studyPopulator(autocompleter, text) {
					authorRule.matchStudies(text, null, function(values) {
							autocompleter.setChoices(values)
					})
			}

			function triggerLevelSelector(atLoad) {
					triggerLevel = $F("trigger-level");
					if (triggerLevel) {
							if (atLoad) {
									$("study-details").show();
							} else {
									AE.slideAndShow("study-details");
							}
					} else {
							AE.slideAndHide("study-details");
					}
					
			}
			

	</script>

  <title>Specify Study Details</title>
</head>
<body>
    <p id="instructions">
        Please select the Study. The rules defined will be applied for the study you select here. </p>

    <chrome:division title="Select Study">

        <%--<form:form cssClass="standard">--%>
    <tags:tabForm tab="${tab}" flow="${flow}" >
	<jsp:attribute name="singleFields">


            <tags:errors path="*"/>
    
            <tags:tabFields tab="${tab}"/>

            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.ruleset.fields}" var="field">
                    <ruleTags:row field="${field}"/>
                </c:forEach>
            </div>

            <div id="study-details">
							<div class="row">
									<div class="label"><label for="study-input">Select Study</label></div>
									<div class="value">
											<form:hidden path="categoryIdentifier"/>
											<input type="text" id="study-input" />
											<tags:indicator id="study-indicator"/>
									</div>
							</div>
							<div id="study-choices" class="autocomplete"></div>
						
            </div>

            <div class="row" style="display: none">
                <div class="label"><label for="trigger-level">Trigger Level</label></div>
                <div class="value">
                    <select id="trigger-level">
                        <option value="">Please select --</option>
                        <c:forEach items="${triggerLevels}" var="category">
                            <option value="${category.metaData.name}">${category.metaData.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            
<%--        </form:form>--%>
</jsp:attribute>
</tags:tabForm> 

		</chrome:division>

</body>
</html>