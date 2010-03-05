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
    
    activeReportDefinitions: [
<c:forEach items="${command.activeReportDefinitionsList}" var="rd" varStatus="status">
        {
            rdName: "${rd.name}",
            rdOrganization: "${rd.organization.fullName}",
            rdFinalReportDue: "${rd.duration} ${rd.timeScaleUnitType.displayName}(s)",
            rdDescription: "${rd.description}",
            
            rdAction: "<select id='action-id' onChange=\"javascript:handleAction(this, '${rd.id}')\">" +
            			"<option value=\"\">Please select</option>" +
            			"<option value=\"\">Edit</option>" +
            			"<option value=\"\">Export</option>" +
            			"<option value=\"\">Disable</option>" +
            			"</select>"
            
         }
         <c:if test="${!status.last}">,</c:if>
</c:forEach>
            
    ],
    
    inactiveReportDefinitions: [
<c:forEach items="${command.inactiveReportDefinitionsList}" var="rd" varStatus="status">
        {
            rdName: "${rd.name}",
            rdOrganization: "${rd.organization.fullName}",
            rdFinalReportDue: "${rd.duration} ${rd.timeScaleUnitType.displayName}(s)",
            rdDescription: "${rd.description}",
            
            rdAction: "<select id='action-id' onChange=\"javascript:handleAction(this, '${rd.id}')\">" +
            			"<option value=\"\">Please select</option>" +
            			"<option value=\"\">Edit</option>" +
            			"<option value=\"\">Export</option>" +
            			"<option value=\"\">Enable</option>" +
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

        var activeDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.activeReportDefinitions.slice(0,50));
        activeDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        activeDataSource.responseSchema = {
            fields: ["rdName", "rdDescription", "rdOrganization", "rdFinalReportDue", "rdAction"]
        };

        //Create config
        var oConfigs = {
				initialRequest: "results=50",
				draggableColumns:false
			};
        var activeDataTable = new YAHOO.widget.DataTable("basic", myColumnDefs, activeDataSource, oConfigs);

        return {
            oDS: activeDataSource,
            oDT: activeDataTable
        };
    }();
    
    YAHOO.example.CustomSort = function() {

        var myColumnDefs = [
            {key:"rdName",              label:"Name",               sortable:true,      resizeable:true},
            {key:"rdDescription",       label:"Description",        sortable:true,      resizeable:true},
            {key:"rdOrganization",      label:"Organization",       sortable:true,      resizeable:true},
            {key:"rdFinalReportDue",    label:"Final Report Due",   sortable:true,      resizeable:true,    minWidth:120},
            {key:"rdAction",            label:"Action",             sortable:false,     resizeable:true}
        ];

        var inactiveDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.inactiveReportDefinitions.slice(0,50));
        inactiveDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        inactiveDataSource.responseSchema = {
            fields: ["rdName", "rdDescription", "rdOrganization", "rdFinalReportDue", "rdAction"]
        };

        //Create config
        var oConfigs = {
				initialRequest: "results=50",
				draggableColumns:false
			};
        var inactiveDataTable = new YAHOO.widget.DataTable("basic-inactive", myColumnDefs, inactiveDataSource, oConfigs);

        return {
            oDS: inactiveDataSource,
            oDT: inactiveDataTable
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
            		case "Disable"      : var url = '<c:url value="/pages/rule/notification/list?action=disable&repDefId="/>' + id;
						            	  document.location = url;
            							  break;
            		case "Enable"      : var url = '<c:url value="/pages/rule/notification/list?action=enable&repDefId="/>' + id;
            							  document.location = url;
            							  break;
            	}
    		}
    }
    
    
</script>
    
    
</head>
<body>
	<c:if test="${disabled}"><p class="updated">Report definition successfully disabled</p></c:if>
	<c:if test="${enabled }"><p class="updated">Report definition successfully enabled</p></c:if>
	<caaers:message code="reportDefinition.manage" var="manageReportDefinitionTitle"/>
	<caaers:message code="reportDefinition.active.manage" var="manageActiveReportDefinitionTitle"/>
	<caaers:message code="reportDefinition.inactive.manage" var="manageInactiveReportDefinitionTitle"/>
	<caaers:message code="reportDefinition.import" var="importReportDefinitionTitle" />
	<chrome:box title="${manageReportDefinitionTitle }" autopad="true">
		<chrome:division title="${manageActiveReportDefinitionTitle }" id="rule-set-id-active" collapsable="true" collapsed="false">
			<p><tags:instructions code="listreportdefinitions" /></p>
		    <div id="basic" class="yui-skin-sam"></div> 
			<div class="new_definition">
				<c:set var="create_url"><c:url value="/pages/rule/notification/create"/></c:set>
				<tags:button color="blue" icon="add" size="small" type="button" value="New Report Definition" markupWithTag="a" href="${create_url}" />
			</div>
		</chrome:division>
		<chrome:division title="${manageInactiveReportDefinitionTitle }" id="rule-set-id-inactive" collapsable="true" collapsed="true">
			<p><tags:instructions code="listreportdefinitions" /></p>
			<div id="basic-inactive" class="yui-skin-sam"></div>
		</chrome:division>
		<chrome:division title="${importReportDefinitionTitle}" id="import-rules-id" collapsable="true" collapsed="true">

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
