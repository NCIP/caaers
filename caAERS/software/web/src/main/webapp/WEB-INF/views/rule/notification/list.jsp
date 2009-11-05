<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Manage report definitions</title>
    <style>
        .yui-dt-resizerliner{height:30px;}
		
        .row .value {
            margin-left: 22%;
        }

        p.description {
            margin: 0.25em 0 0 1em;
        }
        div.submit {
            margin:0 0 0 157px;
			padding:0;
        }
        .value input[type=text] {
            width: 80%;
        }

        form {
            margin-top: 1em;
        }

        .updated {
            border: #494 solid;
            border-width: 1px 0;
            background-color: #8C8;
            padding: 1em 2em;
            text-align: center;
            margin: 1em 30%;
            color: #fff;
            font-weight: bold;
            font-size: 1.1em;
        }
		.new_definition {
			margin:10px 0 10px 65px;
		}
    </style>
    <link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
    <script language="JavaScript">
YAHOO.example.Data = {
    
    reportDefinitions: [
<c:forEach items="${command.reportCalendarTemplateList}" var="rd" varStatus="status">
        {
            rdName: "${rd.name}",
            rdOrganization: "${rd.organization.fullName}",
            rdFinalReportDue: "${rd.duration} ${rd.timeScaleUnitType.displayName}(s)",
            rdDescription: "${rd.description}",
            
            rdAction: "<select id='action-id' onChange=\"javascript:handleAction(this, '${rd.id}')\">" +
            			"<option value=\"\">Please select</option>" +
            			"<option value=\"\">Edit</option>" +
            			"<option value=\"\">Export</option>" +
            			"</select>"
            
         }
         <c:if test="${!status.last}">,</c:if>
</c:forEach>
            
    ]
};

    /////////////////////////////////

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.CustomSort = function() {

        var myColumnDefs = [
            {key:"rdName",              label:"Name",               sortable:true,      resizeable:true},
            {key:"rdDescription",       label:"Description",        sortable:true,      resizeable:true},
            {key:"rdOrganization",      label:"Organization",       sortable:true,      resizeable:true},
            {key:"rdFinalReportDue",    label:"Final Report Due",   sortable:true,      resizeable:true,    minWidth:120},
            {key:"rdAction",            label:"Action",             sortable:false,     resizeable:true}
        ];

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.reportDefinitions.slice(0,50));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["rdName", "rdDescription", "rdOrganization", "rdFinalReportDue", "rdAction"]
        };

        //Create config
        var oConfigs = {
				initialRequest: "results=50",
				draggableColumns:false
			};
        var myDataTable = new YAHOO.widget.DataTable("basic", myColumnDefs, myDataSource, oConfigs);

        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});

    /////////////////////////////////
    
    function handleAction(selectElement, id){
    	var action = selectElement.options[selectElement.selectedIndex].text;
    	if(action != 'Please select')
	    	if(confirm('Are you sure you want to take the action - ' + action + ' ?')){
    			switch (action) {
    		    	case "Please select": break;
       		 	    case "Edit"         : var url = '<c:url value="/pages/rule/notification/edit?repDefId=" />' + id;
                				          window.location = url; 
							              break;
              		case "Export"       : var url = '<c:url value="/pages/rule/notification/export?repDefId="/>' + id;
            					          document.location = url;  
            					          break;
            	}
    		}
    }
    
    
</script>
    
    
</head>
<body>
	<chrome:box title="Manage / Import Report Definitions" autopad="true">
		<chrome:division title="Manage report definitions" id="rule-set-id" >
			<p><tags:instructions code="listreportdefinitions" /></p>
		    <div id="basic" class="yui-skin-sam"></div> 
			<div class="new_definition">
				<c:set var="create_url"><c:url value="/pages/rule/notification/create"/></c:set>
				<tags:button color="blue" icon="add" size="small" type="button" value="New Report Definition" markupWithTag="a" href="${create_url}" />
			</div>
		</chrome:division>
		<chrome:division title="Import report definitions" id="import-rules-id">

				<tags:instructions code="importxmlreportdefinitions" />

			<form:form action="${action}" enctype="multipart/form-data" cssClass="standard">
            	<div class="row">
                	<div class="label" style="width:11em;">
                   		Report definition file  
                	</div>
                	<div class="value" style="margin-left:12em;">
                		<input type="file" name="ruleSetFile1" size="50" onchange="$('add-definition').removeAttribute('disabled')"/>
                	</div>
            	</div>    
        		<div class="row submit">
            		<tags:button id="add-definition" disabled="disabled" type="submit" value="Import" size="small" color="green" icon="check" />
        		</div>
    		</form:form>
    		<c:if test="${command.updated}">
				<c:if test="${not empty command.message}"><p class="updated">${command.message}</p></c:if>
				<c:if test="${not empty command.errorMessage}"><div id="flash-message" class="error">${command.errorMessage}</div></c:if>
			</c:if>
		</chrome:division>
    
    </chrome:box>
</body>
</html>
