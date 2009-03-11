<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<html>
<head>
<title>Routing & Review</title>

<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<script src="js/prototype.js"></script>
<script src="js/common.js"></script>
<script src="js/scriptaculous/effects.js"></script>
<script src="js/scriptaculous/slider.js"></script>
<script src="js/scriptaculous/builder.js"></script>
<script src="js/scriptaculous/controls.js"></script>
<script src="js/scriptaculous/dragdrop.js"></script>
<script src="js/common-scriptaculous.js"></script>

<tags:includePrototypeWindow />
<tags:stylesheetLink name="extremecomponents"/>
<tags:dwrJavascriptLink objects="routingAndReview"/>

<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<style type="text/css">
.eXtremeTable select {
	font-size: 11px;
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
.eXtremeTable .even {
  	background-color: #abc4d8;
}
.eXtremeTable a:hover{
color:#0033FF;
}

</style>
<script type="text/javascript">
	var curWin;
	function displayPopup(ety, etyId){
		var url = "listReviewComments?entity=#{entity}&entityId=#{entityId}&subview".interpolate({entity:ety, entityId:etyId});
	 	curWin = new Window({className:"alphacube",destroyOnClose:true,title:"",url: url, width: 800, height: 550,   recenterAuto:true});
        curWin.showCenter(true);
        
	}
	
	function executeAction(reportId,url){
    	var actions = $("actions-"+reportId)
    	for ( i=0; i < actions.length; i++)
        {
        	if (actions.options[i].selected && actions.options[i].value != "none") {
            	window.open(url + "&format="+ actions.options[i].value,"_self")
            }
         }
     }

	function advanceWorkflow(selectBox,wfId, entityId, entityType){
		var sb = $(selectBox);
		var newStatus = sb.value;
		sb.disable();
		
		if(entityType == 'aeReport'){
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
					for(i = 0; i< output.objectContent.length; i++){
						var error = output.objectContent[i];
						popupContent = popupContent + '<tr><td width="10%"/><td width="80%" align="left">' + error + '</td><td width="10%"/></tr>';
					}
					popupContent = popupContent + '<tr><tr><tr><td width="10%"/><td align="left" width="80%"><font color="red">Note: Please save if you have unsaved data.</font></td><td width="10%"/></tr>';
					$('validation-table').innerHTML = popupContent;
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
					});
				}
			});
		}
		sb.enable();
	}
	
</script>
</head>
<body>
<div class="eXtremeTable" >

  <c:if test="${command.searchResultsDTO.resultCount gt 0}">
	<div>
		${command.searchCriteriaParticipantCentric  ? 'Subject' : 'Study'} : ${command.searchResultsDTO.header}
    </div>    
	<c:forEach items="${command.searchResultsDTO.resultMap}" var="resultEntry">
		<div>
			${command.searchCriteriaParticipantCentric  ? 'Study' : 'Participant'} : ${resultEntry.value.header}
		</div>
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
  </c:if>
  <c:if test="${command.searchResultsDTO.resultCount lt 1}">
		No result found!
  </c:if>
</div>
<div id="reportingPeriod-validation-errors-popup" style="display:none" >
	<chrome:box title="Validation Errors">
		<table width="100%" height="100%">
			<tr>
				<td align="center" style="vertical-align:middle">
					<table id="validation-table" width="100%" height="100%">
					</table>
				</td>
			</tr>
		</table>
	</chrome:box>
</div>
</body>
</html>