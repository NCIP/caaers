<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="blue" tagdir="/WEB-INF/tags/chrome" %>
<html>
  <head>
  </head>
  <body>
	
	<blue:box id="assignment.general" title="General" collapsable="true">
	</blue:box>
	<blue:box id="assignment.diseaseHistory" title="Disease Information" collapsable="true">
	</blue:box>
	<blue:box id="assignment.diseaseHistory.metastaticDiseaseSites" title="Metastatic Disease Site" collapsable="true">
	</blue:box>
	<blue:box id="assignment.preExistingConditions" title="Pre-existing Conditions" collapsable="true">
	</blue:box>
	<blue:box id="assignment.concomitantMedications" title="ConMeds" collapsable="true">
	</blue:box>
	<blue:box id="assignment.priorTherapies" title="Prior Therapies" collapsable="true">
	</blue:box>
  </body>
</html>