<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
    <style type="text/css">
.notify-unit.success {
	color: #090;
}
.notify-unit.failure {
	color: #900;
}
.eXtremeTable .centerTableHeader {
	background-color: #DFE4E8;
	color: black;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 17px;
	font-weight: bold;
	text-align: center;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/manage-reports-gradient.png);
	background-repeat: repeat-x;
	background-position: bottom;
}
.eXtremeTable .eXtremeTable {
	border:1px solid silver;
	padding:2px;
	background-color:#FFFFFF;
	font-size:12px;
	font-weight:normal;
}
.eXtremeTable .eXtremeTable .even{
	background-color:#FFEFEF;
}
.eXtremeTable .eXtremeTable .eXtremeTable .even{
	background-color:#fff;
}
.eXtremeTable .odd td, .eXtremeTable .even td {
	padding-top: 7px;
	padding-right: 3px;
	padding-bottom: 7px;
	padding-left: 3px;
	vertical-align: middle;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 16px;
	font-weight:bold;
}
.eXtremeTable .eXtremeTable .odd td, .eXtremeTable .eXtremeTable .even td {
	padding-top: 6px;
	padding-right: 3px;
	padding-bottom: 6px;
	padding-left: 3px;
	vertical-align: middle;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 12px;
	font-weight: normal;
	border-top:none;
}
.eXtremeTable .highlight td {
	color: black;
	padding-top: 7px;
	padding-right: 3px;
	padding-bottom: 7px;
	padding-left: 3px;
	vertical-align: middle;
	background-color: #F09B5D;
	font-size: 16px;
	font-weight:bold;
}
.eXtremeTable .eXtremeTable .highlight td {
	color:black;
	padding-top: 6px;
	padding-right: 3px;
	padding-bottom: 6px;
	padding-left: 3px;
	vertical-align: middle;
	background-color:#f09b5d;
	font-size: 12px;
	font-weight: normal;
}
.eXtremeTable .tableHeader {
	background-color: #DFE4E8;
	color: black;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 17px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/manage-reports-gradient.png);
	background-repeat: repeat-x;
	background-position: bottom;
}
.eXtremeTable .eXtremeTable .tableHeader {
	background-color: #2b4186;
	color: white;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 13px;
	font-weight: normal;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
}
.eXtremeTable .eXtremeTable .centerTableHeader {
	background-color: #2b4186;
	color: white;
	font-family: Arial, verdana, helvetica, sans-serif;
	font-size: 13px;
	font-weight: normal;
	text-align: center;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4px;
	padding-bottom: 4px;
	margin: 0px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
}
.eXtremeTable .allAEs .tableHeader {
	background-color:#DE5900;
	background-image: url(../../images/blue/eXtable_allAE_header_bg.png);
	background-repeat: repeat-x;
	color:black;
}
.eXtremeTable .allAEs .centerTableHeader {
	background-color:#DE5900;
	background-image: url(../../images/blue/eXtable_allAE_header_bg.png);
	background-repeat: repeat-x;
	color:black;
	text-align:center;
}
.eXtremeTable .odd {
	background-color: #fff;
}
.eXtremeTable .eXtremeTable .odd {
	background-color: #eaeaea;
}
.eXtremeTable a:hover{
color:#0033FF;
}
</style>
<script>
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
                } else {
                    ValidationManager.setInvalidState($(mode.basename + "-input"));
                }
            }
            $(mode.basename + "-input").onchange = function() {
                if (!$(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setInvalidState($(mode.basename + "-input"));
                }
            }
        }
