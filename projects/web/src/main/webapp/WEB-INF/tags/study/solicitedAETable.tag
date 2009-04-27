<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="displayOnly" required="true" type="java.lang.Boolean" %>

<c:if test="${statusMessage eq 'wrongEpochDelete'}">
    <div id="flash-message" class="error"><tags:message key="wrong.epoch.delete" /></div>
</c:if>
    <table id="sae-0" class="sae">
  		 
    		<tbody class="tablebody">
			 <tr>
			  <td style="border:0px">&nbsp;</td>
			  <c:forEach varStatus="statusVar" var="eachEpoch" items="${command.study.epochs}">
			  <td style="border:0px; text-align:center; border-collapse:collapse;"><c:if test="${!displayOnly}"><a ${(statusVar.index == 0)?"style='display:none;'":""} id="delete-epoch-${statusVar.index}" class="delete-epoch" href="#jumhere"><img src="<c:url value='/images/checkno.gif' ></c:url>"></img>&nbsp;Delete</a></c:if></td>
			  </c:forEach>
			 </tr>
    		    <tr class="head">
    		       <th class="term" >Evaluation Period Type</th>

                    <c:forEach varStatus="statusVar" var="eachEpoch" items="${command.study.epochs}">
                        <th id="th-table1-${statusVar.index}" class="reportingperiod">

                            <c:if test="${!displayOnly}">
                                <input type="hidden" name="epoch_id" value="${eachEpoch.epochOrder}"/>
                            </c:if>
                            <div class="index">
                                <c:choose>
                                    <c:when test='${statusVar.index != 0 && !displayOnly}'>
                                        <ui:inplaceTextField path="study.epochs[${statusVar.index}].name"/>
                                    </c:when>
                                    <c:otherwise>
                                        <span id="epochs[${statusVar.index}].name-id"><caaers:value path="study.epochs[${statusVar.index}].name"/></span>
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${!displayOnly}">
                                &nbsp
                                </c:if>
                            </div>
                            <c:if test="${!displayOnly}">
                                <div class="inst">
                                    <a href="#jumphere" class="instructionLinks" id="epochs[${statusVar.index}].descriptionText-id">Edit Instructions</a>
                                </div>
                                <tags:popupEditInstruction propertyName="study.epochs[${statusVar.index}].descriptionText"></tags:popupEditInstruction>
                                <a name="jumphere"/>
                            </c:if>
                        </th>
                    </c:forEach>

                    <c:if test="${!displayOnly}">
        		  	  <td id="addButtonCell" class="action" align="center"><tags:button id="AddEpoch" color="blue" type="button" value="Add" size="small" icon="add" onclick=""/></td>
       		  	   </c:if>  
    			</tr>
 				<c:if test="${!displayOnly}">
    		    <tr class="gap">
        	        <td colspan="3" style="border-width:0px 0px 0px 0px;">
    		           Check the appropriate boxes to associate the AE term to a evaluation period type.
    		        </td>
    		    </tr>
				</c:if>
    			<tr class="head">
                    <th class="term">Adverse Event Term</th>
                    <c:forEach varStatus="statusVar" var="eachEpoch" items="${command.study.epochs}">
                        <th id="th-col-epoch-${statusVar.index}" class="epoch" align="center">
							<c:if test="${not displayOnly}">
                            <div><input id="ck${statusVar.index}" type="checkbox" ${displayOnly?'disabled':''}/></div>
							</c:if>
                        </th>
                    </c:forEach>
                    <c:if test="${!displayOnly}">
                        <th class="action"> &nbsp</th>
                    </c:if>
                </tr>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow displayOnly="${displayOnly}" index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
				<c:if test="${!displayOnly}">
    			<tr id="specialLastRow" class="bottom">
    				<td colspan="5" align='center'><span id='lastRowSpan' class='lastRowValue' style="display:none;">You have no solicited adverse events added in the list !</span></td>
    			</tr>	
    			
    			<tr class="lastLineOfTable">
    		    </tr>
    		    </c:if>			
  			</tbody>
  			
  		</table>	
	
		            