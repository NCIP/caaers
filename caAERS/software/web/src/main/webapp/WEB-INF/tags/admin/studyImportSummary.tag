<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:if test='${fn:length(command.nonImportableStudies) > 0 || fn:length(command.importableStudies) > 0}'>
<c:set var="flashMessage" value="Data imported successfully" scope="request"/>
<chrome:flashMessage/>
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
</c:if>