</script>
</head>
<body>
<tags:standardForm title="Enter search criteria">
    <jsp:attribute name="instructions" />
    <jsp:attribute name="singleFields">
    	<input type="hidden" name="_action" value="finish"/>
    	<input type="hidden" name="searchName" value=""/>
    	<input type="hidden" name="searchDescription" value=""/>
    	<c:if test="${command.searchTargetObject == null}">
    		<table><tr><td width="75%" valign="top" align="left">
			   	<b>Search for:</b> <SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
					<OPTION selected value="none">Please select</OPTION>
					<c:forEach items="${advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
							<OPTION value="${searchTargetObject.className }">${searchTargetObject.displayName }</OPTION>
					</c:forEach>
				</SELECT>
				<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
			</td><td width="25%" valign="top" align="right" id="savedSearchId" style="${fn:length(shortSearchList) > 0 ? '' : 'display:none'}">
				<chrome:box title="Saved searches">
					<table id="shortSavedSearchListTable">
						<c:forEach items="${shortSearchList }" var="search" varStatus="searchStatus">
							<search:oneSavedSearch search="${search}"></search:oneSavedSearch>
						</c:forEach>
						<c:if test="${fn:length(savedSearchList) > 5}">
							<tr>
								<td><a onclick="javascript:advancedSearchHelper.renderFullSearchList();" href="#">more</a></td><td/>
							</tr>
						</c:if>
					</table>
				</chrome:box>
			</td></tr></table>
			<br><br>
			<div id="criteria-section-id">
			</div>
		</c:if>
		<c:if test="${command.searchTargetObject != null}">
			<table><tr><td width="75%" valign="top" align="left">
				<b>Search for:</b> <SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
					<OPTION selected value="none">Please select</OPTION>
					<c:forEach items="${advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
						<OPTION value="${searchTargetObject.className }" <c:if test="${searchTargetObject.className == command.searchTargetObject.className }"> selected </c:if>>${searchTargetObject.displayName }</OPTION>
					</c:forEach>
				</SELECT>
				<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
				</td><td width="25%" valign="top" align="right" id="savedSearchId" style="${fn:length(shortSearchList) > 0 ? '' : 'display:none'}">
				<chrome:box title="Saved searches">
					<table id="shortSavedSearchListTable">
						<c:forEach items="${shortSearchList }" var="search" varStatus="searchStatus">
							<search:oneSavedSearch search="${search}"></search:oneSavedSearch>
						</c:forEach>
						<c:if test="${fn:length(savedSearchList) > 5}">
							<tr>
								<td><a onclick="javascript:advancedSearchHelper.renderFullSearchList();" href="#">more</a></td><td/>
							</tr>
						</c:if>
					</table>
				</chrome:box>
			</td></tr></table>
	
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
				<div align="right">
					<tags:button color="green" type="button" id="save-search" value="Save search" onclick="javascript:advancedSearchHelper.renderSaveSearchPopup();"/>
  					<tags:button color="green" type="submit" id="flow-update" value="Submit"/> 
				</div>
			</div>
		</c:if>
		<div id="save_search_popup" style="display:none;text-align:left" >
			<chrome:box title="Search details" id="popupId">
				<div>
					<div class="row">
				    	<div class="summarylabel">Search name</div>
				        <div class="summaryvalue"><ui:text path="searchName" size="20" mandatory="true"/></div>
					</div>
				    <div class="row">
				    	<div class="summarylabel">Search description</div>
				    	<div class="summaryvalue"><ui:textarea path="searchDescription" rows="3" cols="40"/></div>
				    </div>
				</div>
				<div align="right">
					<tags:button size="small" color="blue" id="save-button" type="button" value="Save"  onclick="javascript:advancedSearchHelper.saveSearch();" />
				</div>
			</chrome:box>
		</div>
		<div id="search_list_popup" style="display:none">
			<chrome:box title="Saved searches" id="searchListPopupId">
				<div>
					<table width="100%">
						<c:forEach items="${savedSearchList }" var="search" varStatus="searchStatus">
							<search:oneSavedSearch search="${search}"></search:oneSavedSearch>
						</c:forEach>
					</table>
				</div>
			</chrome:box>
		</div>
	</jsp:attribute>
</tags:standardForm>
</body>
</html>