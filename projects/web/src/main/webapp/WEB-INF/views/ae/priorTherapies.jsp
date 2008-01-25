<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
     <style type="text/css">
   		div.row div.label { width: 14em; } 
		div.row div.value, div.row div.extra { margin-left: 15em; }
	</style>  
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE,createStudy"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var EnterPriorTherapy = Class.create()
        Object.extend(EnterPriorTherapy.prototype, {
            initialize: function(index, priorTherapyName) {
                this.index = index
                var cmProperty = "aeReport.saeReportPriorTherapies[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".priorTherapy"
                this.otherProperty = cmProperty + ".other"
				
                $(this.priorTherapyProperty).observe("change",this.updateAgentView.bind(this))
                this.updateAgentView()
            },
            
            updateAgentView: function() {
                var id = $F(this.priorTherapyProperty);
                var addAgentsTo = new Array("3", "4", "5", "7", "8", "11")
                if (addAgentsTo.indexOf(id) != -1) {
                    // show Add agents button
                    $('pptAgent' + this.index).style.display = "";
                }
                else {
                    // hide Add agents button
                    $('pptAgent' + this.index).style.display = "none";
                }

            }
        })


        var EnterPriorTherapyAgent = Class.create()
        Object.extend(EnterPriorTherapyAgent.prototype, {
            initialize: function(index, parentIndex, priorTherapyName) {
                this.index = index
                this.parentIndex = parentIndex
                var cmProperty = "aeReport.saeReportPriorTherapies[" + parentIndex + "].priorTherapyAgents[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".chemoAgent"

                if (priorTherapyName) $(this.priorTherapyProperty + "-input").value = priorTherapyName

                AE.createStandardAutocompleter(
                    this.priorTherapyProperty, this.termPopulator.bind(this),
                    function(agent) {
                        return agent.name
                    })
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchChemoAgents(text, function(values) {
                    autocompleter.setChoices(values)
                })
            }

        })


        function createAgentListEditor(id, parentIndex) {
            new ListEditor(id, createAE, "PriorTherapyAgent", {
                addFirstAfter: "aeReport.saeReportPriorTherapies[" + parentIndex + "].endDate-row",
                addParameters: [parentIndex , aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapyAgent(index, parentIndex)
                },
                deletable: true
            },'aeReport.saeReportPriorTherapies[' + parentIndex + '].priorTherapyAgents')
        }

        Element.observe(window, "load", function() {
        <c:forEach items="${command.aeReport.saeReportPriorTherapies}" varStatus="status" var="aePriorTherapy">
            new EnterPriorTherapy(${status.index}, '${aePriorTherapy.priorTherapy.text}')
            createAgentListEditor("ptAgent${status.index}",${status.index})
            	<c:forEach items="${aePriorTherapy.priorTherapyAgents}" varStatus="s" var="priorTherapyAgent">
            		new EnterPriorTherapyAgent(${s.index},${status.index},'${priorTherapyAgent.chemoAgent.name}')	
            	</c:forEach>
            </c:forEach>

            new ListEditor("priorTherapy", createAE, "PriorTherapy", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapy(index);
                    AE.registerCalendarPopups("priorTherapy-" + index);
                    createAgentListEditor("ptAgent" + index, index);
                    //-- update help  
      				captureHelpControlEvents();
                },
                deletable: true
            }, 'aeReport.saeReportPriorTherapies')
        })
        
        function showChemoAgentsTable(tableId) {
               var parameterMap = getParameterMap('command');
               createAE.buildChemoAgentsTable(parameterMap,tableId,showPopUpTable);
               function showPopUpTable(table) {
                   var testDiv = document.getElementById(tableId);
                   testDiv.innerHTML = table;
                   testDiv.show();

               }

           }

        function fillChemoAgentAutoCompletor(chemoAgentId, tableId) {


            var div = document.getElementById(tableId)
            div.hide()
            createAE.getChemoAgentById(chemoAgentId, function(values) {

                var parentIndex = tableId.substring(tableId.indexOf('parent') + 6, tableId.indexOf('index'))
                var index = tableId.substring(tableId.indexOf('index') + 5, tableId.length)

                var autoCompleterFileId = 'aeReport.saeReportPriorTherapies[' + parentIndex + '].priorTherapyAgents[' + index + '].chemoAgent'
                var ctcTerm = document.getElementById(autoCompleterFileId + '-input')
                ctcTerm.value = values.name
                document.getElementById(autoCompleterFileId).value = chemoAgentId

            });
        }


    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section8priortherapies">
        <jsp:attribute name="instructions">
            <tags:instructions code="instruction_ae_priorTherapy" />
            <tags:instructions code="instruction_ae_priorThrerapyNote" heading="Note: " />
        </jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.saeReportPriorTherapies}" varStatus="status" var="aePriorTherapy">
            <ae:onePriorTherapy index="${status.index}" agentCount="${fn:length(aePriorTherapy.priorTherapyAgents)}"/>
        </c:forEach>
        </jsp:attribute>
        
        <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="priorTherapy" label="Add a Therapy Type" buttonCssClass="ae-list-editor-button"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>