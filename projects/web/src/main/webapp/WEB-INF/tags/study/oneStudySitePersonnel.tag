<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<div id="ss-section-0" class="row ss-section" style="${style}">
	<p id="instructions" align="left">
	<c:if  test="${fn:length(command.studySites[index].studyPersonnels) lt 1}">
	   There are no research staffs associated to this study site.
	   </c:if>
	   Click on the Add Research Staff button to associate research staff to this study site.	
	<br />
	</p>
	  <c:forEach var="sp" items="${command.studySites[index].studyPersonnels}" varStatus="status">
	    <study:aStudyChild title="Research Staff ${status.index + 1}" enableDelete="true"
			    sectionClass="ssi-section" removeButtonAction="removeStudyPersonnel" index="${status.index}" />
	    
	    <script>new jsPersonnel(${status.index}, '${sp.researchStaff.fullName}');</script>
	  </c:forEach>
	  <span id="ssi-bookmark"> </span>
</div>