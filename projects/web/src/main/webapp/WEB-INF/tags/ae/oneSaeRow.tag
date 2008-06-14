<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<c:set var="mainGroup">main${index}</c:set>
    
    	<tr class="division ae-section" id="ae-section-${index}" >
    		<td>
            	<c:out value="${command.saeList[index].ctcterm.term}" />
            </td> <br>
	        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
    	        <td>
					<tags:renderInputs field="${field}"/>
           		</td>				
        	</c:forEach>
        </tr>
