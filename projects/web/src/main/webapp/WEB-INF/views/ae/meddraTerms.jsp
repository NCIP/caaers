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
   
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
     <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var LowLevelTerm = Class.create()
        Object.extend(LowLevelTerm.prototype, {
            initialize: function(index, lowLevelTermCode) {
                this.index = index
                var cmProperty = "aeRoutineReport.adverseEvents[" + index + "]";
                this.lowLevelTermProperty = cmProperty + ".adverseEventMeddraLowLevelTerm.lowLevelTerm"
                //this.otherProperty = cmProperty + ".other"

                if (lowLevelTermCode) $(this.lowLevelTermProperty + "-input").value = lowLevelTermCode

      
                AE.createStandardAutocompleter(
                    this.lowLevelTermProperty, this.termPopulator.bind(this),
                    function(lowLevelTerm) { return lowLevelTerm.meddraCode })

                //this.initializePriorTherapyOrOther()
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchLowLevelTermsByCode(text, function(values) {
                    autocompleter.setChoices(values)
                })
            }

        })

        Element.observe(window, "load", function() {
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" varStatus="status" var="aeLowLevelTerm">
            new LowLevelTerm(${status.index}, '${aeLowLevelTerm.adverseEventMeddraLowLevelTerm.lowLevelTerm.meddraCode}')
            </c:forEach>
            
             new ListEditor("ae-section", createAE, "RoutineAeMeddra", {
             	addFirstAfter: "koi",
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
            You are entering an adverse event report for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <div class="report-fields">
                <c:forEach items="${fieldGroups.report.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
            </div>
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
        	<table id="test" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Term:</b> </th>
    				<th ><b> <span class="red">*</span><em></em>Grade:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Attribution:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Hospitalization:</b> </th>
    				<th scope="col" align="left"><b> <span class="red">*</span><em></em>Expected:</b> </th>
    			</tr>
    			<tr id="koi" />
    			</table>
            	<c:forEach items="${command.aeRoutineReport.adverseEvents}" varStatus="status">
                	<ae:oneMeddraTerm index="${status.index}"/>
            	</c:forEach>
            	
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="ae-section" label="Add another AE"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>