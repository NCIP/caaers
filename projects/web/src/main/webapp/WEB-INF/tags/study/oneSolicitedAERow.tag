<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>

<%@attribute name="eachRow" required="true" type="java.util.LinkedList"%>
    			<tr class="data" align="center">
    				<td><c:out value="${eachRow[1]}" /></td>
    			   <c:choose>
    			     <c:when  test="${eachRow[2] == true}">
    			        <c:set var="epoch1checked" value="checked" />
    			     </c:when>
    			     <c:otherwise>
    			        <c:set var="epoch1checked" value="" />
    			     </c:otherwise>   
    			   </c:choose>
    			   <c:choose>
    			     <c:when  test="${eachRow[3] == true}">
    			        <c:set var="epoch2checked" value="checked" />
    			     </c:when>
    			     <c:otherwise>
    			        <c:set var="epoch2checked" value="" />
    			     </c:otherwise>   
    			   </c:choose>
    			   <c:choose>
    			     <c:when  test="${eachRow[4] == true}">
    			        <c:set var="epoch3checked" value="checked" />
    			     </c:when>
    			     <c:otherwise>
    			        <c:set var="epoch3checked" value="" />
    			     </c:otherwise>   
    			   </c:choose>
    			   
    				<td><input  id="ck1-${eachRow[0]}" name="epoch[1]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch1checked}" />/></td>
    				<td><input  id="ck2-${eachRow[0]}" name="epoch[2]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch2checked}" />/></td>
    				<td><input  id="ck3-${eachRow[0]}" name="epoch[3]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch3checked}" />/></td>
    				<td><input  type="button" value="Delete" /></td>
    			</tr>
