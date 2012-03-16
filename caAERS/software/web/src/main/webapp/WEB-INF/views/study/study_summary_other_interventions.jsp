<%@include file="/WEB-INF/views/taglibs.jsp" %>

<chrome:division title="Other interventions">

    <div id="otherInterventionsTableDiv"></div>

<script language="JavaScript">

    var otherInterventionsColumnDefs = [
        {key:"name", label:"Name", sortable:true, resizeable:true, minWidth:100},
        {key:"description", label:"Description", sortable:true, resizeable:true, minWidth:600},
        {key:"type", label:"Intervention type", sortable:true, resizeable:true, minWidth:200}
    ];

    var otherInterventionsFields = [
        {key:'name', parser:"string"},
        {key:'description', parser:"string"},
        {key:'type', parser:"string"}
    ];

    otherInterventionsJSONResult = [
        <c:forEach items="${command.study.otherInterventions}" var="oi">
            <c:if test="${!oi.retiredIndicator}">
                {
                    "name":"<c:out value="${oi.name}" escapeXml="true"/>",
                    "description":"<c:out value="${oi.description}" escapeXml="true"/>",
                    "type":"<c:out value="${oi.studyTherapyType}" escapeXml="true"/>"
                },
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("otherInterventionsTableDiv", otherInterventionsJSONResult, otherInterventionsColumnDefs, otherInterventionsFields);
</script>

</chrome:division>
