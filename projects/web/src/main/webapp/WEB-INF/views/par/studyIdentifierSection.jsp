<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
    <study:studyIdentifier title="Study Identifier ${index + 1}" enableDelete="${index > 0}"
			sectionClass="si-section" removeButtonAction="removeIdentifier" index="${index}" style="display: none" identifier="${command.identifiers[index]}"/>
</tags:noform>
