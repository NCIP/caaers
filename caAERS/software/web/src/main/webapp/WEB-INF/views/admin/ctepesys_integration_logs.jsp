<%@include file="/WEB-INF/views/taglibs.jsp"%>
<tags:dwrJavascriptLink objects="ctepDataInitialization"/>
<html>
<head>
<title>CTEP-ESYS Data Integration Logs</title>
<link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<style type="text/css">

	/** 
	*
	* Style the yui-dt-expandablerow-trigger column 
	*
	**/ 
	
	th.yui-dt-col-service, th.yui-dt-col-loggedOn, th.yui-dt-col-overallStatus, th.yui-dt-col-notes {
    position: relative;
    background: #3882c1 url(<c:url value="/images/table/yui-datatable_header.jpg" />) repeat-x top;
    font-size: 13px;
    font-weight: bold;
    margin: 0px;
    color: white;
    text-shadow: 0 -1px #2166a1;
    height: auto;
    text-decoration: none
}

</style>

<script type="text/javascript">
    var correlationArray = [];
	function initializeYUITableNoPagination(tableId, responseData, columnDefs, fields) {
	    YAHOO.example.CellSelection = new function() {
	        var columDefs = columnDefs.clone();
	        var tableFields = fields.clone();
	
	        var activeDataSource = new YAHOO.util.DataSource(responseData);
	        var rowFormatter = function(elTr, oRecord) {
		        return true;
		    };
	        activeDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	        activeDataSource.responseSchema = {
	            fields: tableFields
	        };
	
	        var myConfigs = {
	            draggableColumns : false,
	            width: "100%",
	            formatRow : rowFormatter
	        };
	
	        this.activeDataTable = new YAHOO.widget.DataTable(tableId, columDefs, activeDataSource, myConfigs);
	        this.activeDataTable.subscribe("rowMouseoverEvent", this.activeDataTable.onEventHighlightRow);
	        this.activeDataTable.subscribe("rowMouseoutEvent", this.activeDataTable.onEventUnhighlightRow);
	    }
	}
	
        
	function ajaxCallBack(jsonResult) {
	  	$$("form .arbitrary").each(function(e){e.className='indicator';	});
	    $('integrationLogsBox').style.display="block";
	    correlationArray = [];
	    for(i=0 ; i < jsonResult.length; i++) {
	    	correlationArray.push(jsonResult[i].correlationId);
	    }
	    showLogsYUITable(jsonResult);
	}
	
	 function buildTable() {
	 	$$("form .indicator").each(function(e){e.className='arbitrary';	});
  		
  		// check if start date is valid
		var startDateParts = ($('startDate').value).split("/");
		var startDateParam = new Date(startDateParts[2],startDateParts[0]-1, startDateParts[1]);
		if ( Object.prototype.toString.call(startDateParam) === "[object Date]" ){
			if (isNaN( startDateParam.getTime())) { 
    			startDateParam = null; // date is not valid
  			} 
		} else  {
			startDateParam = null;
		}
		
		// check if end date is valid
		var endDateParts = ($('endDate').value).split("/");
		var endDateParam = new Date(endDateParts[2],endDateParts[0]-1, endDateParts[1],23,59,59);
		if ( Object.prototype.toString.call(endDateParam) === "[object Date]" ){
			if (isNaN( endDateParam.getTime())) { 
    			endDateParam = null; // date is not valid
  			} 
		} else  {
			endDateParam = null;
		}
		
        ctepDataInitialization.searchIntegrationLogs(startDateParam, endDateParam, $('status').value, $('service').value,ajaxCallBack);
    }
    
    
    var expansionRowTableGenerator = function(o) {
		tr = o.row_element,
		cells=o.row_element.cells;
		div = o.liner_element,
		data = o.data,
		state = o.state;
		var dataArray = o.data.getData("steps");
		div.innerHTML = CreateTableView(dataArray,true);
	};
	
	
		// Parameter Information
	// objArray = Anytype of object array, like JSON results
	// theme (optional) = A css class to add to the table (e.g. <table class="<theme>">
	// enableHeader (optional) = Controls if you want to hide/show, default is show
	function CreateTableView(objArray, enableHeader) {
	    if (enableHeader === undefined) {
	        enableHeader = true; //default enable headers
	    }
		    // table body
	 
	    var str = '<table class="tablecontent" cellpadding="1" cellspacing="0" align="center">';
	    
	     // table head
	    if (enableHeader) {
	        str += '<thead><tr class="taskTitleRow">';
	        str += '<th scope="col">Status</th>';
	        str += '<th scope="col">Result</th>';
	        str += '</tr></thead>';
	    }
	    
	    str += '<tbody>';
	    var i=0;
		for (var key in objArray) {
			str += (i % 2 == 0) ? '<tr class="alt">' : '<tr>';
			str += '<td>' + key + '</td>';
			if(objArray[key] == 'Success'){
				str += '<td> <img src="<c:url value="/images/chrome/../check.png" />"> </td>';
			} else {
				str += '<td> <img src="<c:url value="/images/chrome/../checkno.gif" />"> </td>';
			}
		    str += '</tr>';
			i = i +1;
		}
	    str += '</tbody>'
	    str += '</table>';
	    return str
	}
	
	 function showPopupMessage(row_id){
		ctepDataInitialization.getIntegrationLogDetailsBasedOnCorrelationId(correlationArray[row_id], ajaxCallBackForDetails);
	 }
	 
	 function ajaxCallBackForDetails(jsonResult) {
	 	var d = $('synchMessage');
	  	displayLogDetailsTable(jsonResult);
	  	Dialog.alert(d.innerHTML, {className: "alphacube", height:500, width:500, okLabel: "Close" });
	}
	
	var booleanFormatter = function(elCell, oRecord, oColumn, oData) {
	        	if(oData == true){
	        			elCell.innerHTML = 'Yes';
	        		}
	        	if(oData == false){
	        			elCell.innerHTML = 'No';
	        		}
	         }
	
	var myColumnDefsForDetails = [{key:"entity", label:"Entity", resizeable:true, minWidth:200, maxWidth:350},
							{key:"businessId", label:"BusinessId", sortable:true, resizeable:true, minWidth:200, maxWidth:350},
						{key:"failed", label:"Failed", sortable:true, resizeable:true, minWidth:300, maxWidth:350, formatter:booleanFormatter},
						{key:"outcome", label:"Notes", sortable:true, resizeable:true, minWidth:100, maxWidth:100}];
	
	var myFieldsForDetails = [{key:'entity', parser:"string"},{key:'businessId', parser:"string"},{key:'failed', parser:"boolean"},{key:'outcome', parser:"string"}];
	
	function displayLogDetailsTable(data) {
	   initializeYUITableNoPagination("tableDiv",data, myColumnDefsForDetails, myFieldsForDetails);
	}
    
      /* Modify as needed */ 
	 
	function showLogsYUITable(responseData) {
	    YAHOO.example.Basic = function() { 
	        var expansionFormatter  = function(el, oRecord, oColumn, oData) { 
	            var cell_element    = el.parentNode; 
	 
	            //Set trigger 
	            if( oData ){ //Row is closed 
	                YAHOO.util.Dom.addClass( cell_element, 
	                    "yui-dt-expandablerow-trigger" ); 
	            } 
	        }; 
	        
	         var rowFormatter = function(elTr, oRecord) {
	        	return true;
	    	  };
	    	  
	        var myDataSource = new 
	            YAHOO.util.DataSource(responseData); 
	            myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	            myDataSource.responseSchema = { 
	                fields: ["steps", "loggedOn","service","overallStatus","notes","entity","loggedOnDateStr"] 
	            }; 
	            
	        var actionFormatter = function(elCell, oRecord, oColumn, oData) {
	        	var row_number = oRecord._nCount;
	        	elCell.innerHTML = '<A  HREF="javascript:showPopupMessage(' + row_number + ')";>View Details</A> || <a href="logdownload?cstr=' + correlationArray[row_number]+ '&dstr=' + oRecord._oData.loggedOnDateStr + '&' + 'entity=' + oRecord._oData.entity + '">Messages</a>';
	         }
	            
	        var myDataTable = new YAHOO.widget.RowExpansionDataTable( 
	                "expandableDiv", 
	                [ 
	                	 { 
	                       // key:"steps", 
	                        label:"", 
	                        formatter:YAHOO.widget.RowExpansionDataTable.formatRowExpansion,
	                        width : '400px' 
	                    }, 
	                    { 
	                        key:"loggedOn", 
	                        label:"Timestamp", 
	                        width : '400px' 
	                    }, 
	                    { 
	                        key:"service", 
	                        label:"Service", 
	                        width : '400px' 
	                    },
	                    { 
	                        key:"overallStatus", 
	                        label:"Status", 
	                        width : '400px'
	                    }, 
	                    { 
	                        key:"notes", 
	                        label:"More Info", 
	                        formatter:actionFormatter,
	                        width : '400px'
	                    }  
	                ], 
	                myDataSource, 
                    { rowExpansionTemplate : expansionRowTableGenerator,
                      draggableColumns : false,
           				 paginator : new YAHOO.widget.Paginator({rowsPerPage: 15}),
           				 width: "100%",
            			 formatRow : rowFormatter
                     } 
	                ); 
	 
	        //Subscribe to a click event to bind to 
	        myDataTable.subscribe( 'cellClickEvent', 
	            myDataTable.onEventToggleRowExpansion ); 
	         
        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable 
	        }; 
	    }(); 
	}
    
