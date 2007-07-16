<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<div id="ss-section-0" class="row ss-section" style="${style}">
	<p id="instructions" align="left">Add Investigators to this study <a href="javascript:fireAction('addInv', '0');"><img
				src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a><tags:indicator id="ssi-add-indicator"/>
	</p>
	  <c:forEach var="si" items="${command.studySites[index].studyInvestigators}" varStatus="status">
	    <study:aStudyChild title="Study Investigator ${status.index + 1}" enableDelete="true"
			    sectionClass="ssi-section" removeButtonAction="removeInv" index="${status.index}" />
	    
	    <script>new jsInvestigator(${status.index}, '${si.siteInvestigator.investigator.fullName}');</script>
	  </c:forEach>
	  <span id="ssi-bookmark" />
</div>