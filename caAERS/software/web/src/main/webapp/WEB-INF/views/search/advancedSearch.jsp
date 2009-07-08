<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
	<head>
		<tags:dwrJavascriptLink objects="advSearch"/>
		<script>
			var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
		</script>
	</head>
	<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" hideBox="true">
			<jsp:attribute name="singleFields">
	      		<input type="hidden" name="_action" id="_action" value="">
	      		
	      		<c:if test="${command.searchTargetObject == null}">
    				<div>
					   	<b>Search for:</b> 
					   		<SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
								<OPTION selected value="none">Please select</OPTION>
								<c:forEach items="${command.advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
									<OPTION value="${searchTargetObject.className }">${searchTargetObject.displayName }</OPTION>
								</c:forEach>
							</SELECT>
						<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
					</div>
					<br><br>
					<div id="criteria-section-id">
					</div>
				</c:if>
				<c:if test="${command.searchTargetObject != null }">
					<div>
					   	<b>Search for:</b>
					   	<SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
							<OPTION selected value="none">Please select</OPTION>
							<c:forEach items="${command.advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
								<OPTION value="${searchTargetObject.className }" <c:if test="${searchTargetObject.className == command.searchTargetObject.className }"> selected </c:if>>${searchTargetObject.displayName }</OPTION>
							</c:forEach>
						</SELECT>
						<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
					</div>
					<br><br>
					<div id="criteria-section-id">
						<c:forEach items="${command.searchTargetObject.dependentObject}" varStatus="status" var="dependentObject">
							<chrome:division title="${dependentObject.displayName} search criteria" collapsable="true" collapsed="${dependentObject.className != command.searchTargetObject.className}" id="${dependentObject.displayName}">
								<div class="eXtremeTable" id="${dependentObject.displayName}-div-id" style="text-align:left">
									<table width="100%" border="0" cellspacing="0" class="tableRegion">
										<tr align="center" class="label">
											<td class="centerTableHeader" width="25%">Attribute</td>
											<td class="centerTableHeader" width="25%">Operator</td>
											<td class="centerTableHeader" width="40%">Value</td>
											<td width="10%" class="centerTableHeader"/>
										</tr>
										<c:forEach items="${command.criteriaParameters }" varStatus="parameterStatus" var="criteriaParameter">
											<c:if test="${dependentObject.className == criteriaParameter.objectName && !criteriaParameter.deleted}">
												<search:oneCriteriaRow index="${parameterStatus.index}" dependentObject="${dependentObject}" criteriaParameter="${criteriaParameter}"/>
											</c:if>
										</c:forEach> 
										<tr id="${dependentObject.displayName }-blank-row"/>
									</table>
									<br>
									<tags:button size="small" color="blue" icon="add" id="${dependentObject.displayName}-add-button" type="button" value="Add"  onclick="javascript:advancedSearchHelper.addCriteria('${dependentObject.displayName }');" />
								</div>
							</chrome:division>
						</c:forEach>
					</div>
				</c:if>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>