<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="parentIndex" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:nestedFieldGroupDivision fieldGroupFactoryName="ptAgent${parentIndex}" index="${index}" parentIndex="${parentIndex}"  style="${style}">
     	
     	
     	<div class="row" id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}]-row" >
    		<div class="label">
        		Agent
    		</div>
    
    		<div class="value">
    		
    		 <input size="50" type="text" id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-input"/>
			 <img id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-indicator" class="indicator" src="/caaers/images/indicator.white.gif" alt="activity indicator"/>
        
        	 <div id="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent-choices" class="autocomplete" style="display: none"></div>
       
       		 <form:hidden path="aeReport.adverseEventPriorTherapies[${parentIndex}].priorTherapyAgents[${index}].agent"/>
   
			</div>
     	</div>
</ae:nestedFieldGroupDivision>

