<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
<study:treatmentAssignment title="Treatment Assignment ${index+1}" 	sectionClass="si-section" index="${index}" 
					identifier="${command.treatmentAssignments[index]}" style="display:none" />
	

</tags:noform>
