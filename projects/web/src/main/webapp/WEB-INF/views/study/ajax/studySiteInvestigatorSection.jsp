<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
    <study:aStudyChild title="Study Investigator ${index + 1}" style="display: none"
			    sectionClass="ssi-section" removeButtonAction="removeInv" enableDelete="true" index="${index}" />
    
</tags:noform>