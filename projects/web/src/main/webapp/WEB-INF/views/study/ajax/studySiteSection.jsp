<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
    <study:aStudyChild index="${index}" title="Study Site ${index + 1}" enableDelete="${index > 0}"
		   sectionClass="ss-section" removeButtonAction="removeSite" style="display: none"/>
</tags:noform>