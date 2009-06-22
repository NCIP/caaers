<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<tr>
	<td align="right">
    	<tags:paginationControl isFirstPage="false" isLastPage="false"/>
    </td>
</tr>
<tr>
	<td witdh="100%">
		<c:forEach items="${command.searchResultsDTO.resultMap}" var="resultEntry">
			<div>
				${command.searchCriteriaParticipantCentric  ? 'Study' : 'Participant'} : ${resultEntry.value.header}
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
   	 			<thead>
      				<tr align="center" class="label">
       					<td width="2%" class="tableHeader"></td>
       			 		<td width="18%" class="tableHeader">Course</td>
       			 		<td width="22%" class="centerTableHeader">Treatment Type</td>
       			 		<td width="25%" class="centerTableHeader">Review Status</td>
        				<td width="8%" class="tableHeader">Comments</td>
        				<td width="25%" class="centerTableHeader">Action</td>
      				</tr>
    			</thead>
				<c:forEach items="${resultEntry.value.results}" var="rp" varStatus="rpStatus">
					<ae:oneRoutingReportingPeriodRow index="${rpStatus.index}" reportingPeriod="${rp}" />
				</c:forEach>
			</table>
		</c:forEach>
	</td>
</tr>