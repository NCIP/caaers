<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:if test='${fn:length(command.nonImportableParticipants) > 0 || fn:length(command.importableParticipants) > 0 }'>
<c:set var="flashMessage" value="Data imported successfully" scope="request"/>
<chrome:flashMessage/>
				<table id="test" width="100%" class="tablecontent">
	    			<tr>
	    				<th scope="col" align="left"><b>Name</b> </th>
	    				<th scope="col" align="left"><b>Assigned To Study</b> </th>
	    			</tr>
	    			<c:forEach var='item' items='${command.importableParticipants}'>
						<tr class="results">
	   						<td align="left">
	   							<c:out value="${item.importedDomainObject.firstName}" />
	   							<c:out value="${item.importedDomainObject.lastName}" />
	   							<c:out value=" ( " />
	   							<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
								 		item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
								<c:out value =" ) " />	
	   						</td>
	   						<td align="left">
	   							<c:forEach var='assignment' items='${item.importedDomainObject.assignments}'>
	   								<c:out value="${assignment.studySite.study.shortTitle}" />
	   								( <c:out value="${assignment.studySite.study.primaryIdentifierValue != null ? 
								 		assignment.studySite.study.primaryIdentifierValue :  'NA'}" /> )
	   							</c:forEach>
	   						</td>
	   					</tr>
	   				</c:forEach>
	   			</table>	
			
</c:if>
