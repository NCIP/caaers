<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Study Devices" jsAction="goToPage('AgentsTab')">

    <div id="devicesTableDiv"></div>

<script language="JavaScript">

    var devicesColumnDefs = [
        {key:"brandName", label:"Brand name", sortable:true, resizeable:true, minWidth:"300", maxWidth:"300"},
        {key:"commonName", label:"Common name", sortable:true, resizeable:true, minWidth:"300", maxWidth:"300"},
        {key:"deviceType", label:"Device type", sortable:true, resizeable:true, minWidth:"300", maxWidth:"300"}
    ];

    var devicesFields = [
        {key:'brandName', parser:"string"},
        {key:'commonName', parser:"string"},
        {key:'deviceType', parser:"string"}
    ];

    devicesJSONResult = [
        <c:forEach items="${command.study.activeStudyDevices}" var="sd">
            <c:if test="${!sd.retiredIndicator}">
                {"brandName":"${sd.brandName}", "commonName":"${sd.commonName}", "deviceType":"${sd.deviceType}"},
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("devicesTableDiv", devicesJSONResult, devicesColumnDefs, devicesFields);
</script>

</chrome:division>
