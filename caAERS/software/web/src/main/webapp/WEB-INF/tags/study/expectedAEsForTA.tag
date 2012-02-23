<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<%@taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
    #termsTable .even {
    }

    #termsTable .odd {
        /*background-color: #e8e8ff;*/
    }
</style>
<c:if test="${command.study.havingTreatmentLevelInterventions }">
	<c:forEach items="${command.study.treatmentAssignments }" var="ta" varStatus="ta_status">
		<c:if test="${ta.havingInterventions }">
		<c:set var="index" value="0"/>
		<chrome:division title="${ta.code }" collapsable="true" id="expected-ae-${ta.code }">
			<tags:table bgColor="#AAAAAA" contentID="termsDiv">
	               <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
	                    <tr bgcolor="#E4E4E4">
	                        <th scope="col" align="left" width="17%"><b>Intervention(s)</b></th>
	                        <th scope="col" align="left" width="26%"><b>Term</b></th>
	                        <th scope="col" align="left" width="8%"><b>Grade 1</b></th>
	                        <th scope="col" align="left" width="8%"><b>Grade 2</b></th>
	                        <th scope="col" align="left" width="8%"><b>Grade 3</b></th>
	                        <th scope="col" align="left" width="8%"><b>Grade 4</b></th>
	                        <th scope="col" align="left" width="8%"><b>Grade 5</b></th>
	                        <th scope="col" align="left" width="8%"><b>Expected (yes/no)</b></th>
	                        <th scope="col" align="left" width="9%"><b>Overall % expected</b></th>
	                        <%-- <th width="7%">&nbsp;</th>--%>
	                    </tr>
	                   <c:forEach items="${ta.abstractStudyInterventionExpectedAEs}" var="tasae" varStatus="tasae_status">
                   			<tr class="ae-section ${tasae_status.index % 2 gt 0 ? 'odd' : 'even'}" id="TA_TERM_-${tasae.id}" bgcolor="white">
                   				<td><ul style="padding: 0px 0px 0px 15px;">
                   				<c:forEach items="${tasae.treatmentAssignmentAgents }" var="taa">
                   					<li>${taa.studyAgent.agent.name }</li>
                   					<%--<c:if test="${fn:length(tasae.treatmentAssignmentAgents)-1 > tasae_status.index }"><br /></c:if>  --%>
                   				</c:forEach>
                   				</ul>
                   				</td>
	                           <admin:oneAgentSpecificAE isOtherSpecify="false" index="${tasae_status.index}" term="${tasae}" 
	                           		pathToAECollection="study.treatmentAssignments[${ta_status.index }].abstractStudyInterventionExpectedAEs" showTerminology="false"/>
                                <%-- <td style="text-align:center;" width="50px">
                                     <tags:button id="${tasae.id}" color="red" type="button" value="" size="small" icon="x" onclick="removeTerm(${ta_status.index}, ${tas_status.index}, ${tasae_status.index})"/>
                                </td> --%>
	                       </tr>
	                   </c:forEach>
	                <tr id="observedBlankRow" style="display:none;"><td></td></tr>
	               </table>
	
	        </tags:table>
		</chrome:division>
		</c:if>
	</c:forEach>
</c:if>