</script>
</head>

<body>
<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>

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
	  </ul>
  </div>


<form:form name="command" id="command" method="post">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
 
<chrome:box title="Search" autopad="true">
    <div class="content">
          <table>
        		<tr>
        			<td>
            				<tags:renderRow field="${fieldGroups.main.fields[1]}"/>
        			</td>
        			<td>
            				<tags:renderRow field="${fieldGroups.main.fields[2]}"/>
        			</td>
        		</tr> 
        		<tr>
        			<td>
            				<tags:renderRow field="${fieldGroups.main.fields[3]}"/>
        			</td>
        			<td>
            				<tags:renderRow field="${fieldGroups.main.fields[4]}"/>
        			</td>
        		</tr>
        		<tr>
        			<td></td>
            		<td>
            				<tags:button color="blue" type="button" value="Find" size="small" icon="search" onclick="buildTable();"/>
            				<img class="indicator" src="<c:url value="/images/alphacube/progress.gif" />" id="indicator"></td>
            		</td>
            	</tr>
         </table>	
    </div>
    
</chrome:box>

<chrome:box title="Integration Logs" id="integrationLogsBox" style="display:none">
	<div id="expandableDiv"></div>
</chrome:box>

</form:form>

<div id="synchMessage" style="display:none; padding: 15px;">
		<div id="tableDiv">
		</div>
</div>

</body>
</html>
