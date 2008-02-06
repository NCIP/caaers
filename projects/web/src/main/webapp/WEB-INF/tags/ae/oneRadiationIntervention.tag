<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="radiationIntervention" index="${index}" style="${style}">
    <tags:errors path="aeReport.radiationInterventions[${index}]"/>
    
     <div class="row">
		<div class="label">
			Treatment Arm
		</div>
		<div class="value">
			${code}
		</div>
	</div>
	
	<div class="row">
		<div class="label">
			Treatment arm description
		</div>
		<div class="value">
			${description}
		</div>
	</div>
    
    
	 <c:forEach begin="2" end="${fn:length(fieldGroup.fields) - 1}" var="i">
        <tags:renderRow field="${fieldGroup.fields[i]}"/>
    </c:forEach>
    
</ae:fieldGroupDivision>
