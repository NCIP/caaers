<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%-- 
Note: -
   This should work on the orginal adverse event list, and not on the decorated list in command
--%>
<c:if test='${not empty command.adverseEventReportingPeriod}'>
<chrome:division id="rpd-div" title="Evaluation Period Details">
	<ae:reportingPeriodDetails />
</chrome:division>

<chrome:division title="Observed Adverse Event(s)" collapsable="true" id="observedID">
               <tags:aeTermQuery isMeddra="${not empty command.study.aeTerminology.meddraVersion}"
                       noBackground="true"
                   callbackFunctionName="rpCreator.addAdverseEvents" ignoreOtherSpecify="false" isAjaxable="true"
                   version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}"
                   title="">
               </tags:aeTermQuery>
               <table id="observedTable" width="100%" class="tablecontent">
                   <tr>
                       <th scope="col" align="left" width="43%"><b>Term</b> </th>
                       <th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
                       <th scope="col" align="left"><b>Attribution</b> </th>
                       <th scope="col" align="left"><b>Hospitalization</b> </th>
                       <th scope="col" align="left"><b>Expected</b> </th>
                       <caaers:renderFilter elementID="adverseEvents[].serious"><th scope="col" align="left"><b>Serious</b> </th></caaers:renderFilter>
                    <th scope="col" align="left"> </th>
                   </tr>
                <c:set var="noObservedAE" value="true" scope="request"/>
                   <tr id="observedBlankRow" />
                   <c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
                       <c:if test="${not ae.solicited}">
                        <c:set var="noObservedAE" value="false" scope="request"/>
                        <ae:oneSaeRow index="${status.index}" isSolicitedAE="false" isAETermOtherSpecify="${ae.adverseEventTerm.otherRequired}" adverseEvent="${ae}" aeTermIndex="0"/>
                    </c:if>
                   </c:forEach>
                <c:if test="${noObservedAE}">
                <tr id="observedEmptyRow">
                        <td colspan="7">No observed adverse event added</td>
                </tr>
                </c:if>
               </table>
    </chrome:division>

<chrome:division title="Solicited Adverse Event(s)" collapsable="true" id="solicitatedID">
	<center>
			<table id="solicitedTable" width="100%" class="tablecontent" border="0">
				<tr>
					<th scope="col" align="left" width="43%"><b>Term</b> </th>
					<th scope="col" align="left"><b>Grade</b> </th>
					<th scope="col" align="left"><b>Attribution</b> </th>
					<th scope="col" align="left"><b>Hospitalization</b> </th>
					<th scope="col" align="left"><b>Expected</b> </th>
					<caaers:renderFilter elementID="adverseEvents[].serious"><th scope="col" align="left"><b>Serious</b> </th></caaers:renderFilter>
				</tr>
				<c:set var="noSolictedAE" value="true" scope="request"/>
   				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
   					<c:if test="${ae.solicited}">
						<c:set var="noSolictedAE" value="false" scope="request" />
    					<ae:oneSaeRow index="${status.index}" isAETermOtherSpecify="false" isSolicitedAE="true" adverseEvent="${ae}" aeTermIndex="0"/>
    				</c:if>
   				</c:forEach>
				<c:if test="${noSolictedAE}">
				<tr id="solicitedBlankRow">
					<td colspan="6">No solicited adverse event(s) associtated to this study</td>
				</tr>
				</c:if>
   			</table>
   	</center>
 </chrome:division>


</c:if>