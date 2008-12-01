<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
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
    <c:if test="${empty tab}">
        <tags:stylesheetLink name="tabbedflow"/>
        <tags:javascriptLink name="tabbedflow"/>
    </c:if>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
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

        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps)
            acCreate(studyAutocompleterProps)
            acCreate(siteAutocompleterProps)
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            updateSelectedDisplay(siteAutocompleterProps)
            initSearchField()
        })
    </script>
</head>
<body>
<p>
    <tags:instructions code="instruction_ae_assignment"/>
</p>
<form:form method="post" cssClass="standard autoclear">
    <tags:tabFields tab="${tab}"/>
    <div class="autoclear">
        <chrome:box title="Select subject" id="participant-entry" cssClass="paired" autopad="true">
            <p><tags:instructions code="instruction_ae_select_subject"/></p>
            <form:hidden path="participant"/>
            <tags:requiredIndicator/>
            <input type="text" id="participant-input" value="${command.participant.fullName}" class="autocomplete"/>
            <input type="button" id="participant-clear" value="Clear"/>
            <tags:indicator id="participant-indicator"/>
            <div id="participant-choices" class="autocomplete"></div>
            <tags:errors path="participant"/>
            <p id="participant-selected" style="display: none">
                You have selected the subject <span id="participant-selected-name"></span>.
            </p>
        </chrome:box>
        <chrome:box title="Select study" id="study-entry" cssClass="paired" autopad="true">
            <p><tags:instructions code="instruction_ae_select_study"/></p>
            <form:hidden path="study"/>
            <input type="text" id="study-input" value="${command.study.shortTitle}" class="autocomplete"/>
            <input type="button" id="study-clear" value="Clear"/>
            <tags:indicator id="study-indicator"/>
            <tags:errors path="study"/>
            <div id="study-choices" class="autocomplete"></div>
            <p id="study-selected" style="display: none">
                You have selected the study <span id="study-selected-name"></span>.
            </p>
        </chrome:box>
        <chrome:box title="Select Study Site" id="site-entry" cssClass="paired" autopad="true">
            <p><tags:instructions code="instruction_ae_select_study"/></p>
            <form:hidden path="studySite"/>
            <input type="text" id="site-input" value="${command.studySite}" class="autocomplete"/>
            <input type="button" id="site-clear" value="Clear"/>
            <tags:indicator id="site-indicator"/>
            <tags:errors path="study"/>
            <div id="site-choices" class="autocomplete"></div>
            <p id="site-selected" style="display: none">
                You have selected the site <span id="site-selected-name"></span>.
            </p>
        </chrome:box>
        <chrome:box title="Select Review Status" id="status-entry" cssClass="paired" autopad="true">
        	<p><tags:instructions code="instruction_ae_select_study"/></p>
        	<ui:select options="${reviewStatusOptions}" path="reviewStatus"></ui:select>
        </chrome:box>
    </div>
    <c:choose>
        <c:when test="${empty tab}">
            <tags:tabControls tabNumber="${0}" isLast="${false}" willSave="${false}"/>
        </c:when>
        <c:otherwise>
            <tags:tabControls tab="${tab}" flow="${flow}" willSave="${false}"/>
        </c:otherwise>
    </c:choose>
</form:form>
</body>
</html>