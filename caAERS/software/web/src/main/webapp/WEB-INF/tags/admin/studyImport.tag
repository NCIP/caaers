<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

	<c:if test='${fn:length(command.nonImportableStudies) > 0 || fn:length(command.importableStudies) > 0}'>
		<chrome:division title="Study records will NOT be loaded" id="study_will_not_load">
			<table id="test" width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b>Study Identifier</b> </th>
    				<th scope="col" align="left"><b>Study Short Title</b> </th>
    				<th scope="col" align="left"><b>Possible Problem</b> </th>
    			</tr>
    			<c:forEach var='item' items='${command.nonImportableStudies}'>
				<tr class="results">
					<td align="left">
						<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
										item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
					</td>
   					<td align="left"><c:out value="${item.importedDomainObject.shortTitle}" /></td>
   					<td align="left">
   						<c:forEach var='message' items='${item.messages}'>
   							- <font color="red"><c:out value='${message.message}'/></font><br>
   						</c:forEach>
   					</td>
   				</tr>
   				</c:forEach>
   				<c:if test='${fn:length(command.nonImportableStudies) == 0 }'>
   					<tr class="results"><td align="left"><i>None</i></td></tr> 
   				</c:if>
   			</table>	
   		</chrome:division>
		
		<chrome:division title="Study records will be loaded" id="study_will_load">
			<table id="test" width="100%" class="tablecontent">
   		 		<tr>
   	 				<th scope="col" align="left"><b>Study Identifier</b> </th>
   	 				<th scope="col" align="left"><b>Study Short Title</b> </th>
   	 				<th scope="col" align="left"><b>Possible Problem</b> </th>
   	 			</tr>
   	 			<c:forEach var='item' items='${command.importableStudies}'>
					<tr class="results">
						<td align="left">
							<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
										item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
						</td>
   						<td align="left"><c:out value="${item.importedDomainObject.shortTitle}" /></td>
   						<td align="left" color="red">
   							<c:forEach var='message' items='${item.messages}'>
   								- <c:out value='${message.message}'/><br>
   							</c:forEach>
   						</td>
   					</tr>
   				</c:forEach>
   			</table>	
   		</chrome:division>
	</c:if>
