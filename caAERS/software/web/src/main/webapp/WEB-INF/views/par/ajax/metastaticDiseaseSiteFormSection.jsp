<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<tags:noform>
<c:forEach items="${indexes}" var="index">
	<c:set var="mSite" value="${metastaticDiseaseSites[index]}" />
	<par:oneMetastaticDiseaseSite index="${index}" anatomicSite="${mSite.codedSite}" />
</c:forEach>
</tags:noform>