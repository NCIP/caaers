<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="investigator" tagdir="/WEB-INF/tags/investigator"%>
<tags:noform>
<table id="siteInvestigatorTable" class="tablecontent" width="100%">
<tbody id="siteInvestigatorTable-body">
  			<tr id="site-investigator">
  				<th class="tableHeader" ><tags:requiredIndicator />Organization</th>
  				<th class="tableHeader" ><tags:requiredIndicator />Start date</th>
  				<th class="tableHeader" >End date</th>
  				<th class="tableHeader" >Status</th>
  				<th class="tableHeader" >Action</th>
  			</tr>
    				
		     <c:forEach var="siteInvestigator" varStatus="status" items="${command.siteInvestigators}">
			<investigator:siteInvestigator 	
								title="Associated Sites ${status.index + 1}" 
								enableDelete="${empty siteInvestigator.id}"
								sectionClass="site-investigator-row" 
								index="${status.index}" 
								active="${siteInvestigator.active}"
								orgName="${siteInvestigator.organization.name}"/>
						</c:forEach>
				
            	</tbody>
 </table>
 </tags:noform>      
