<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

	<c:if test='${fn:length(command.nonImportableStudies) > 0 || fn:length(command.importableStudies) > 0}'>
		<c:choose> 
			<c:when test="${not empty command.importableStudies}">
				<chrome:division title="Valid records" id="study_will_load">
					<tags:instructions code="admin.import.reviewSubmit.willImport"/>
					
					<div class="green-means-go">
					<table id="test" width="100%" class="tablecontent">
		   		 		<tr>
		   	 				<th scope="col" align="left"><b>Study Identifier</b> </th>
		   	 				<th scope="col" align="left"><b>Study Short Title</b> </th>
		   	 			</tr>
		   	 			<c:forEach var='item' items='${command.importableStudies}'>
							<tr class="results">
								<td align="left">
									<c:out value="${item.importedDomainObject.primaryIdentifierValue != null ? 
												item.importedDomainObject.primaryIdentifierValue :  'NA'}" />
								</td>
		   						<td align="left"><c:out value="${item.importedDomainObject.shortTitle}" /></td>
		   						<td align="left" color="red">
		   							<ul>
		   							<c:forEach var='message' items='${item.messages}'>
		   								<li><c:out value='${message.message}'/></li>
		   							</c:forEach>
									</ul>
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
		<c:if test="${not empty command.nonImportableStudies}">
			<chrome:division id="study_will_not_load" title="Invalid records">
				<tags:instructions code="admin.import.reviewSubmit.wontImport"/>
				<div class="red-means-stop">
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
				</div>
	   		</chrome:division>
		</c:if>
	</c:if>
