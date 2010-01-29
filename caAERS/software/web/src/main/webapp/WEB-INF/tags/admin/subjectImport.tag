<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:if test='${fn:length(command.nonImportableParticipants) > 0 || fn:length(command.importableParticipants) > 0 }'>
	<c:choose> 
		<c:when test="${not empty command.importableParticipants}">
			<chrome:division id="particpant_will_load" title="Valid records">
			<tags:instructions code="admin.import.reviewSubmit.willImport"/>
			<div class="green-means-go">
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
			</div>
	   		</chrome:division>
		</c:when>
		<c:otherwise>
			<c:set var="importDisabled" value="disabled" scope="request"/>
		</c:otherwise>
	</c:choose>
			
	<c:if test="${not empty command.nonImportableParticipants}">
		<chrome:division id="particpant_will_not_load" title="Invalid records">
		<tags:instructions code="admin.import.reviewSubmit.wontImport"/>
		<div class="red-means-stop">
			<table id="test" width="100%" class="tablecontent">
		 			<tr>
		 				<th scope="col" align="left"><b>Name</b> </th>
		 				<th scope="col" align="left"><b>Assigned to Study</b> </th>
					<th scope="col" align="left"><b>Possible Problem</b> </th>
				</tr>
				<c:forEach var='item' items='${command.nonImportableParticipants}'>
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
									<c:out value=" ( " />
									<c:out value="${assignment.studySite.study.primaryIdentifierValue != null ? 
								 		assignment.studySite.study.primaryIdentifierValue :  'NA'}" />
								<c:out value =" ) " />	 		
								</c:forEach>
							</td>
							<td align="left">
								<ul>
								<c:forEach var='message' items='${item.messages}'>
									<li><font color="red"><c:out value='${message.message}'/></font></li>
								</c:forEach>
								</ul>
							</td>
						</tr>
					</c:forEach>
					<c:if test='${fn:length(command.nonImportableParticipants) == 0 }'>
						<tr class="results"><td align="left"><i>None</i></td></tr> 
					</c:if>
				</table>	
			</div>
		</chrome:division>
	</c:if>
</c:if>
