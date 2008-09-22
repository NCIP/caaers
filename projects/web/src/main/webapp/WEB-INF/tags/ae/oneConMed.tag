<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="concomitantMedication" type="gov.nih.nci.cabig.caaers.domain.ConcomitantMedication" required="true" %>
<c:set var="mainGroup">conmed${index}</c:set>
<chrome:division  id="aeReport.concomitantMedications[${index}]" collapsable="true" collapsed="${collapsed}"
 enableDelete="true" deleteParams="'concomitantMedication' ,${index}, 'anchorConcomitantMedication', {}">
		<jsp:attribute name="titleFragment">
			${concomitantMedication.agentName}
			<c:if test="${empty concomitantMedication.agentName}">
				<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}" />
			</c:if>
		</jsp:attribute>
		<jsp:body>
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="lpStatus" begin="1">
		<tags:renderRow field="${field}" />
	</c:forEach>
	<script>
	 function initializeConMed_${index}(){
		 if($('aeReport.concomitantMedications[${index}].stillTakingMedications')){
			 $('aeReport.concomitantMedications[${index}].stillTakingMedications').observe('click', function(evt){
				 	//the code to clear end-date and make it a readonly field.
				 	var edYrField = $('aeReport.concomitantMedications[${index}].endDate.yearString')
					if(edYrField){

						var edDdField = $('aeReport.concomitantMedications[${index}].endDate.dayString');
						var edMmField = $('aeReport.concomitantMedications[${index}].endDate.monthString');
						if(evt.element().value){
							edYrField.value = '';
							edDdField.value = '';
							edMmField.value = '';
							edYrField.readOnly = true;
							edDdField.readOnly = true;
							edMmField.readOnly =  true;
						}else{
							edYrField.readOnly = false;
							edDdField.readOnly = false;
							edMmField.readOnly =  false;
						}
						
					}
			  });	
			 AE.registerCalendarPopups();
		 }

	 }
	 initializeConMed_${index}.defer();
	</script>
		
		</jsp:body>
</chrome:division>
<%--
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="conmed" index="${index}" style="${style}">
    <tags:errors path="aeReport.concomitantMedications[${index}]"/>
	<c:forEach items="${fieldGroup.fields}" var="field">
	<tags:renderRow field="${field}" />
	</c:forEach>
    
</ae:fieldGroupDivision>
--%>