<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="investigator" tagdir="/WEB-INF/tags/investigator"%>
<tags:noform>

	<investigator:siteInvestigator 	title="Associated Sites ${index + 1}" enableDelete="${index > 0}"
						sectionClass="site-investigator-row"
						removeButtonAction="removeInvestigator" index="${index}" />
				
			
</tags:noform>

