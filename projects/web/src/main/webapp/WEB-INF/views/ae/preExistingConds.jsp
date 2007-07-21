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
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 20em;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
        
      	div.row div.label { width: 13em; }
    	div.row div.value { margin-left: 14em;}
    </style>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var EnterPriorTherapy = Class.create()
        Object.extend(EnterPriorTherapy.prototype, {
            initialize: function(index, preExisitingConditionName) {
                this.index = index
                var cmProperty = "aeReport.adverseEventPreExistingConds[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".preExistingCondition"
                this.otherProperty = cmProperty + ".other"

                if (preExisitingConditionName) $(this.priorTherapyProperty + "-input").value = preExisitingConditionName

                $("select-priorTherapy-" + this.index)
                    .observe("click", this.updatePriorTherapyOrOther.bind(this))
                $("select-other-" + this.index)
                    .observe("click", this.updatePriorTherapyOrOther.bind(this))

                AE.createStandardAutocompleter(
                    this.priorTherapyProperty, this.termPopulator.bind(this),
                    function(preExistingCondition) { return preExistingCondition.text })

                this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchPreExistingConds(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },

            updatePriorTherapyOrOther: function() {
                var isPriorTherapy = $("select-priorTherapy-" + this.index).checked
                var priorTherapyRow = $(this.priorTherapyProperty + "-row")
                var otherRow = $(this.otherProperty + "-row")
                if (isPriorTherapy) {
                    priorTherapyRow.removeClassName("disabled")
                    otherRow.addClassName("disabled")
                    priorTherapyRow.getElementsByClassName("value")[0].enableDescendants()
                    otherRow.getElementsByClassName("value")[0].disableDescendants()
                } else {
                    otherRow.removeClassName("disabled")
                    priorTherapyRow.addClassName("disabled")
                    otherRow.getElementsByClassName("value")[0].enableDescendants()
                    priorTherapyRow.getElementsByClassName("value")[0].disableDescendants()
                }
            },

            initializePriorTherapyOrOther: function() {
                var otherValue = $(this.otherProperty).value
                if (otherValue.length == 0) {
                    $("select-priorTherapy-" + this.index).click()
                } else {
                    $("select-priorTherapy-" + this.index).click()
                }
            }
        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.adverseEventPreExistingConds}" varStatus="status" var="aePreExistingCond">
            new EnterPriorTherapy(${status.index}, '${aePreExistingCond.preExistingCondition.text}')
            </c:forEach>

            new ListEditor("conmed", createAE, "PreExistingCond", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapy(index);
                }
            })
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            If applicable, enter a Pre-Existing Condition for ${command.assignment.participant.fullName}
            on ${command.assignment.studySite.study.shortTitle}. Note: If the condition you need is not listed, 
            enter the condition in Other .
        </jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.adverseEventPreExistingConds}" varStatus="status">
            <ae:onePreExistingCond index="${status.index}"/>
        </c:forEach>    
        </jsp:attribute>
        
         <jsp:attribute name="localButtons">
         <tags:listEditorAddButton divisionClass="conmed" label="Add a Pre-Existing Condition"/>
         </jsp:attribute>
    </tags:tabForm>
</body>
</html>
