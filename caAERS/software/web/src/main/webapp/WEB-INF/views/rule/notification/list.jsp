<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>List Report Calendars</title>
    <style>
        .yui-dt-resizerliner{height:30px;}
    </style>
</head>
<body>
    <p><tags:instructions code="listreportdefinitions" /></p>
    <div id="basic" class="yui-skin-sam"></div> 
<script language="JavaScript">
YAHOO.example.Data = {
    
    reportDefinitions: [
<c:forEach items="${command.reportCalendarTemplateList}" var="rd" varStatus="status">
        {
            rdName: "<a href='<c:url value="/pages/rule/notification/edit?repDefId=${rd.id}" />'>${rd.name}</a>",
            rdOrganization: "${rd.organization.fullName}",
            rdFinalReportDue: "${rd.duration} ${rd.timeScaleUnitType.displayName}(s)",
            rdDescription: "${rd.description}",
            rdAction: "<a href='<c:url value="/pages/rule/notification/export?repDefId=${rd.id}"/>'>Export/Download</a>"}
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
</script>

</body>
</html>
