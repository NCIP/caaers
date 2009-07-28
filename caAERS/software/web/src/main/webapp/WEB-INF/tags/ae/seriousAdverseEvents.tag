<%--
 Flow : CaptureAdverseEventController
ae_review_report.jsp uses this to display a list of serious adverse events. 
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="aeReportId" required="true" type="java.lang.Integer" description="Id of the data collection"%>
<%@attribute name="adverseEvents" required="true" type="java.util.List" description="List of adverse events to be displayed"%>
<%@attribute name="primaryAeId" required="true" type="java.lang.Integer" description="The primary adverse event ID" %>
<c:if test="${not empty adverseEvents}">
<b>Adverse events</b>
<div id="adverseEvents-dc-${aeReportId}" class="serious-aes">
<table width="100%" class="tablecontent">
	       <tr>
	         <th scope="col" align="center" style="text-align:center;" width="5%"><b>Select</b></th>
	         <th scope="col" align="center" style="text-align:center;" width="10%">Requires<br>expedited<br> reporting?</th>
	         <th scope="col" align="left" width="35%"><b>Term</b> </th>
	         <th scope="col" align="left" width="25%"><b>Grade</b> </th>
	         <th scope="col" align="left" width="15%"><b>Start Date</b> </th>
	         <th scope="col" align="left" width="10%"><b><tags:requiredIndicator></tags:requiredIndicator>Select Primary</b> </th>
	       </tr>
      <c:forEach var="ae" items="${adverseEvents}" varStatus="aeStatus">
      	<c:set var="_cssClass" value="${ae.retired ? 'retired' : ''} ${ae.modified ? 'modified':''} ${ae.reported ? 'reported' :''}" />
      	<c:set var="_jsHandler" value="handleAdverseEventSelection(${aeReportId},${ae.id}, false)" />
      	<c:if test="${ae.reported}">
      		<c:set var="_jsHandler" value="handleAdverseEventSelection(${aeReportId},${ae.id}, true)" />
      	</c:if>
      	
      	  <!-- cssClass : ${_cssClass } -->
	      <tr class="${_cssClass}">
		      <td align="center" class="${_cssClass}">
		      	<c:if test="${not ae.retired}">
		      	 <input id="ae-${aeReportId}-${ae.id}" type="checkbox" checked 
		      		value="${ae.id}" class="ae ae_${aeReportId}" 
		      		name="ae_${aeReportId}"
		      	 	onclick="${_jsHandler}" />
		      	</c:if>
		      </td>
		      <td align="center" class="${_cssClass}">
		      	<span class="${ae.requiresReporting ? 'reportingYes' : 'reportingNo' }">${ae.requiresReporting? 'Yes' : 'No' }</span>
		      </td>
		      <td class="${_cssClass}">
		      	${ae.adverseEventTerm.universalTerm}
		      	<c:if test="${empty ae.report}"><img src="<chrome:imageUrl name="../new.gif" />" /></c:if>
		      	<c:if test="${ae.retired}"><img src="<chrome:imageUrl name="../redexclamation.gif" />" /></c:if>
		      </td>
		      <td align="center" class="${_cssClass}">
		      ${ae.grade.code}:${ae.grade.displayName }
		      </td>
		      <td align="center" class="${_cssClass}">
		      	<c:if test="${empty ae.startDate}">
		      	 <ui:date path="evaluationResult.allAeMap[${aeReportId}][${aeStatus.index}].startDate" />
		      	</c:if>
		      	<c:if test="${not empty ae.startDate}">
		      	 <tags:formatDate value="${ae.startDate}" />
		      	</c:if>
		       
		      </td>
		      <td align="center" class="${_cssClass}">
		        <c:if test="${not ae.retired}">
		        <input id="ae-${aeReportId}-${ae.id}-primary" type="radio" name="ae_${aeReportId}_primary" 
		        value="${ae.id}" ${ae.id eq primaryAeId ? 'checked' :''} class="ae_${aeReportId}_primary"
		        onclick="handlePrimaryAdverseEvent( ${aeReportId},${ae.id},'${ae.adverseEventTerm.universalTerm}', '${ae.grade.code}:${ae.grade.displayName }')" />
		        </c:if>
		      </td>
	      </tr>
      </c:forEach> 
    
        
 </table>
 </div>
</c:if>
