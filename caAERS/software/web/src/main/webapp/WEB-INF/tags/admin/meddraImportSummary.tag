<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<chrome:division id="meddra-id">
	<p id="instructions">
		"${command.meddraVersionName}" <caaers:message code='LBL_Meddra_Success'/> <br>	
	</p>
</chrome:division>