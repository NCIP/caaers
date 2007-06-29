<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        function switchModified(checkbox) {
            var index = checkbox.id.substring(18)
            var hasModified = checkbox.checked
            enableModified(index, hasModified)
            var baseProp = "aeReport.treatmentInformation.courseAgents[" + index + "]."
            if (hasModified) {
                $(baseProp + "modifiedDose.amount").value = ""
                $(baseProp + "modifiedDose.units").value = $(baseProp + "dose.units").value
                $(baseProp + "modifiedDose.route").value = $(baseProp + "dose.route").value
            } else {
                $A([
                    baseProp + "modifiedDose.amount",
                    baseProp + "modifiedDose.units",
                    baseProp + "modifiedDose.route"
                ]).each(function(input) {
                    $(input).value = "";
                })
            }
        }

        function enableModified(index, enable) {
            var baseProp = "aeReport.treatmentInformation.courseAgents[" + index + "]."
            $A([
                baseProp + "modifiedDose.amount",
                baseProp + "modifiedDose.units",
                baseProp + "modifiedDose.route"
            ]).each(function(input) {
                $(input).disabled = !enable;
            })
        }

        function switchModifiedHandler(event) {
            switchModified(Event.element(event))
        }

        function renableModified() {
            var courseAgentCount = $$(".courseAgent").size
            for (var i = 0 ; i < courseAgentCount ; i++) {
                enableModified(index, true)
            }
        }

        function registerDoseModHandler(index) {
            var checkbox = $('dose-mod-checkbox-' + index)
            checkbox.observe('click', switchModifiedHandler)
            enableModified(index, checkbox.checked)
        }

        Element.observe(window, "load", function() {
            new ListEditor("courseAgent", createAE, "CourseAgent", {
                addParameters: [aeReportId],
                addFirstAfter: "single-fields",
                addCallback: function(index) {
                    registerDoseModHandler(index)
                    AE.registerCalendarPopups("courseAgent-" + index)
                }
            })
            console.log($("command"))
            Event.observe("command", "submit", renableModified)
            $$('.dose-mod-checkbox').each(function(elt) {
                var index = elt.id.substring(18)
                registerDoseModHandler(index)
            })
        })
    </script>
    <style type="text/css">
        div.row div.label {
            width: 18em;
        }
        div.row div.value {
            margin-left: 19em;
        }
    </style>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            You are entering treatment information for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <tags:renderRow field="${fieldGroups.treatmentInfo.fields[3]}"/>
            <div class="row">
                <div class="label">
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[0]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[0]}"/>
                    (course number 1)
                </div>
            </div>
            <div class="row">
                <div class="label">
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[2]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[2]}"/>
                    course number
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[1]}" size="4"/>
                </div>
            </div>
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
            <c:forEach items="${command.aeReport.treatmentInformation.courseAgents}" varStatus="status">
                <ae:oneCourseAgent index="${status.index}"/>
            </c:forEach>
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="courseAgent" label="Add a course agent"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>