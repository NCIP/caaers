<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
<title>Routing & Review || Course/Cycle Information</title>

<tags:dwrJavascriptLink objects="routingAndReview"/>

<script type="text/javascript">
	var curWin;
	function displayPopup(ety, etyId){
		var url = "listReviewComments?entity=#{entity}&entityId=#{entityId}&subview".interpolate({entity:ety, entityId:etyId});
	 	curWin = new Window({className:"alphacube",destroyOnClose:true,title:"",url: url, width: 800, height: 550,   recenterAuto:true});
        curWin.showCenter(true);
        
	}
	
	function showToolTip(text, title) {
         Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
     }
	
		
	function advanceWorkflow(valueSelected, wfId, entityId, entityType){
		
		var newStatus = valueSelected;
		if(valueSelected == '' || valueSelected == 'Please Select') return;
		if(confirm('Are you sure you want to take the action - ' + valueSelected + ' ?')){
			
			if(entityType == 'report'){
				var indicatorId = entityType + '-' + entityId + '-indicator';
						$(indicatorId).style.display='';
						routingAndReview.advanceWorkflow(wfId, newStatus, entityId, entityType , function(output){
				
							if(output.objectContent){
								var i = 0;
								var _actions = window['options_report_' + entityId];
								_actions.length = 0;
								for(i = 0; i< output.objectContent.length; i++){
									var status = output.objectContent[i];
									_actions.push(status);	
								}
								showRoutingReviewCourseMenuOptions(wfId, entityId);
								
								$(indicatorId).style.display='none';
								var entityStatusId = entityType + '-' + entityId + '-status';
								$(entityStatusId).innerHTML = output.htmlContent;
							}
						//	alert('The action: "' + newStatus + '" was taken.');
						});
			}
		
			// First we need to check if the entity type is "reportingPeriod"
			// Incase its reportingPeriod its validated
			// Only if the validation passes, the workflow is advanced
			// Otherwise a popup is displayed with the errors occured during validation.
			if(entityType == 'reportingPeriod'){
				routingAndReview.validateTransition(entityId, newStatus, function(output){
					if(output.objectContent){
						var i = 0;
						var popupContent = '';
						popupContent = '<table id="validation-table" width="100%" height="100%">';
						for(i = 0; i< output.objectContent.length; i++){
							var error = output.objectContent[i];
							popupContent = popupContent + '<tr><td width="10%"/><td width="80%" align="left">' + error + '</td><td width="10%"/></tr>';
						}
						popupContent = popupContent + '<tr><tr><tr><td width="10%"/><td align="left" width="80%"><font color="red">Note: Please save if you have unsaved data.</font></td><td width="10%"/></tr>';
						popupContent = popupContent + '</table>';
						$('validation-div').innerHTML = popupContent;
						var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"validation-popup-id", width:500,  height:230, top: 330, left: 500});
       		 			contentWin.setContent( 'reportingPeriod-validation-errors-popup' );
      	 	 			contentWin.showCenter(true);
       		 			popupObserver = {
 			     			onDestroy: function(eventName, win) {
      							if (win == contentWin) {
      								$('reportingPeriod-validation-errors-popup').style.display='none';
		   		   					contentWin = null;
      								Windows.removeObserver(this);
      							//	sb.selectedIndex = 0;
      							}
      						}
      					}
				        Windows.addObserver(popupObserver);
					}else{
						var indicatorId = entityType + '-' + entityId + '-indicator';
						$(indicatorId).style.display='';
						routingAndReview.advanceWorkflow(wfId, newStatus, entityId, entityType , function(output){
				
						if(output.objectContent){
			
							var i = 0;
							var _actions = window['options_' + entityId];
							_actions.length = 0;
							for(i = 0; i< output.objectContent.length; i++){
								var status = output.objectContent[i];
								_actions.push(status);
								
							}
							showRoutingReviewCourseMenuOptions(wfId, entityId);
							
							$(indicatorId).style.display='none';
							var entityStatusId = entityType + '-' + entityId + '-status';
							$(entityStatusId).innerHTML = output.htmlContent;
						}
					//	alert('The action: "' + newStatus + '" was taken.');
					});
				}
			});
		}
