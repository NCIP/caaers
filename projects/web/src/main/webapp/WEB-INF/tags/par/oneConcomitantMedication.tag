<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="collapsed" required="true" description="Tells whether to display collapsed"%>
<%@attribute name="concomitantMedication" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication" required="true" %>

<c:set var="mainGroup">conmed${index}</c:set>

<chrome:division title="${not empty concomitantMedication.agentName ? concomitantMedication.agentName : 'new ConMed '}" id="assignment.concomitantMedications[${index}]" collapsable="true" collapsed="${collapsed}" enableDelete="true" deleteParams="'concomitantMedication' ,${index}, 'anchorConcomitantMedication', {}">

    <table border="0" width="100%">
        <tr>
            <td>
                        <ui:row path="assignment.concomitantMedications[${index}].agentName">
                            <jsp:attribute name="label">
                                <ui:label path="assignment.concomitantMedications[${index}].agentName" text="Medication name" />
                            </jsp:attribute>
                            <jsp:attribute name="value">
                                <ui:text path="assignment.concomitantMedications[${index}].agentName" />
                            </jsp:attribute>
                        </ui:row>
                            
                        <ui:row path="assignment.concomitantMedications[${index}].startDate">
                            <jsp:attribute name="label">
                                <ui:label path="assignment.concomitantMedications[${index}].startDate" text="Start date" />
                            </jsp:attribute>
                            <jsp:attribute name="value">
                                <ui:splitDate path="assignment.concomitantMedications[${index}].startDate" />
                            </jsp:attribute>
                        </ui:row>
            <td>
                        <ui:row path="assignment.concomitantMedications[${index}].stillTakingMedications">
                            <jsp:attribute name="label">
                                <ui:label path="assignment.concomitantMedications[${index}].stillTakingMedications" text="Still taking ?" />
                            </jsp:attribute>
                            <jsp:attribute name="value">
                                <ui:checkbox path="assignment.concomitantMedications[${index}].stillTakingMedications" />
                            </jsp:attribute>
                        </ui:row>


                        <ui:row path="assignment.concomitantMedications[${index}].endDate">
                            <jsp:attribute name="label">
                                <ui:label path="assignment.concomitantMedications[${index}].endDate" text="End date" />
                            </jsp:attribute>
                            <jsp:attribute name="value">
                                <ui:splitDate path="assignment.concomitantMedications[${index}].endDate" />
                            </jsp:attribute>
                        </ui:row>

    </table>

	<script>
	 function initializeConMed_${index}(){
		 $('assignment.concomitantMedications[${index}].stillTakingMedications').observe('click', function(evt){
				//the code to clear end-date and make it a readonly field.
				
			 	
			 	var edYrField = $('assignment.concomitantMedications[${index}].endDate.year')
				if(edYrField){

					var edDdField = $('assignment.concomitantMedications[${index}].endDate.day');
					var edMmField = $('assignment.concomitantMedications[${index}].endDate.month');
					if(evt.element().value){
						edYrField.value = '';
						edDdField.value = '';
						edMmField.value = '';
						edYrField.readonly = true;
						edDdField.readonly = true;
						edMmField.value =  true;
					}else{
						edYrField.readonly = false;
						edDdField.readonly = false;
						edMmField.value =  false;
					}
					
				}

				
		  });	
		 AE.registerCalendarPopups();
	 }
	 initializeConMed_${index}.defer();
	</script>
</chrome:division>