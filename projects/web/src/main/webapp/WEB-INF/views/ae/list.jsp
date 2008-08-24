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
			  alert('here');
			window.location = url;
			
		}else if(action =='amend'){
			var url = '<c:url value="/pages/ae/edit"/>' +'?aeReport=' + aeReportId + '&reportId=' + reportId;
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
				<c:if test="${fn:length(command.assignment.reportingPeriods) gt 0}">
					<thead>
						<tr align="center" class="label">
							<td width="5%" class="tableHeader"></td>
							<td width="15%" class="tableHeader">Evaluation Periods</td>
							<td width="16%" class="tableHeader"># of Reports</td>
							<td width="16%" class="tableHeader"># of AEs</td>
							<td width="16%" class="tableHeader">Data Entry Status</td>
							<td width="16%" class="tableHeader">Report Status</td>
							<td width="16%" class="tableHeader">Options</td>
						</tr>
					</thead>
				    <c:forEach items="${command.assignment.reportingPeriods}" var="reportingPeriod" varStatus="rpStatus">
					    <ae:oneListReportingPeriodRow reportingPeriod="${reportingPeriod}" index="${rpStatus.index}"/>		
				    </c:forEach>
				</c:if>
			</table>
		</div>
</body>
</html>
