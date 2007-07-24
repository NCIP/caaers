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

        var EnterPriorTherapy = Class.create()
        Object.extend(EnterPriorTherapy.prototype, {
            initialize: function(index, priorTherapyName) {
                this.index = index
                var cmProperty = "aeReport.adverseEventPriorTherapies[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".priorTherapy"
                this.otherProperty = cmProperty + ".other"

                if (priorTherapyName) $(this.priorTherapyProperty + "-input").value = priorTherapyName

                $("select-priorTherapy-" + this.index)
                    .observe("click", this.updatePriorTherapyOrOther.bind(this))
                $("select-other-" + this.index)
                    .observe("click", this.updatePriorTherapyOrOther.bind(this))

                AE.createStandardAutocompleter(
                    this.priorTherapyProperty, this.termPopulator.bind(this),
                    function(priorTherapy) {  return priorTherapy.text }, {
                        afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                        	 $(this.priorTherapyProperty).value = selectedChoice.id
                            this.showAddAgent(selectedChoice,index)
                        }.bind(this)
                    })
                    
				this.showAddAgent( { id: $(this.priorTherapyProperty).value },index) 
                this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchPriorTherapies(text, function(values) {
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
            
             showAddAgent: function(selectedChoice,index) {
             	//console.debug(selectedChoice)
             	//console.debug(index)
             	var id = selectedChoice.id;
             	var addAgentsTo=new Array("3","4","5","7","8","11")
                if (addAgentsTo.indexOf(id) !=  -1){
                	// show Add agents button
                	$('pptAgent'+index).style.display="";
                }
                else{
                	// hide Add agents button
                	$('pptAgent'+index).style.display="none";
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
        
        
         var EnterPriorTherapyAgent = Class.create()
        Object.extend(EnterPriorTherapyAgent.prototype, {
            initialize: function(index, parentIndex, priorTherapyName) {
                this.index = index
                this.parentIndex = parentIndex
                var cmProperty = "aeReport.adverseEventPriorTherapies[" + parentIndex + "].priorTherapyAgents[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".agent"

                if (priorTherapyName) $(this.priorTherapyProperty + "-input").value = priorTherapyName

                AE.createStandardAutocompleter(
                    this.priorTherapyProperty, this.termPopulator.bind(this),
                    function(agent) { return agent.name })
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchAgents(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },

          
        })
        
        
        function hello(id, parentIndex){
        	new ListEditor(id, createAE, "PriorTherapyAgent", {
                addFirstAfter: "priorTherapy-"+parentIndex,
                addParameters: [parentIndex , aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapyAgent(index,parentIndex)	
                }
            })
        
        }

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.adverseEventPriorTherapies}" varStatus="status" var="aePriorTherapy">
            new EnterPriorTherapy(${status.index}, '${aePriorTherapy.priorTherapy.text}')
            hello("ptAgent${status.index}",${status.index})
            	<c:forEach items="${aePriorTherapy.priorTherapyAgents}" varStatus="s" var="priorTherapyAgent">
            		new EnterPriorTherapyAgent(${s.index},${status.index},'${priorTherapyAgent.agent.name}')	
            	</c:forEach>
            </c:forEach>

            new PriorTherapyListEditor("priorTherapy", createAE, "PriorTherapy", {
                addFirstAfter: "single-fields",
                addAfter: "ptAgent" ,
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapy(index);
                    AE.registerCalendarPopups("priorTherapy-" + index);
                    hello("ptAgent"+index,index);
                    
                }
            })
            
            
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section7priortherapies">
        <jsp:attribute name="instructions">
            If applicable, enter a Prior Therapy for the Primary Disease for ${command.assignment.participant.fullName}
            on ${command.assignment.studySite.study.shortTitle}. Note: If the therapy you need is not listed, select 
            Prior Therapy, NOS and enter the therapy in Other .
        </jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.adverseEventPriorTherapies}" varStatus="status" var="aePriorTherapy">
            <ae:onePriorTherapy index="${status.index}"/>
            
            <c:forEach items="${aePriorTherapy.priorTherapyAgents}" varStatus="s" var="priorTherapyAgent">
            	<ae:onePriorTherapyAgent index="${s.index}" parentIndex="${status.index}"/>
            </c:forEach>
            
                    
        </c:forEach>    
        </jsp:attribute>
        
         <jsp:attribute name="localButtons">
         <tags:listEditorAddButton divisionClass="priorTherapy" label="Add a Therapy"/>
        <%--<input type="button" value="Add a Therapy" id="add-priorTherapy-button"/>
        <tags:indicator id="add-priorTherapy-indicator"/> --%>
         </jsp:attribute>
    </tags:tabForm>
</body>
</html>

