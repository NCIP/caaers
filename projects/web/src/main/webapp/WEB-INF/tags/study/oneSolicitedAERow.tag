<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>

<%@attribute name="eachRow" required="true" type="java.util.LinkedList"%>
    			<tr id="tr-${eachRow[0]}" class="data" align="center">
    				<td><label id='name-${eachRow[0]}'>${eachRow[1]}</label>
    				<input type="hidden" class="eachRowTermID" value="${eachRow[0]}" />
    				</td>
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
    			   
    				<td><input  class="ck1" id="ck1-${eachRow[0]}" name="epoch[1]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch1checked}" />/></td>
    				<td><input   class="ck2" id="ck2-${eachRow[0]}" name="epoch[2]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch2checked}" />/></td>
    				<td><input   class="ck3" id="ck3-${eachRow[0]}" name="epoch[3]" value="${eachRow[0]}" type="checkbox" <c:out value="${epoch3checked}" />/></td>
    				<td class='deletecol'><input  id="button-${eachRow[0]}" class="eachRowDeleteButton" type="button" value="Delete" /></td>
    			</tr>
