<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Track Reports</title>
<tags:dwrJavascriptLink objects="reportDef"/>

<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<script type="text/javascript">
	var _collapsedELs = new Array();
	window.onload = function () { 
		Event.observe('actions', 'change', showAndHideFields);
		showAndHideFields();		
	}
	
	
	
	function showAndHideFields(){
		
		if ($('actions').value == 'report') {
			$('reportIdDiv').show();
			$('datesDiv').hide();
		} else if ($('actions').value == 'daterange') {
			$('reportIdDiv').hide();
			$('datesDiv').show();
		} else {
			$('reportIdDiv').hide();
			$('datesDiv').hide();		
		}

	}
	
	function showToolTip(text, title) {
        Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
    }
    
    function resetReports() {
    			try {
					reportDef.resetReports(function(values) { 
						alert("Reports stuck for more than 5 minutes are reset to 'Failed' status , Please resubmit.");
					})
				} catch(e) {alert(e)}
	}
</script>
</head>



<body>
<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>
<div class="workflow-tabs2">
    <ul id="" class="tabs autoclear">
        <li id="thirdlevelnav" class="tab"><div><a href="ctepesysDataImport">CTEP-ESYS Data Import</a></div></li>
        <li id="thirdlevelnav" class="tab selected"><div><a href="#">Report Submission Logs</a></div></li>
        <li id="thirdlevelnav" class="tab"><div><a href="ctepesysDataIntegrationLogs">CTEP-ESYS Data Integration Logs</a></div></li>
    </ul>
</div>


<form:form name="command" id="command" method="post">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
<chrome:box title="Search" autopad="true">
   
        <div class="content">
        
        
              <table>
            		<tr>
            			<td>
                    		<tags:renderRow field="${fieldGroups.main.fields[0]}"/>
            			</td>
            			<td>
            				<tags:button color="blue" type="submit" value="Find" size="small" icon="search"/>
            			</td>
            		</tr>
            		<tr id="reportIdDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[1]}"/>
            			</td>
            		</tr>
            		<tr id="datesDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[2]}"/>
	            				<tags:renderRow field="${fieldGroups.main.fields[3]}"/>
            			</td>
            		</tr>   
             		<tr id="reportResetDiv">
            			<td>
	            				<a href="javascript:resetReports()">Reset Stuck Reports ('Submission to AdEERS in process') </a>
            			</td>
            		</tr>           		         		
             </table>	

        </div>
    
</chrome:box>





<div class="eXtremeTable" >
   <table width="100%" cellspacing="1" cellpadding="0" border="0">
   
   	   		<tr>
				<td style="padding-top:15px;padding-left:15px;">
    				<tags:paginationControlTracking isFirstPage="${isFirstPage}" isLastPage="${isLastPage}"/>
					<div style="color:#808080">
						${totalResults } results found, displaying ${startIndex } to ${endIndex }
					</div>
  				</td>
			</tr>
			
			<tr>
				<td witdh="100%">

					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
					    <thead>
					      <tr align="center" class="label">
					        <td width="2%" class="tableHeader"></td>
					        <td width="10%" class="tableHeader">Report ID</td>
					        <td width="13%" class="tableHeader">Amendment #</td>
					        <td width="25%" class="tableHeader">Submitter</td>
					        <td width="15%" class="tableHeader">Status Date</td>
					        <td width="20%" class="tableHeader">Submission Status</td>
					        <td width="15%" class="tableHeader">More Info</td>
					      </tr>
					    </thead>
				      <c:forEach items="${command.searchResultsDTO.filteredResultDto.results}" var="reportVersion" varStatus="rpStatus">
				        <ae:oneListReportForTracking reportVersion="${reportVersion}" index="${rpStatus.index}"/>
				      </c:forEach>	
				   </table>

				</td>
			</tr>
  </table>
     
</div>
</form:form>
</body>
</html>
