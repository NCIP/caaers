<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:if test='${fn:length(command.importableInvestigators) > 0 || fn:length(command.nonImportableInvestigators) > 0 }'>

		<chrome:division id="investigator_load" title="Records caAERS understands">
		<tags:instructions code="admin.import.reviewSubmit.willImport"/>
		<div class="green-means-go">
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.importableInvestigators}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.importableInvestigators) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
		</div>
   		</chrome:division>
		
		<chrome:division id="investigator_not_load" title="Records that have a problem">
		<tags:instructions code="admin.import.reviewSubmit.wontImport"/>
		<div class="red-means-stop">
		<table id="test" width="100%" class="tablecontent">
    		<tr>
    			<th scope="col" align="left"><b>First Name</b> </th>
    			<th scope="col" align="left"><b>Last Name</b> </th>
    			<th scope="col" align="left"><b>NCI Identifier</b> </th>
    			<th scope="col" align="left"><b>Possible Problem</b> </th>
    		</tr>
    		<c:forEach var='item' items='${command.nonImportableInvestigators}'>
			<tr class="results">
				<td align="left"><c:out value="${item.importedDomainObject.firstName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.lastName}" /></td>
   				<td align="left"><c:out value="${item.importedDomainObject.nciIdentifier}" /></td>
   				<td align="left">
   					<ul>
   					<c:forEach var='message' items='${item.messages}'>
   						<li><font color="red"><c:out value='${message.message}'/></font></li>
   					</c:forEach>
					</ul>
   				</td>
   			</tr>
   			</c:forEach>
   			<c:if test='${fn:length(command.nonImportableInvestigators) == 0 }'>
   				<tr class="results"><td align="left"><i>None</i></td></tr> 
   			</c:if>
   		</table>	
		</div>
   		</chrome:division>
	</c:if>
