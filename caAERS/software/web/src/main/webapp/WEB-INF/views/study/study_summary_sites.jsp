<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Sites">

    <div id="sitesTableDiv"></div>

<script language="JavaScript">

    var sitesColumnDefs = [
        {key:"name", label:"Study Site", sortable:true, resizeable:true, minWidth:"900", maxWidth:"900"}
    ];

    var sitesFields = [
        {key:'name', parser:"string"}
    ];

    sitesJSONResult = [
        <c:forEach items="${command.study.studySites}" var="studySite">
            <c:if test="${!studySite.retired}">
                {"name":"<c:out value="${studySite.organization.name}" escapeXml="true" />"},
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("sitesTableDiv", sitesJSONResult, sitesColumnDefs, sitesFields);
</script>

</chrome:division>
