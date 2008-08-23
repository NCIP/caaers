<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="rpIndex" required="true" type="java.lang.Integer" description="The index of the Report"%>
<%@attribute name="report" required="true" type="gov.nih.nci.cabig.caaers.domain.report.Report" description="The report that is printed by this row." %>
<c:set var="repcurrClass" value="${rpIndex %2 gt 0 ? 'odd' : 'even'}" />
<tr align="center" id="row${rpIndex}" class="${repcurrClass}" onMouseOver="this.className='highlight'"	onMouseOut="this.className='${repcurrClass}'">
	<td width="5%"><chrome:collapsableElement targetID="reptable${report.id}" collapsed="true" id="ID_02"/></td>
	<td width="15%">
		<a style="text-decoration:none" href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}"/>">
			${report.reportDefinition.name}
		</a>	
	</td>
	<td width="20%">${report.aeReport.numberOfAes}</td>
	<td width="20%">${report.status.displayName}</td>
	<td width="20%">
		${report.lastVersion.statusAsString}
	</td>
	<td width="20%"></td>
</tr>
<tr id="reptable${report.id}" style="display:none;">
	<td/><td/>
	<td colspan=4>
		<div class="eXtremeTable">
			<table width="100%" border="0" cellspacing="0" class="rpAeTableRegion">
				<thead>
					<tr align="center" class="label">
						<td class="tableHeader" width="25%">AE Term</td>
						<td class="tableHeader" width="25%">Grade</td>
						<td class="tableHeader" width="25%">Start of AE(Primary)</td>
						<td class="tableHeader" width="25%">Requires report</td>
					</tr>
				</thead>
						
				<c:forEach items="${report.aeReport.adverseEvents}" var="ae" varStatus="statusAE">
					<ae:oneListAeRow index="${statusAE.index}" ae="${ae}" width="25%"/>
				</c:forEach>	
			</table>
		</div>
	</td>
</tr>