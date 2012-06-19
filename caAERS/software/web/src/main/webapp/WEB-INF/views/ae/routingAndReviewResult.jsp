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
	
	function advanceWorkflow(selectBox,wfId, entityId, entityType){
		var sb = $(selectBox);
		var newStatus = sb.value;
		if(sb.value == '' || sb.value == 'Please Select') return;
		if(confirm('Are you sure you want to take the action - ' + sb.value + ' ?')){
			sb.disable();
			
			if(entityType == 'report'){
				var indicatorId = entityType + '-' + entityId + '-indicator';
						$(indicatorId).style.display='';
						routingAndReview.advanceWorkflow(wfId, newStatus, entityId, entityType , function(output){
				
							if(output.objectContent){
								sb.options.length = 0;
								var pleaseSelectOpt = new Option('Please select', 'Please select');
								sb.options.add(pleaseSelectOpt);
								var i = 0;
								for(i = 0; i< output.objectContent.length; i++){
									var status = output.objectContent[i];
									var opt = new Option(status, status);
									
									sb.options.add(opt);
								}
								$(indicatorId).style.display='none';
								var entityStatusId = entityType + '-' + entityId + '-status';
								$(entityStatusId).innerHTML = output.htmlContent;
							}
							alert('The action: "' + newStatus + '" was taken.');
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
      								sb.selectedIndex = 0;
      							}
      						}
      					}
				        Windows.addObserver(popupObserver);
					}else{
						var indicatorId = entityType + '-' + entityId + '-indicator';
						$(indicatorId).style.display='';
						routingAndReview.advanceWorkflow(wfId, newStatus, entityId, entityType , function(output){
				
						if(output.objectContent){
							sb.options.length = 0;
							var pleaseSelectOpt = new Option('Please Select', 'Please Select');
							sb.options.add(pleaseSelectOpt);
							var i = 0;
							for(i = 0; i< output.objectContent.length; i++){
								var status = output.objectContent[i];
								var opt = new Option(status, status);
								
								sb.options.add(opt);
							}
							$(indicatorId).style.display='none';
							var entityStatusId = entityType + '-' + entityId + '-status';
							$(entityStatusId).innerHTML = output.htmlContent;
						}
						alert('The action: "' + newStatus + '" was taken.');
					});
				}
			});
		}
		sb.enable();
		}else{
			sb.value = 'Please Select';
			return false;
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
				<div class="summaryvalue">
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
								<div class="summaryvalue">
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
									<div class="summaryvalue">
										${resultEntry.value.groupHeader}
									</div>
								</div>
							</c:if>
							<div class="row" style="margin-top:30px; margin-bottom:0;">
								<div class="summarylabel">
									Subject
								</div>
								<div class="summaryvalue">
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
