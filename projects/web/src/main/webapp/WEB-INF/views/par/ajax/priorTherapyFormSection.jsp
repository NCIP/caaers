<%@taglib prefix="par" tagdir="/WEB-INF/tags/par"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<chrome:box title="Prior Therapy Details">
<form:form>
  <par:onePriorTherapy index="${index}" />
  <hr />	
  <div>
	<input type="button" value="Add" onclick="mHistory.savePopup('command', 'priorTherapy')" />
  </div>
</form:form>
</chrome:box>
