<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
	<head>
		<tags:dwrJavascriptLink objects="advSearch"/>
		<script type="text/javascript">
			var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
			
			function acCreate(mode) {
     	    	new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices", mode.populator, {
       	    		valueSelector: mode.valueSelector,
       	    		afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
            			acPostSelect(mode, selectedChoice)
                	},
                	indicator: mode.basename + "-indicator"
            	})
            	Event.observe(mode.basename + "-clear", "click", function() {
               		Element.addClassName($(mode.basename + "-input"), "required");
                	Element.removeClassName($(mode.basename + "-input"), "validField");
                	Element.removeClassName($(mode.basename + "-input"), "valueOK");
                	//$(mode.basename + "-selected").hide()
                	$(mode.basename).value = ""
                	$(mode.basename + "-input").value = ""
            	})
        	}
	
			function acPostSelect(mode, selectedChoice) {
            	Element.removeClassName($(mode.basename + "-input"), "required");
            	Element.removeClassName($(mode.basename + "-input"), "valueOK");
            	Element.addClassName($(mode.basename + "-input"), "validField");
            	$(mode.basename).value = selectedChoice.id;
            	$(mode.displayName).value = $(mode.basename + "-input").value;
            	new Effect.Highlight(mode.basename + "-selected")
            	$(mode.basename + "-input").onblur = function() {
                	if ($(mode.basename + "-input").hasClassName('validField')) {
                    	ValidationManager.setNormalState($(mode.basename + "-input"));
                	}else {
                    	ValidationManager.setInvalidState($(mode.basename + "-input"));
                	}
            	}
            	$(mode.basename + "-input").onchange = function() {
                	if (!$(mode.basename + "-input").hasClassName('validField')) {
                		ValidationManager.setInvalidState($(mode.basename + "-input"));
                	}
            	}
        	}
        
        function updateSearchTargetObject(){
			$('criteria-section-id').style.display = 'none';
			$('targetObjectProgessIndicator').style.display = '';
			advSearch.updateSearchTargetObject($('target-object-id').value , function(ajaxOutput) {
				$('criteria-section-id').innerHTML = ajaxOutput.htmlContent;
				//new Insertion.Top($('criteria-section-id'), ajaxOutput.htmlContent);
				$('criteria-section-id').style.display='';
				$('targetObjectProgessIndicator').style.display = 'none';
			});
		}
		
		function updateAttribute(index){
		
			var attributeId = 'attribute-' + index;
			var attributeValue = $(attributeId).value;
			var selectedIndex = $(attributeId).selectedIndex;
			var attributeText = $(attributeId)[selectedIndex].text; 
			var operatorSelectId = 'operator-' + index;
			var selectElement = $(operatorSelectId);
			var valueTdId = 'value-td-' + index;
			selectElement.options.length = 0;
			selectElement.options[0] = new Option('Please select', 'none');
				
			// This is if the value is set to 'Please select'
			if(attributeValue == 'none'){
				$(valueTdId).innerHTML = '';		
			}else{
				advSearch.updateAttribute(attributeValue, attributeText, index, function(ajaxOutput){
					// here we have to update the dropdown options of the respective operator dropdown with the the values in ajaxOutput
					// and the html content of the value td should be updated with the htmlContent of ajaxOutput
					for(i = 0; i< ajaxOutput.objectContent.length; i++){
						var operator = ajaxOutput.objectContent[i];
						selectElement.options[i+1] = new Option(operator.displayUri, operator.name);
					}
					$(valueTdId).innerHTML = '';
					new Insertion.Top($(valueTdId), ajaxOutput.htmlContent);
				});
			}
		}
		
		function updateOperator(index){
			//alert('update operator');
		}
		
		function addCriteria(dependentObjectDisplayName){
			advSearch.addCriteria(dependentObjectDisplayName, function(ajaxOutput){
				var blankRowId = dependentObjectDisplayName + '-blank-row';
				$(blankRowId).insert({before: ajaxOutput.htmlContent});
			});	
		}
	
		function deleteCriteria(index){
			advSearch.deleteCriteria(index, function(ajaxOutput){
				var rowId = 'criteria-' + index;
				$(rowId).style.display = 'none';
			});
		}
		
		</script>
	</head>
	<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" hideBox="true">
			<jsp:attribute name="singleFields">
	      		<input type="hidden" name="_action" id="_action" value="">
	      		<c:if test="${command.searchTargetObject == null}">
    				<div>
					   	<b>Search for:</b> 
					   		<SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:updateSearchTargetObject();">
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
					   	<SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:updateSearchTargetObject();">
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
							<c:if test="${dependentObject.hidden == false}">
								<c:set var="expandChromeDivision" value="true"/>
								<c:forEach items="${command.criteriaParameters}" var="p" varStatus="parameterStatus">
									<c:if test="${p.objectName != null && p.attributeName != null && p.predicate != null && p.value != null && !p.deleted}">
										<c:if test="${p.dependentObjectName == dependentObject.className}">
											<c:set var="expandChromeDivision" value="false"/>
										</c:if>
									</c:if>
								</c:forEach>
								<chrome:division title="${dependentObject.displayName} search criteria" collapsable="true" collapsed="${expandChromeDivision}" id="${dependentObject.displayName}">
									<div class="eXtremeTable" id="${dependentObject.displayName}-div-id" style="text-align:left">
										<table width="100%" border="0" cellspacing="0" class="tableRegion">
											<tr align="center" class="label">
												<td class="centerTableHeader" width="25%">Attribute</td>
												<td class="centerTableHeader" width="25%">Operator</td>
												<td class="centerTableHeader" width="40%">Value</td>
												<td width="10%" class="centerTableHeader"/>
											</tr>
											<c:forEach items="${command.criteriaParameters }" varStatus="parameterStatus" var="criteriaParameter">
												<c:if test="${dependentObject.className == criteriaParameter.dependentObjectName && !criteriaParameter.deleted}">
													<search:oneCriteriaRow index="${parameterStatus.index}" dependentObject="${dependentObject}" criteriaParameter="${criteriaParameter}" searchTargetObject="${command.searchTargetObject }"/>
												</c:if>
											</c:forEach> 
											<tr id="${dependentObject.displayName }-blank-row"/>
										</table>
										<tags:button size="small" color="blue" icon="add" id="${dependentObject.displayName}-add-button" type="button" value="Add"  onclick="javascript:addCriteria('${dependentObject.displayName }');" />
										<br><br><br>
									</div>
								</chrome:division>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>