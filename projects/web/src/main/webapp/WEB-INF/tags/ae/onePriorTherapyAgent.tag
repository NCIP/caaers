<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="parentIndex" type="java.lang.Integer" %>
<%@attribute name="style"%>

<div class="ptAgent${parentIndex}" id="ptAgent${parentIndex}-${index}" <tags:attribute name="style" value="${style}"/> >
    <div class="row" id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}]-row" >
        <div class="label">
            Agent
        </div>

        <div class="value">

            <input size="50" type="text" id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-input"/>
            <tags:indicator id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-indicator"/>

            <div id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-choices" class="autocomplete" style="display: none"></div>

            <form:hidden path="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent"/>

        </div>
    </div>
</div>

