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
                indicator: "study-indicator"});
				
				
			});
			
			function postSelect(selectedChoice) {
				// Invoked when user selects the study.
				
				// Fetch the study details
				alert("Going to fetch the details of this study");
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
<title>Specify Trigger Details</title>
</head>
<body>
<p>Feature in progress.....Creating Rule at Study level....</p>
<p id="instructions"> Please select the Study. The rules defined will be applied for the study you select here. </p>
<chrome:division title="Select Study">
  <form:form cssClass="standard">
    <tags:errors path="*"/>
    <tags:tabFields tab="${tab}"/>
    <div id="ruleset-fields">
      <c:forEach items="${fieldGroups.ruleset.fields}" var="field">
        <tags:row field="${field}"/>
      </c:forEach>
    </div>
    <div id="study-details">
      <div class="row">
        <div class="label">
          <label for="study-input">Select Study</label>
        </div>
        <div class="value">
          <input type="text" id="study-input" />
          <tags:indicator id="study-indicator"/>
        </div>
      </div>
      <div id="study-choices" class="autocomplete"></div>
    </div>
    <div class="row" style="display: none">
      <div class="label">
        <label for="trigger-level">Trigger Level</label>
      </div>
      <div class="value">
        <select id="trigger-level">
          <option value="">Please select --</option>
          <c:forEach items="${triggerLevels}" var="category">
            <option value="${category.metaData.name}">${category.metaData.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <c:if test="${empty tab}">
      <tags:tabControls tabNumber="${0}" isLast="${false}"/>
    </c:if>
  </form:form>
</chrome:division>
</body>
</html>
