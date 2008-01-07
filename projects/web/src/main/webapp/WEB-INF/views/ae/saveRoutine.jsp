<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            
            You have added ${fn:length(command.aeRoutineReport.adverseEvents)} AE(s). 
            To save the set of AE(s) that you entered, click on Save button. 
            <%--
            To save the set of AEs that you entered, click on Save button. 
            To exit the current flow after saving, click on Save and Continue button. 
            This will return you to the list of AEs for the selected study and participant combination.
            --%>
            
        </jsp:attribute>
        
         <jsp:attribute name="repeatingFields">
    		<center>
    		<c:if test="${fn:length(command.aeRoutineReport.adverseEvents) > 0}" >
    		<table width="90%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Term:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Grade:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Attribution:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Hospitalization:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Expected:</b> </th>
    			</tr>
    				
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" var="ae" varStatus="status">
            	<tr>
            		<td>
            			<c:out value="${ae.adverseEventTerm.universalTerm}" />
            		</td>
            		<td>
            			<c:out value="${ae.grade}" />
            		</td>
            		
            		<td>
            			<c:out value="${ae.attributionSummary}" />
            		</td>
            		
            		<td>
            			<c:out value="${ae.hospitalization}"/>
            		</td>
            		
            		<td>
            			<c:out value="${ae.expected == true ? 'Yes' : 'No' }" />
            		</td>	
            	</tr>	 
            </c:forEach>
            </table>
            </c:if>
            </center>
        </jsp:attribute>
        
        <jsp:attribute name="singleFields">
            <input type="hidden" name="_finish"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>