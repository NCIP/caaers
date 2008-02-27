<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="title"><c:choose>
    <c:when test="${index == 0}">Primary adverse event</c:when>
    <c:otherwise>Adverse event ${index + 1}</c:otherwise>
</c:choose></c:set>

    
    	<tr class="division ae-section" id="ae-section-${index}" >
    	
    	<td>
    	<c:if test="${command.aeRoutineReport.adverseEvents[index].report == null}">
    	<tags:renderInputs field="${fieldGroups[ctcTermGroup].fields[0]}"/>
    	</c:if>
    	<c:if test="${command.aeRoutineReport.adverseEvents[index].report != null}">
    		<span class="sae"><c:out value="${command.aeRoutineReport.adverseEvents[index].adverseEventTerm.universalTerm}" /></span>
    	</c:if>
    	</td>
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
            <%--<tags:renderRow field="${field}"/>--%>

			<c:if test="${command.aeRoutineReport.adverseEvents[index].report == null}">
            <td>
				<tags:renderInputs field="${field}"/>
            </td>				
			</c:if>
        </c:forEach>
        <c:if test="${command.aeRoutineReport.adverseEvents[index].report != null}">
        	<td><span class="sae"><c:out value="${command.aeRoutineReport.adverseEvents[index].grade}" /></span></td>
   			<td><span class="sae"><c:out value="${command.aeRoutineReport.adverseEvents[index].attributionSummary}" /></span></td>
   			<td><span class="sae"><c:out value="${command.aeRoutineReport.adverseEvents[index].hospitalization}" /></span></td>
   			<td><span class="sae"><c:out value="${command.aeRoutineReport.adverseEvents[index].expected}" /></span></td>
   		</c:if>
        
        </tr>
