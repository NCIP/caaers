<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="integrationLogDetail" type="gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail" required="true" description="Log details" %>

<c:set var="currClass" value="${(index %2) eq 0 ? 'odd' : 'even'}" />

	<tr align="center" id="${index}" class="${currClass}" onmouseout="this.className='${currClass}'" onmouseover="this.className='highlight'">
		<td width="2%"><chrome:collapsableInputElement targetID="table${integrationLogDetail.id}" collapsed="true" id="collapseElement${integrationLogDetail.id}"/></td>
		<td width="15%" align="center" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.integrationLog.loggedOn }</td>
		<td width="10%" align="center" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.integrationLog.entity}</td>
		<td width="15%" align="left" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.businessId}</td>
		<td width="20%" align="left" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.synchStatus.stageName}</td>
		<td width="4%" align="center" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.outcome }</td>
		<td width="20%" align="left" onclick="expandImageClick('collapseElement${integrationLogDetail.id}', 'table${integrationLogDetail.id}');">${integrationLogDetail.integrationLog.notes}</td>
	</tr>

      




