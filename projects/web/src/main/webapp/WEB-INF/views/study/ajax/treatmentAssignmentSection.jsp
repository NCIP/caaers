<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
<study:treatmentAssignment
        title="...."
        sectionClass="si-section"
        index="${index}"
		treatmentAssignment="${command.study.treatmentAssignments[index]}"
		readOnly="false"
        style="display:none" 
        collapsed="false"
         />
</tags:noform>
