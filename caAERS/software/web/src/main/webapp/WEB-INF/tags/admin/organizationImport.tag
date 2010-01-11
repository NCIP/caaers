<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

	<chrome:division title="Sumary" id="organization_import_sumary">
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Invalid records</b> </th>
    			<th scope="col" align="left"><b>Organizations to be added</b> </th>
    			<th scope="col" align="left"><b>Organizations to be updated</b> </th>
    		</tr>
			<tr class="results">
				<td align="left"><c:out value="${fn:length(command.nonImportableOrganizations)}" /></td>
				<td align="left"><c:out value="${fn:length(command.importableOrganizations)}" /></td>
				<td align="left"><c:out value="${fn:length(command.updateableOrganizations)}" /></td>
   			</tr>
   		</table>	
   	</chrome:division>

	<c:if test='${fn:length(command.nonImportableOrganizations) > 0 }'>
		<chrome:division title="${fn:length(command.nonImportableOrganizations)} Invalid Organization records found" id="organization_invalid">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Possible problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableOrganizations}'>
			<tr class="results">
				<td align="left">
   					<c:forEach var='message' items='${item.messages}'>
   						- <font color="red"><c:out value='${message.message}'/></font><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>

	<c:if test='${fn:length(command.importableOrganizations) > 0 }'>
		<chrome:division title="${fn:length(command.importableOrganizations)} Organizations to be added" id="organization_create">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Assigned Identifier</b> </th>
    			<th scope="col" align="left"><b>Organization name</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableOrganizations}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.nciInstituteCode}" /></td>
				<td align="left"><c:out value="${item.importedDomainObject.name}" /></td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>
	
	<c:if test='${fn:length(command.updateableOrganizations) > 0 }'>
		<chrome:division title="${fn:length(command.updateableOrganizations)} Organizations to be updated" id="organization_update">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>Assigned Identifier</b> </th>
    			<th scope="col" align="left"><b>Organization name</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.updateableOrganizations}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.nciInstituteCode}" /></td>
				<td align="left"><c:out value="${item.importedDomainObject.name}" /></td>
   			</tr>
   			</c:forEach>
   		</table>	
   		</chrome:division>
	</c:if>	
	
	<c:if test='${fn:length(command.importableOrganizations) == 0 }'>
		<chrome:division title="No Organization records to be loaded" id="no_organizations">
			<table id="test" width="100%" class="tablecontent">
	   			<tr class="results">
	   				<td align="left"><i>None</i></td>
	   			</tr>
	   		</table>	 
   		</chrome:division>
   	</c:if>