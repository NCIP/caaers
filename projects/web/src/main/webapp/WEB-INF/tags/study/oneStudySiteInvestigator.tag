<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<div id="ss-section-0" class="row ss-section" style="${style}">
	<p id="instructions" align="left">
	   <p><tags:instructions code="study.study_investigator.1" /></p>.	
	<br />
	</p>
	<table width="100%" class="tablecontent" valign="top">
	  <tr id="ssi-table-head" class="ssi-table-head">
		<th width="55%" class="tableHeader"><tags:requiredIndicator />Investigator</th>
		<th width="20%" class="tableHeader"><tags:requiredIndicator />Role</th>
		<th width="20%" class="tableHeader"><tags:requiredIndicator />Status</th>
		<th width="5%" class="tableHeader" style=" background-color: none">&nbsp;</th>
 	  </tr>
	
	  <c:forEach var="si" items="${command.studyOrganizations[index].studyInvestigators}" varStatus="status">
	   <study:oneStudyChildRow cssClass="ssi-table-row" index="${status.index}" />
	   <script>new jsInvestigator(${status.index}, '${si.siteInvestigator.investigator.fullName}');</script>
	  </c:forEach>
	  <c:if  test="${fn:length(command.studyOrganizations[index].studyInvestigators) lt 1}">
	   <tr id="ssi-empty-row" class="ssi-empty-row"><td colspan="4">There are no investigators associated to this study site.</td></tr>
	  </c:if>
	</table>
</div>