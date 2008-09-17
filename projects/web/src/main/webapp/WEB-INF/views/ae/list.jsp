<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<script src="js/prototype.js"></script>
<script src="js/common.js"></script>
<script src="js/scriptaculous/effects.js"></script>
<script src="js/scriptaculous/slider.js"></script>
<script src="js/scriptaculous/builder.js"></script>
<script src="js/scriptaculous/controls.js"></script>
<script src="js/scriptaculous/dragdrop.js"></script>
<script src="js/common-scriptaculous.js"></script>
<html>
<head>
<title>Manage AEs and Evaluation Periods</title>
<tags:stylesheetLink name="extremecomponents"/>
<tags:dwrJavascriptLink objects="createAE"/>
<tags:dwrJavascriptLink objects="adverseEventHistory"/>
<tags:labs labs="${command.assignment.labLoads}"/>
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<style type="text/css">
.notify-unit.success {
	color: #090;
}
.notify-unit.failure {
	color: #900;
}
.centerTableHeader {
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
</style>
<script type="text/javascript">

	function showDetails(elId){
		$(elId).show();
	}

	function hideDetails(elId){
		$(elId).hide();
	}
	
	function doAction(action, aeReportId,reportId) {

		if(action == 'withdraw'){
			//AE.showIndicator("notify-indicator-" + aeReportId)
	        createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
	           	//AE.hideIndicator("notify-indicator-" + aeReportId)
	           	var statusColumn = $("status"+reportId)
	     		var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";
	      
	      		var optionColumn = $("action"+reportId)
	      		optionColumnData = $("action"+reportId).innerHTML;
	      
	      		Element.update(statusColumn, statusColumnData)
	      		Element.update(optionColumn, optionColumnData)
	        });
	        
		}else if(action =='submit'){
			var url = '<c:url value="/pages/ae/submitReport?from=list" />'  + '&aeReport=' + aeReportId + '&reportId=' + reportId;
			window.location = url;
			
		}else if(action =='amend'){
			var url = '<c:url value="/pages/ae/edit"/>' +'?aeReport=' + aeReportId + '&reportId=' + reportId + '&action=amendReport';
			window.location = url; 
		}
        
    }  
    </script>
</head>
<body>
<!-- AE summary  -->
<c:if test="${not empty command.participant}">
  <div>
    <div class="row">
      <div class="summarylabel">Subject</div>
      <div class="summaryvalue">${command.participant.fullName}</div>
    </div>
    <div class="row">
      <div class="summarylabel">Study</div>
      <div class="summaryvalue">${command.study.longTitle}</div>
    </div>
  </div>
</c:if>
<div class="eXtremeTable" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
    <thead>
      <tr align="center" class="label">
        <td width="2%" class="tableHeader"></td>
        <td width="18%" class="tableHeader">Evaluation Period</td>
        <td width="16%" class="centerTableHeader"># of Reports</td>
        <td width="16%" class="centerTableHeader"># of AEs</td>
        <td width="16%" class="tableHeader">Data Entry Status</td>
        <td width="16%" class="tableHeader">Report Status</td>
        <td width="16%" class="tableHeader">Options</td>
      </tr>
    </thead>
    <c:if test="${fn:length(command.assignment.reportingPeriods) gt 0}">
      <c:forEach items="${command.assignment.reportingPeriods}" var="reportingPeriod" varStatus="rpStatus">
        <ae:oneListReportingPeriodRow reportingPeriod="${reportingPeriod}" index="${rpStatus.index}"/>
      </c:forEach>
    </c:if>
  </table>
  <c:set var="reportingPeriodPageURLNoPeriod" value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&_target1=1&displayReportingPeriod=true"/>
  <c:if test="${fn:length(command.assignment.reportingPeriods) le 0}"> Evaluation period not created yet. Click 
  <a href="<c:url value="${reportingPeriodPageURLNoPeriod}"/>">here</a>
   to enter AEs.</c:if>
</div>
</body>
</html>
