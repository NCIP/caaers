<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />
<c:set var="lastVersion" value="${report.lastVersion}" />
<c:set var="reportStatus" value="${lastVersion.reportStatus}" />
<tr align="center" id="row${rpIndex}" class="${repcurrClass}" onMouseOver="this.className='highlight'"	onMouseOut="this.className='${repcurrClass}'">
	<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
	<td align="left" width="15%">
			${report.reportDefinition.label}
	</td>
	<c:if test="${report.reportDefinition.amendable == true}">
	            		<td align="center" width="10%"><div class="label">${report.lastVersion.reportVersionId}</div></td>
	            	</c:if>
	            	<c:if test="${report.reportDefinition.amendable == false}">
	            		<td width="10%"/>
	            	</c:if>
	<td width="10%">${report.aeReport.numberOfAes}</td>
	<td width="20%" align="left">
		${report.lastVersion.reportStatus}
	</td>
	<td width="20%" id="status${report.id}" align="left">
		<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${reportStatus}" lastVersion="${lastVersion}"/>
	</td>
</tr>
<tr id="reptable${report.id}" style="display:none;">
	<td/><td/>
	<td colspan=5>
		<div class="eXtremeTable">
			<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
				<thead>
					<tr align="center" class="label">
						<td class="tableHeader" width="25%">AE Term</td>
						<td class="centerTableHeader" width="25%">Grade</td>
						<td class="tableHeader" width="25%">AE Start Date</td>
						<td class="tableHeader" width="25%">Requires Expedited Reporting?</td>
					</tr>
				</thead>
						
				<c:forEach items="${report.aeReport.adverseEvents}" var="ae" varStatus="statusAE">
						<ae:oneListAeRow index="${statusAE.index}" ae="${ae}" width="25%"/>
				</c:forEach>	
			</table>
		</div>
	</td>
</tr>