<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE,createStudy"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var EnterConMed = Class.create()
        Object.extend(EnterConMed.prototype, {
            initialize: function(index, agentName) {
                this.index = index
                var cmProperty = "aeReport.concomitantMedications[" + index + "]";
                this.agentProperty = cmProperty + ".agent"
                this.otherProperty = cmProperty + ".other"

                if (agentName) $(this.agentProperty + "-input").value = agentName

                $("select-agent-" + this.index)
                    .observe("click", this.updateAgentOrOther.bind(this))
                $("select-other-" + this.index)
                    .observe("click", this.updateAgentOrOther.bind(this))

                AE.createStandardAutocompleter(
                    this.agentProperty, this.termPopulator.bind(this),
                    function(agent) { return agent.name })

                this.initializeAgentOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createStudy.matchAgents(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },

            updateAgentOrOther: function() {
                var isAgent = $("select-agent-" + this.index).checked
                var agentRow = $(this.agentProperty + "-row")
                var otherRow = $(this.otherProperty + "-row")
                if (isAgent) {
                    agentRow.removeClassName("disabled")
                    otherRow.addClassName("disabled")
                    agentRow.getElementsByClassName("value")[0].enableDescendants()
                    otherRow.getElementsByClassName("value")[0].disableDescendants()
                } else {
                    otherRow.removeClassName("disabled")
                    agentRow.addClassName("disabled")
                    otherRow.getElementsByClassName("value")[0].enableDescendants()
                    agentRow.getElementsByClassName("value")[0].disableDescendants()
                }
            },

            initializeAgentOrOther: function() {
                var otherValue = $(this.otherProperty).value
                if (otherValue.length == 0) {
                    $("select-agent-" + this.index).click()
                } else {
                    $("select-other-" + this.index).click()
                }
            }
        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.concomitantMedications}" varStatus="status" var="conMed">
            new EnterConMed(${status.index}, '${conMed.agent.name}')
            </c:forEach>

            new ListEditor("conmed", createAE, "ConcomitantMedication", {
                addFirstAfter: "instructions",
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterConMed(index);
                }
            })
        })
    </script>
</head>
<body>
<form:form cssClass="standard">
        <p id="instructions">
            You are entering concomitant medications for ${command.assignment.participant.fullName}
            on ${command.assignment.studySite.study.shortTitle}.  For each medication, if there's
            an appropriate known agent, enter that.  Otherwise, enter a description in the other
            field.
        </p>
        <tags:hasErrorsMessage/>

        <tags:tabFields tab="${tab}"/>
        <c:forEach items="${command.aeReport.concomitantMedications}" varStatus="status">
            <ae:oneConMed index="${status.index}"/>
        </c:forEach>
        <input type="button" value="Add a medication" id="add-conmed-button"/>
        <tags:indicator id="add-conmed-indicator"/>
    </form:form>
</body>
</html>