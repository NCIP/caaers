<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        /* TODO: all these are temporary */
        div.entry {
            float: left;
            width: 45%;
            margin: 1em;
            padding: 0;
            border: 1px solid #30f;
        }

        div.entry h2 {
            background-color: #30f;
            color: #fff;
            padding: 6px;
            margin: 0;
        }

        div.entry .content {
            padding: 0.5em;
        }
    </style>
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
                return obj.fullName
            }
        }

        var studyAutocompleterProps = {
            basename: "study",
            populator: function(autocompleter, text) {
                createAE.matchStudies(text, $('participant').value, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.shortTitle
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
                }
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
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            Element.update("flow-next", "Continue &raquo;")
        })
    </script>
    </head>
    <body>
        <p id="instructions">
            In order to create or edit an AE or SAE, you need to first select a participant and a
            study. You may start with either one. Once you have selected one, the options
            for the other will be automatically constrained. (Try it and see.)
        </p>

        <form:form method="post" cssClass="autoclear">
            <tags:tabFields tab="${tab}"/>
            <div>
                <div id="participant-entry" class="entry">
                    <h2>Select participant</h2>
                    <div class="content">
                        <p>Enter a portion of a participant's name or another registered identifier.</p>
                        <form:hidden path="participant"/>
                        <input type="text" id="participant-input" value="${command.participant.fullName}"/>
                        <input type="button" id="participant-clear" value="Clear"/>
                        <div id="participant-choices" class="autocomplete"></div>
                        <p id="participant-selected" style="display: none">
                            You've selected the participant <span id="participant-selected-name"></span>.
                        </p>
                        <form:errors path="participant"/>
                    </div>
                </div>
                <div id="study-entry" class="entry">
                    <h2>Select study</h2>
                    <div class="content">
                        <p>Enter a portion of a study's name or another registered identifier.</p>
                        <form:hidden path="study"/>
                        <input type="text" id="study-input" value="${command.study.shortTitle}"/>
                        <input type="button" id="study-clear" value="Clear"/>
                        <div id="study-choices" class="autocomplete"></div>
                        <p id="study-selected" style="display: none">
                            You've selected the study <span id="study-selected-name"></span>.
                        </p>
                        <form:errors path="study"/>
                    </div>
                </div>
            </div>
        </form:form>
    </body>
</html>