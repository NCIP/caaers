<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<div id="ss-section-0" class="row ss-section" style="${style}">
	<tags:instructions code="study.study_personnel.staff" />
	<table width="100%" class="tablecontent" valign="middle" id="ssi-table-row-TABLE" style="display:${fn:length(command.study.activeStudyOrganizations[index].studyPersonnels) lt 1 ? 'none' : 'inline;'};">
	  <tr id="ssi-table-head" class="ssi-table-head">
		<th width="55%" class="tableHeader"><tags:requiredIndicator />Research Staff</th>
		<th width="20%" class="tableHeader"><tags:requiredIndicator />Role</th>
		<th width="20%" class="tableHeader"><tags:requiredIndicator />Status</th>
		<th width="5%" class="tableHeader">&nbsp;</th>
 	  </tr>
 	   
	  <c:forEach var="sp" items="${command.study.activeStudyOrganizations[index].studyPersonnels}" varStatus="status">
	  	<study:oneResearchStaff cssClass="ssi-table-row" index="${status.index}" readOnly="${not empty sp.siteResearchStaff.researchStaff}" sp="${sp}"/>
	  	<c:if test="${empty sp.siteResearchStaff.researchStaff}">
      		<script>new jsPersonnel(${status.index}, '${sp.siteResearchStaff.researchStaff.fullName}');</script>
      	</c:if>
	  </c:forEach>

	  <c:if test="${fn:length(command.study.activeStudyOrganizations[index].studyPersonnels) lt 1}">
	    <tr id="ssi-empty-row" class="ssi-empty-row"><td colspan="4">There are no personnel associated to this study site.</td></tr>
	  </c:if>
	</table>
</div>