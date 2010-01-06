<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<c:if test='${fn:length(command.importableResearchStaff) > 0 || fn:length(command.nonImportableResearchStaff) > 0 }'>
		<chrome:division title="ResearchStaff records did NOT get loaded" id="research_staff_not_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableResearchStaff}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>
   				<td align="left">
   					<c:forEach var='message' items='${item.messages}'>
   						- <font color="red"><c:out value='${message.message}'/></font><br>
   					</c:forEach>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableResearchStaff) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
		
		
		<chrome:division title="ResearchStaff records got loaded" id="research_staff_load">
		
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableResearchStaff}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>

   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.importableResearchStaff) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
   		</chrome:division>
	</c:if>
