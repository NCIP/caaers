<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="investigator" tagdir="/WEB-INF/tags/investigator"%>
<tags:noform>
	<investigator:siteInvestigator 	title="Associated Sites ${index + 1}"
						enableDelete="true"
						sectionClass="site-investigator-row"
						index="${index}" style=""/>
</tags:noform>