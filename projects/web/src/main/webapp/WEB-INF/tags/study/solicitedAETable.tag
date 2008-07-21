<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

        <table id="sae-0" class="sae">
  		 
    		<tbody class="tablebody">
    		    <tr class="head">
    		       <th class="term" >Reporting period type</td>
    		       <c:forEach varStatus="statusVar" var="eachEpoch" items="${command.epochs}">
        			<th id="th-table1-${statusVar.index}" class="reportingperiod">
        			<input type="hidden" name="epoch_id" value="${eachEpoch.epochOrder}" />
		            	
		                <div class="index">
		                <c:choose>
						  <c:when test='${statusVar.index != 0}' >
						   <tags:inplaceTextField propertyName="epochs[${statusVar.index}].name" />
						  </c:when>
						  <c:otherwise>
						    <span id="epochs[${statusVar.index}].name-id"><caaers:value path="epochs[${statusVar.index}].name" /></span>
						  </c:otherwise>
						</c:choose>
								                
		                
		                &nbsp<a ${(statusVar.index == 0)?"style='display:none;'":""} id="delete-epoch-${statusVar.index}" class="delete-epoch" href="#jumhere"><img align='right' class="close-button" src="<c:url value='/images/checkno.gif' ></c:url>"></img></a></div>
		            	<div class="inst"><a href="#jumphere" id="epochs[${statusVar.index}].descriptionText-id">Add Instructions</a></div>
		            	<tags:popupEditInstruction propertyName="epochs[${statusVar.index}].descriptionText"></tags:popupEditInstruction>
  		                <a name="jumphere" />
            		</th>
            		</c:forEach>
            		<th id="addButtonCell" class="action"> &nbsp<input id="AddEpoch" type="button" value="Add" /></th>
    			</tr>
    		    <tr class="gap">
    		    </tr>
    			<tr class="head">
        			<th class="term">Adverse event term</th>
        			<c:forEach varStatus="statusVar" var="eachEpoch" items="${command.epochs}">
        			<th id="th-col-epoch-${statusVar.index}" class="epoch" >
		            	<div><input id="ck${statusVar.index}" type="checkbox" /></div>
		            </th>
            		</c:forEach>
            		<th class="action"> &nbsp </th>
    			</tr>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
    			<tr id="specialLastRow" class="bottom">
    				<td colspan="5" align='center'><span id='lastRowSpan' class='lastRowValue' style="display:none;">You have no solicited adverse events added in the list !</span></td>
    			</tr>	
    		    <tr class="lastLineOfTable">
    		    </tr>			
  			</tbody>
  			
  		</table>	
