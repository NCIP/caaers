<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
    <study:aStudyChild title="Study Identifier ${index + 1}" enableDelete="${index > 0}"
			sectionClass="si-section" removeButtonAction="removeIdentifier" index="${index}" style="display: none" />
</tags:noform>