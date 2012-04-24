<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>CTEP-ESYS Data Integration Logs</title>
<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>


<style type="text/css">

    /*
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
    */

    
</style>

<script type="text/javascript">
	var _collapsedELs = new Array();
	window.onload = function () { 
		Event.observe('actions', 'change', showAndHideFields);
		showAndHideFields();		
	}
	
	function showAndHideFields(){
		
		if ($('actions').value == 'businessId') {
			$('businessIdDiv').show();
			$('datesDiv').hide();
			$('synchStatusDiv').hide();
			$('outcomeDiv').hide();
			$('entityDiv').hide();
		} else if ($('actions').value == 'daterange') {
			$('businessIdDiv').hide();
			$('datesDiv').show();
			$('synchStatusDiv').hide();
			$('outcomeDiv').hide();
			$('entityDiv').hide();
		} else if ($('actions').value == 'entity') {
			$('businessIdDiv').hide();
			$('datesDiv').hide();
			$('synchStatusDiv').hide();
			$('outcomeDiv').hide();
			$('entityDiv').show();	
		}else if ($('actions').value == 'synchStatus') {
			$('businessIdDiv').hide();
			$('datesDiv').hide();
			$('synchStatusDiv').show();
			$('outcomeDiv').hide();
			$('entityDiv').hide();
		} else if ($('actions').value == 'outcome') {
			$('businessIdDiv').hide();
			$('datesDiv').hide();
			$('synchStatusDiv').hide();
			$('outcomeDiv').show();
			$('entityDiv').hide();
		} 

	}
	
</script>
</head>



<body>
<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>
 <div class="tabpane">
     <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
  	<li id="thirdlevelnav" class="tab"><div>
        <a href="ctepesysDataImport">CTEP-ESYS Data Import</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="trackReports">Report Submission Logs</a>
    </div></li>
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="#">CTEP-ESYS Data Integration Logs</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="happy">System Status</a>
    </div></li>
  </ul>
</div>
         

<form:form name="command" id="command" method="post">
 
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
            		<tr id="entityDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[1]}"/>
            			</td>
            		</tr>
            		<tr id="outcomeDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[2]}"/>
            			</td>
            		</tr>  
            		<tr id="synchStatusDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[3]}"/>
            			</td>
            		</tr>            		
            		<tr id="datesDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[4]}"/>
	            				<tags:renderRow field="${fieldGroups.main.fields[5]}"/>
            			</td>
            		</tr> 
            		<tr id="businessIdDiv" style="display:none">
            			<td>
	            				<tags:renderRow field="${fieldGroups.main.fields[6]}"/>
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
					      	<td width="15%" class="tableHeader">Date</td>
					        <td width="10%" class="tableHeader">Entity</td>
					         <td width="15%" class="tableHeader">Business Id</td>
					        <td width="20%" class="tableHeader">Synch Status</td>
					        <td width="4%" class="tableHeader">Outcome</td>
					        <td width="20%" class="tableHeader">Notes</td>
					      </tr>
					    </thead>
					    <c:forEach items="${command.searchResultsDTO.filteredResults}" var="integrationLogDetail" varStatus="rpStatus">
				        <ae:oneListIntegrationLogDetail integrationLogDetail="${integrationLogDetail}" index="${rpStatus.index}"/>
				      </c:forEach>	
				     
				   </table>

				</td>
			</tr>
  </table>
     
</div>
</form:form>
</body>
</html>
