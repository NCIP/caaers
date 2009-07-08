<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Manage Reports</title>
<script>
YAHOO.example.Data = {

    rsList: [
<c:forEach items="${savedSearchList}" var="ss" varStatus="status">
        {
            //ssName: "${ss.name}",
            ssName: '<a href="<c:url value="/pages/search/advancedSearch?searchName=${ss.name }"/>">${ss.name }</a>',
            ssDescription: "${ss.description}",
            ssCreatedDate: "${ss.createdDate}",
            ssAction: '<a href="<c:url value="/pages/search/advancedSearch?searchName=${ss.name }&_target2=2&_page=1&runSavedQuery=true"/>">Run</a>'
        }
        <c:if test="${!status.last}">,</c:if>
</c:forEach>

    ]
};

    /////////////////////////////////

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.CustomSort = function() {

        var myColumnDefs = [
            {key:"ssName",             label:"Name",         sortable:true,      resizeable:true},
            {key:"ssDescription",       label:"Description",           sortable:true,      resizeable:true},
            {key:"ssCreatedDate",      label:"Saved on",       sortable:true,      resizeable:true},
            {key:"ssAction",           label:"Action",              sortable:true,      resizeable:true},
        ];

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.rsList.slice(0,50));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["ssName", "ssDescription", "ssCreatedDate", "ssAction"]
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
    </script>
</head>
<body>
	 <div id="basic" class="yui-skin-sam"></div>
</body>
</html>