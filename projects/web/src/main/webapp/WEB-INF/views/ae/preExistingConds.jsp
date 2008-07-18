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
    <tags:labs labs="${command.assignment.labViewerLabs}"/>
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
                var cmProperty = "aeReport.saeReportPreExistingConditions[" + index + "]";
                this.priorTherapyProperty = cmProperty + ".preExistingCondition"
                this.otherProperty = cmProperty + ".other"
                
                $(this.priorTherapyProperty).observe("change",this.updatePriorTherapyOther.bind(this))
                
                this.initializePriorTherapyOther()
            },
            
            updatePriorTherapyOther: function() {
               var isNOS = $(this.priorTherapyProperty).options[1].selected 
               if(isNOS){
               		$(this.priorTherapyProperty).options[1].selected=true
               		AE.slideAndShow(this.otherProperty + "-row")
               }else{
               		$(this.otherProperty).value="";
               		AE.slideAndHide(this.otherProperty + "-row")
               }
            },
            
            initializePriorTherapyOther: function() {
               var isNOS = ( $(this.otherProperty).value.length >0 )
               if(isNOS){
               		$(this.priorTherapyProperty).options[1].selected=true
               		AE.slideAndShow(this.otherProperty + "-row")
               }
            }
        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeReport.saeReportPreExistingConditions}" varStatus="status" var="aePreExistingCond">
            new EnterPriorTherapy(${status.index}, '${aePreExistingCond.preExistingCondition.text}')
            </c:forEach>

            new ListEditor("conmed", createAE, "PreExistingCond", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterPriorTherapy(index);

                    captureHelpControlEvents();
                },
                deletable: true
            }, 'aeReport.saeReportPreExistingConditions')
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section7preexistingconditions">
        <jsp:attribute name="instructions">
         <tags:instructions code="instruction_ae_preexistingCond" />
        </jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.saeReportPreExistingConditions}" varStatus="status">
            <ae:onePreExistingCond index="${status.index}"/>
        </c:forEach>    
        </jsp:attribute>
        
         <jsp:attribute name="localButtons">
         <tags:listEditorAddButton divisionClass="conmed" label="Add a Pre-Existing Condition" buttonCssClass="ae-list-editor-button"/>
         </jsp:attribute>
    </tags:tabForm>
</body>
</html>
