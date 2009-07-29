<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>


<c:forEach items="${command.searchTargetObject.dependentObject}" varStatus="status" var="dependentObject">
	<c:if test="${dependentObject.hidden == false}">
		<chrome:division title="${dependentObject.displayName} search criteria" collapsable="true" collapsed="${dependentObject.className != command.searchTargetObject.className}" id="${dependentObject.displayName}">
			<div class="eXtremeTable" id="${dependentObject.displayName}-div-id" style="text-align:left">
				<table width="100%" border="0" cellspacing="0" class="tableRegion">
					<tr align="center" class="label">
						<td class="centerTableHeader" width="25%">Attribute</td>
						<td class="centerTableHeader" width="25%">Operator</td>
						<td class="centerTableHeader" width="40%">Value</td>
						<td width="10%" class="centerTableHeader"/>
					</tr>
					<search:oneCriteriaRow index="${status.index}" dependentObject="${dependentObject}" searchTargetObject="${command.searchTargetObject}"/> 
					<tr id="${dependentObject.displayName }-blank-row"/>
				</table>
				<tags:button size="small" color="blue" icon="add" id="${dependentObject.displayName}-add-button" type="button" value="Add"  onclick="javascript:addCriteria('${dependentObject.displayName }');" />
				<br><br><br>
			</div>
		</chrome:division>
	</c:if>
</c:forEach>
