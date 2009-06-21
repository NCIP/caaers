<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="search" type="gov.nih.nci.cabig.caaers.domain.Search" required="true" description="The dependent object for which the criteria row is being added" %>
<tr>
	<td align="left" width="80%">
		<a href="<c:url value="/pages/search/advancedSearch?searchName=${search.name }"/>">${search.name }</a>
	</td>
	<td align="right" width="20%">
		<img src="<c:url value="/images/checkno.gif" />" id="delete-search-${index}" onClick="javascript:advancedSearchHelper.deleteSavedSearch('${search.name }');"/>
	</td>
</tr>