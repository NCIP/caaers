<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="investigator" tagdir="/WEB-INF/tags/investigator"%>
<tags:noform>
	<investigator:siteInvestigator 	title="Associated Sites ${index + 1}"
						enableDelete="true"
						sectionClass="site-investigator-row"
						index="${index}" style=""/>
</tags:noform>
