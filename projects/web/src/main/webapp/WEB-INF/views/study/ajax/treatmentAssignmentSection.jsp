<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
<study:treatmentAssignment title="Treatment Assignment ${index+1}" enableDelete="${index > 0}" 
					sectionClass="si-section" removeButtonAction="removeTreatmentAssignment" index="${index}" identifier="${command.treatmentAssignments[index]}" />
	

</tags:noform>
