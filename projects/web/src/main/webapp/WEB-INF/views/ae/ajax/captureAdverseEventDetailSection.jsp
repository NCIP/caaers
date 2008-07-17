<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<tags:noform>

      		
	<div class="leftpanel">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
			<c:forEach items="${fieldGroups.reportingPeriodDetailsFG.fields}" var="field">
      			<tags:renderRow field="${field}" />
      		</c:forEach>
		</c:if>
	</div>
	
	<div class="rightpanel">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
			<c:forEach items="${fieldGroups.treatmentAssignmentFG.fields}" var="field">
      			<tags:renderRow field="${field}"/>
      		</c:forEach>
    	</c:if>	
	</div>

    <chrome:division title="Solicited adverse event(s)">
    	<center>
    		<c:if test='${command.adverseEventReportingPeriod != null}'>
    			<table id="solicitedTable" width="100%" class="tablecontent">
    				<tr>
    					<th scope="col" align="left" width="30%"><b>Term</b> </th>
    					<th scope="col" align="left"><b>Grade</b> </th>
    					<th scope="col" align="left"><b>Attribution</b> </th>
   						<th scope="col" align="left"><b>Hospitalization</b> </th>
    					<th scope="col" align="left"><b>Expected</b> </th>
    				</tr>
    				<tr id="solicitedBlankRow" />
       				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       					<c:if test="${ae.solicited == true}">
	       					<ae:oneSaeRow index="${status.index}"/>
	       				</c:if>
       				</c:forEach>
       			</table>
       		</c:if>	
       	</center>
     </chrome:division>
            
        
	<chrome:division title="Observed adverse event(s)">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
        	<tags:aeTermQuery isMeddra="${not empty command.study.aeTerminology.meddraVersion}"  
        		callbackFunctionName="myCallback" 
        		version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}" title="Choose CTC terms">
        	</tags:aeTermQuery>
        	<%-- <c:if test='${hasObservedEvent == true}'> --%>
        	<table id="observedTable" width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left" width="30%"><b><tags:requiredIndicator/>Term</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Hospitalization</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Expected</b> </th>
    			</tr>
    			<tr id="observedBlankRow" />
    			<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            		<c:if test="${ae.solicited == false}">
	            		<ae:oneSaeRow index="${status.index}"/>
	            	</c:if>
            	</c:forEach>
            </table>
        	<%-- </c:if> --%>
        </c:if>
    </chrome:division>
</tags:noform>