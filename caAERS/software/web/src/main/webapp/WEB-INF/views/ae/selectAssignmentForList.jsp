<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${pageTitle}</title>
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

        #criteria-div{
        
        }
        
    </style>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">

    	AE.checkForModification = false; 
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

        function acPostSelect(mode, selectedChoice) {
            Element.removeClassName($(mode.basename + "-input"), "required");
            Element.addClassName($(mode.basename + "-input"), "validField");
            
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected");

            $(mode.basename + "-input").onblur = function() {
                if ($(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setNormalState($(mode.basename + "-input"))
                };
            }
            $(mode.basename + "-input").onchange = function() {
                if (!$(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setInvalidState($(mode.basename + "-input"));
                }
            }
            
        }

        function updateSelectedDisplay(mode) {
            if ($(mode.basename).value) {
                Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
                $(mode.basename + '-selected').show()
            }
        }

        function acCreate(mode) {
            new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices", mode.populator, {
                valueSelector: mode.valueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    acPostSelect(mode, selectedChoice)
                },
                indicator: mode.basename + "-indicator",
                minChars : AE.autocompleterChars , 
                frequency : AE.autocompleterDelay
            })
            Event.observe(mode.basename + "-clear", "click", function() {
                Element.removeClassName($(mode.basename + "-input"), "valueOK");
                Element.removeClassName($(mode.basename + "-input"), "validField");
                Element.addClassName($(mode.basename + "-input"), "required");
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
            })
        }

        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps)
            acCreate(studyAutocompleterProps)
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            initSearchField()
        })
    </script>
</head>
<body>
<form:form method="post" cssClass="standard autoclear">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
    <tags:tabFields tab="${tab}"/>

    <div class="autoclear" id="criteria-div">
    	<chrome:box title="Select study and subject" id="study-entry" autopad="true" cssClass="pairedLong">

            <%--<p><tags:instructions code="instruction_ae_assignment"/></p>--%>
            <div class="row">
                <div class="label"><tags:requiredIndicator/><caaers:message code="LBL_Study" /></div>
                <div class="value">
                    <%--<p><tags:instructions code="instruction_ae_select_study"/></p>--%>
                    <form:hidden path="study"/>
                    <input type="text" id="study-input" value="${command.study.shortTitle}" class="autocomplete"/>
                    <a id="study-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
                    <tags:indicator id="study-indicator"/>
                    <tags:errors path="study"/>
                    <div id="study-choices" class="autocomplete"></div>
                </div>
                <p id="study-selected" style="display: none">You have selected the study <span id="study-selected-name"></span>.</p>
            </div>

            <div class="row">
                <%--<p><tags:instructions code="instruction_ae_select_subject"/></p>--%>
                <div class="label"><tags:requiredIndicator/><caaers:message code="LBL_Subject" /></div>
                <div class="value">
                    <form:hidden path="participant"/>
                    <input type="text" id="participant-input" value="${command.participant.fullName}" class="autocomplete"/>
                    <a id="participant-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
                    <tags:indicator id="participant-indicator"/>
                    <div id="participant-choices" class="autocomplete"></div>
                    <tags:errors path="participant"/>
                </div>
                <p id="participant-selected" style="display: none">You have selected the subject <span id="participant-selected-name"></span>.</p>
            </div>
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
