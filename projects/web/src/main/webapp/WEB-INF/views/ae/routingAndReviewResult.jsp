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
.report-row {
	background-color: #FFDADA;
}
.notify-unit.success {
	color: #090;
}
.notify-unit.failure {
	color: #900;
}
.eXtremeTable .centerTableHeader {
	background-color: #2b4186;
	color: white;
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
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
}
.eXtremeTable .eXtremeTable {
	border:1px solid silver;
	padding:2px;
	background-color:#FFFFFF;
	font-size:12px;
	font-weight:normal;
}
.eXtremeTable .eXtremeTable .even{
background-color:#ffdada;
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
	background-color: #2b4186;
	color: white;
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
	background-image: url(../../images/blue/eXtableheader_bg.png);
	background-repeat: repeat-x;
	background-position: top;
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
.eXtremeTable .even {
  	background-color: #abc4d8;
}
.eXtremeTable a:hover{
color:#0033FF;
}
.divNotes, .divOtherMeddra {
float:left;
font-size:8pt;
margin-top:5px;
}
</style>
<script type="text/javascript">
	var curWin;
	function displayPopup(ety, etyId){
		var url = "listReviewComments?entity=#{entity}&entityId=#{entityId}&subview".interpolate({entity:ety, entityId:etyId});
	 	curWin = new Window({className:"alphacube",destroyOnClose:true,title:"",url: url, width: 800, height: 550,   recenterAuto:true});
        curWin.showCenter(true);
        
	}

	function advanceWorkflow(selectBox,wfId, entityId, entityType){
		var sb = $(selectBox);
		var newStatus = sb.value;
		sb.disable();
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
					sb.enable();	
					$(indicatorId).style.display='none';
					var entityStatusId = entityType + '-' + entityId + '-status';
					$(entityStatusId).innerHTML = output.htmlContent;
			}
		});
		
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
        			<td width="18%" class="tableHeader">Evaluation Period</td>
        			<td width="22%" class="centerTableHeader">Evaluation Period Type</td>
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
</body>
</html>