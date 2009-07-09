<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="step" type="java.lang.String" required="true"%>
<%@attribute name="stepDesc" type="java.lang.String" required="true"%>
<%@attribute name="reportVersionId" type="java.lang.String" required="true"%>
<%@attribute name="reportTrackingStatus" type="gov.nih.nci.cabig.caaers.domain.report.ReportTrackingStatus" %>
<%@attribute name="reportTracking" type="gov.nih.nci.cabig.caaers.domain.report.ReportTracking" %>

			<tr>
				<td width="5%">
					<c:if test="${reportTrackingStatus.status || reportTrackingStatus.status==false}">
						<chrome:collapsableElement targetID="reptable${step}-${reportTracking.id}-${reportTracking.attemptNumber}" collapsed="true" id="ID_02"/>
					</c:if>
				</td>
				<td width="50%"  class="step${reportTrackingStatus.status}" >
					${stepDesc}
				</td>
				<td width="15%" ><tags:formatDate value="${reportTrackingStatus.recordedTime}" />   </td>
				<td width="5%" >
					<c:if test="${reportTrackingStatus.status}">
						<img src="<chrome:imageUrl name="../check.png" />" />
					</c:if>
					<c:if test="${reportTrackingStatus.status == false}">
						<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</c:if>					
				</td>
			</tr>
			
			<tr id="reptable${step}-${reportTracking.id}-${reportTracking.attemptNumber}" style="display:none;">
				<td/>
				<td colspan="2">
					<div class="info">
 
						<c:if test="${reportTrackingStatus.statusMessage != ''}">
							&nbsp;&nbsp;&nbsp;Message	  :  <font color="red">${reportTrackingStatus.statusMessage}</font>
						</c:if>
					</div>
				</td>
			</tr>
			
			
			
			