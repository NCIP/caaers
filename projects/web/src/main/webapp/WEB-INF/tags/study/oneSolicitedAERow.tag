<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>

<%@attribute name="eachRow" required="true" type="java.util.LinkedList"%>
    			<tr id="tr-${eachRow[0]}" class="data" align="center">
    				<td><label id='name-${eachRow[0]}'>${eachRow[1]}</label>
    				<input type="hidden" class="eachRowTermID" value="${eachRow[0]}" />
    				</td>
    			   <c:forEach varStatus="status" var="eachCheckBox" items="${eachRow}" begin="2" >
                     <td class="col-epoch-${status.index-2}"><input  class="ck${status.index-2} ck-${eachRow[0]}" id="ck${status.index-2}-${eachRow[0]}" name="epoch[${status.index-2}]" value="${eachRow[0]}" type="checkbox" ${(eachRow[status.index] == true)?'checked':''}/></td>    			   
    			   </c:forEach>	
    				<td id="deletecol-${eachRow[0]}" class='deletecol' ><input  id="button-${eachRow[0]}" class="eachRowDeleteButton" type="button" value="Delete" /></td>
    			</tr>