//	sb.enable();
		}else{
			//sb.value = 'Please Select';
			return false;
		}
	}
	

	function showRoutingReviewCourseMenuOptions(workflowId, entityId,entityType, aeReportId, aeStatus) {
		
		var html="";
		if(entityType == 'report'){
				
			var _element = $('course_routingreview_reportcycle_' + entityId);
			var _actions = window['options_report_' + entityId];
			var reportURL = '<c:url value= "/pages/ae/reviewResolver" />';
			reportURL = reportURL
					+ '?aeReport=' + aeReportId
					+ '&report=' + entityId
					+ '&viewOnly=true&src=RoutingReview" />';
					
			var _optionDetails = "";
			
			if ( aeStatus != 'COMPLETED') {
				_optionDetails = "<li><a class='submitter-blue' href='" + reportURL + "' >"	+ "View" + "</a></li>";
			}
			for ( var i = 0; i < _actions.length; i++) {
				_optionDetails = _optionDetails
								+ '<li><a class="submitter-blue" href="#" onclick="advanceWorkflow('
								+ "'" + _actions[i] + "' , ";
				_optionDetails = _optionDetails + workflowId + ", ";
				_optionDetails = _optionDetails + entityId + ',';
				_optionDetails = _optionDetails + "'" +entityType + "'";
				_optionDetails = _optionDetails + ')">';
				_optionDetails = _optionDetails + _actions[i] + '</a></li>';
				_optionDetails = _optionDetails + "\n";
			}
			 html = "<div><ul style='font-family:tahoma;'>" + _optionDetails
				+ "</ul></div>";
			
			 jQuery(_element).menu({
					content : html,
					maxHeight : 180,
					width : 180,
					positionOpts : {
						directionV : 'down',
						posX : 'left',
						posY : 'bottom',
						offsetX : 0,
						offsetY : 0
					},
					showSpeed : 300
				});	
				
		} else if (entityType == 'reportingPeriod') {

			var _element = $('course_routingreview_cycle_' + entityId);
			var _actions = window['options_' + entityId];

			var reportingPeriodPageURL = '<c:url value= "/pages/ae/reviewResolver" />';
			reportingPeriodPageURL = reportingPeriodPageURL
					+ '?adverseEventReportingPeriod=' + entityId
					+ '&src=RoutingReview" />';

			var _optionDetails = "<li><a class='submitter-blue' href='" + reportingPeriodPageURL + "' >"
					+ "Edit" + "</a></li>";
			for ( var i = 0; i < _actions.length; i++) {
				_optionDetails = _optionDetails
						+ '<li><a class="submitter-blue" href="#" onclick="advanceWorkflow('
						+ "'" + _actions[i] + "' , ";
				_optionDetails = _optionDetails + workflowId + ", ";
				_optionDetails = _optionDetails + entityId + ',';
				_optionDetails = _optionDetails + "'" +entityType + "'";
				_optionDetails = _optionDetails + ')">';
				_optionDetails = _optionDetails + _actions[i] + '</a></li>';
				_optionDetails = _optionDetails + "\n";
			}
			html = "<div><ul style='font-family:tahoma;'>" + _optionDetails
					+ "</ul></div>";
			jQuery(_element).menu({
				content : html,
				maxHeight : 180,
				width : 180,
				positionOpts : {
					directionV : 'down',
					posX : 'left',
					posY : 'bottom',
					offsetX : 0,
					offsetY : 0
				},
				showSpeed : 300
			});
		
		}
		
	}
</script>
</head>
<body>
<tags:standardForm title="Course/Cycle Information">
<jsp:attribute name="singleFields">
<div class="eXtremeTable" >
	<c:if test="${courseRetired == 'true'}">
		<div>		
			<tags:instructions code="course_retired_instruction"/>
		</div>
	</c:if>
	<c:if test="${command.searchResultsDTO.resultCount gt 0}">
		<c:if test="${command.searchCriteriaNeitherStudyNorParticipantCentric == false}">
			<div class="row">
				<div class="summarylabel">
					${command.searchCriteriaParticipantCentric  ? 'Subject' : 'Study'}
				</div>
				<div class="summaryvalue shorty">
					${command.searchResultsDTO.header}
				</div>
   			</div>
   		</c:if>
    	<table width="100%" cellspacing="1" cellpadding="0" border="0">
	   		<tr>
				<td style="padding-top:15px;padding-left:15px;">
    				<tags:paginationControl isFirstPage="${isFirstPage}" isLastPage="${isLastPage}"/>
					<div style="color:#808080">
						${totalResults } results found, displaying ${startIndex } to ${endIndex }
					</div>
  				</td>
			</tr>
			<tr>
				<td witdh="100%">
					<c:forEach items="${command.searchResultsDTO.filteredResultMap}" var="resultEntry">
						<c:if test="${command.searchCriteriaNeitherStudyNorParticipantCentric == false}">
							<div class="row" style="margin-top:30px; margin-bottom:0;">
								<div class="summarylabel">
									${command.searchCriteriaParticipantCentric  ? 'Study' : 'Subject'}
								</div>
								<div class="summaryvalue shorty">
									${resultEntry.value.header}
								</div>
							</div>
						</c:if>
						<c:if test="${command.searchCriteriaNeitherStudyNorParticipantCentric == true}">
							<c:if test="${resultEntry.value.groupHeader != null and resultEntry.value.groupHeader != ''}">
								<div class="row">
									<div class="summarylabel">
										Study
									</div>
									<div class="summaryvalue shorty">
										${resultEntry.value.groupHeader}
									</div>
								</div>
							</c:if>
							<div class="row" style="margin-top:30px; margin-bottom:0;">
								<div class="summarylabel">
									Subject
								</div>
								<div class="summaryvalue shorty">
									${resultEntry.value.header}
								</div>
							</div>
						</c:if>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
   	 						<thead>
      							<tr align="center" class="label">
       								<td width="2%" class="tableHeader"></td>
       						 		<td width="18%" class="tableHeader">Course</td>
       				 				<td width="22%" class="centerTableHeader">Treatment Type</td>
       						 		<td width="25%" class="centerTableHeader">Review Status</td>
        							<td width="8%" class="tableHeader">Comments</td>
        							<td width="25%" class="centerTableHeader">Action</td>
      							</tr>
    						</thead>
							<c:forEach items="${resultEntry.value.results}" var="rp" varStatus="rpStatus">
								<ae:oneRoutingReportingPeriodRow index="${rpStatus.index}" reportingPeriod="${rp}" />
							</c:forEach>
						</table>
					</c:forEach>
				</td>
			</tr>
		</table>
  	</c:if>
  	<c:if test="${command.searchResultsDTO.resultCount lt 1}">
		<b style="display:block; margin-left:20px; margin-top:10px;"><img src="<chrome:imageUrl name="../error-yellow.png" />" alt="Alert" /> No results found!</b>
  	</c:if>
</div>
<div id="reportingPeriod-validation-errors-popup" style="display:none" >
	<chrome:box title="Validation Errors">
		<table width="100%" height="100%">
			<tr>
				<td align="center" style="vertical-align:middle">
					<div id="validation-div">
					</div>
				</td>
			</tr>
		</table>
	</chrome:box>
</div>
</jsp:attribute>
</tags:standardForm>
</body>
</html>
