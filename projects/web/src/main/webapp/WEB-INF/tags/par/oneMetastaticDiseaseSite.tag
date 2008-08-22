<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>
<div>
		<table width="100%">
 			<tr>
  				<td width="99%">
					<ui:autocompleter path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].codedSite" readonly="true" 
						displayNamePath="assignment.diseaseHistory.metastaticDiseaseSites[${index}].codedSite.name" />
					<c:if test="${anatomicSite.id eq 110}">
					<%-- Other, Specify--%>
					<ui:text path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].otherSite" required="true"/>
					</c:if>
  				</td>
  				<td>
					<a href="#anchorMetastaticDiseases" onClick="mHistory.removeDetails('metastaticDiseaseSite', ${index}, 'anchorMetastaticDiseases')">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
</div>