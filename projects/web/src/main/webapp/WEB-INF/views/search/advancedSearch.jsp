<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
                          s
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
</script>
</head>
<body>
<tags:standardForm title="Enter search criteria">
    <jsp:attribute name="instructions" />
    <jsp:attribute name="singleFields">
    	<input type="hidden" name="_finish" value="finish"/>
    	<c:if test="${command.searchTargetObject == null}">
	    	<b>Search for:</b> <SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
				<OPTION selected value="none">Please select</OPTION>
				<c:forEach items="${advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
						<OPTION value="${searchTargetObject.className }">${searchTargetObject.displayName }</OPTION>
				</c:forEach>
			</SELECT>
			<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
			<br><br>
			<div id="criteria-section-id">
			</div>
		</c:if>
		<c:if test="${command.searchTargetObject != null}">
			<b>Search for:</b> <SELECT style="width:200px;" id="target-object-id" name="actions" onChange="javascript:advancedSearchHelper.updateSearchTargetObject();">
				<OPTION selected value="none">Please select</OPTION>
				<c:forEach items="${advancedSearchUi.searchTargetObject}" var="searchTargetObject" varStatus="tartgetObjectStatus">
					<OPTION value="${searchTargetObject.className }" <c:if test="${searchTargetObject.className == command.searchTargetObject.className }"> selected </c:if>>${searchTargetObject.displayName }</OPTION>
				</c:forEach>
			</SELECT>
			<img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="targetObjectProgessIndicator">
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
  					<tags:button color="green" type="submit" id="flow-update" value="Submit"/> 
				</div>
			</div>
		</c:if>
	</jsp:attribute>
</tags:standardForm>
</body>
</html>