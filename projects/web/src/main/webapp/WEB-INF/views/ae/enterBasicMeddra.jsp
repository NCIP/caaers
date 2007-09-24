<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Enter basic AE information</title>
    <tags:stylesheetLink name="ae"/>
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 20em;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
    </style>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
     <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var LowLevelTerm = Class.create()
        Object.extend(LowLevelTerm.prototype, {
            initialize: function(index, lowLevelTermCode) {
                this.index = index
                var cmProperty = "aeReport.adverseEvents[" + index + "]";
                this.lowLevelTermProperty = cmProperty + ".adverseEventMeddraLowLevelTerm.lowLevelTerm"
                this.otherProperty = cmProperty + ".other"

                if (lowLevelTermCode) $(this.lowLevelTermProperty + "-input").value = lowLevelTermCode

      
                AE.createStandardAutocompleter(
                    this.lowLevelTermProperty, this.termPopulator.bind(this),
                    function(lowLevelTerm) { return lowLevelTerm.fullName })

                //this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchLowLevelTermsByCode(text, function(values) {
                    autocompleter.setChoices(values)
                })
            }

        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status" var="aeLowLevelTerm">
            new LowLevelTerm(${status.index}, '${aeLowLevelTerm.adverseEventMeddraLowLevelTerm.lowLevelTerm.fullName}')
            </c:forEach>
            
             new ListEditor("ae-section", createAE, "AdverseEventMeddra", {
                addParameters: [aeReportId],
                addCallback: function(nextIndex) {
                    new LowLevelTerm(nextIndex);
                }
             })    

        }) 
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enterbasicaeinformation">
        <jsp:attribute name="instructions">
           <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <div class="report-fields">
                <div class="row">
                    <div class="label">MedDRA Version</div>
                    <div class="value">
                    	 ${command.assignment.studySite.study.terminology.meddraVersion.name}
                    </div>
                </div>
            </div>
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status">
                <ae:oneAdverseEventMeddra index="${status.index}"/>
            </c:forEach>
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="ae-section" label="Add another AE"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>