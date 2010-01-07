<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

	<c:if test='${fn:length(command.importableAgents) > 0 }'>
		<chrome:division title="${fn:length(command.importableAgents)} Agent records got loaded" id="agent_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>NSC number</b> </th>
    			<th scope="col" align="left"><b>Agent name</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableAgents}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.nscNumber}" /></td>
				<td align="left"><c:out value="${item.importedDomainObject.name}" /></td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>
	<c:if test='${fn:length(command.importableAgents) == 0 }'>
		<chrome:division title="No Agent records to be loaded" id="agent_load">
			<table id="test" width="100%" class="tablecontent">
	   			<tr class="results">
	   				<td align="left"><i>None</i></td>
	   			</tr>
	   		</table>	 
   		</chrome:division>
   	</c:if>