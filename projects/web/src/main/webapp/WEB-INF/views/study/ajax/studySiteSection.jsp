<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
  <study:oneStudyChildRow cssClass="ss-section" index="${index}" disableDelete="${index < 1}" style="display: none" />
</tags:noform>