<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="parentIndex" type="java.lang.Integer" %>
<%@attribute name="style"%>


<chrome:division title="Agent ${index +1}" cssClass="ptAgent${parentIndex}" id="ptAgent${parentIndex}-${index}" style="${style}">

    <div class="row" id="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}]-row" >
        <div class="label">
            Agent
        </div>

        <div class="value">

            <input size="50" type="text" id="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent-input"/>
            <tags:indicator id="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent-indicator"/>

            <div id="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent-choices" class="autocomplete" style="display: none"></div>

            <a id="showAllChemoAgentparent${parentIndex}index${index}"
               href="javascript:showChemoAgentsTable('chemoAgentTableparent${parentIndex}index${index}')">Show
                All</a>

            <div id="chemoAgentTableparent${parentIndex}index${index}"
                 style="position: absolute; display: block; left: 640px; width:400px; z-index:99;">
            </div>


            <form:hidden path="aeReport.saeReportPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].chemoAgent"/>

        </div>
    </div>
</chrome:division>
	