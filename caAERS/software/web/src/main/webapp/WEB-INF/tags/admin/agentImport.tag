<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

	<chrome:division title="Summary" id="agent_import_sumary">
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Invalid records</b> </th>
    			<th scope="col" align="left"><b>Agents to be added</b> </th>
    			<th scope="col" align="left"><b>Agents to be updated</b> </th>
    		</tr>
			<tr class="results">
				<td align="left"><c:out value="${fn:length(command.nonImportableAgents)}" /></td>
				<td align="left"><c:out value="${fn:length(command.importableAgents)}" /></td>
				<td align="left"><c:out value="${fn:length(command.updateableAgents)}" /></td>
   			</tr>
   		</table>	
   	</chrome:division>



	<c:if test='${fn:length(command.nonImportableAgents) > 0 }'>
		<chrome:division title="${fn:length(command.nonImportableAgents)} Invalid records" id="agent_invalid" collapsable="true" collapsed="true">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Possible problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableAgents}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.message}" /></td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>

	<c:if test='${fn:length(command.importableAgents) > 0 }'>
		<chrome:division title="${fn:length(command.importableAgents)} Valid records" id="agent_create" collapsable="true" collapsed="true">
		
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
	
	<c:if test='${fn:length(command.updateableAgents) > 0 }'>
		<chrome:division title="${fn:length(command.updateableAgents)} Agents to be updated" id="agent_update" collapsable="true" collapsed="true">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>NSC number</b> </th>
    			<th scope="col" align="left"><b>Agent name</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.updateableAgents}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.nscNumber}" /></td>
				<td align="left"><c:out value="${item.importedDomainObject.name}" /></td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>