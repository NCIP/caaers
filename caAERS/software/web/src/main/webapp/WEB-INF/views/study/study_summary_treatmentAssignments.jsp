<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Treatment Assignments">

    <div id="treatmentAssignmentsTableDiv"></div>

<script language="JavaScript">

    var treatmentAssignmentsColumnDefs = [
        {key:"code", label:"Code", sortable:true, resizeable:true, minWidth:"100", maxWidth:"100"},
        {key:"doseLevelOrder", label:"Dose level order", sortable:true, resizeable:true, minWidth:"150", maxWidth:"150"},
        {key:"description", label:"Description", sortable:true, resizeable:true, minWidth:"600", maxWidth:"600"},
        {key:"comments", label:"Comments", sortable:true, resizeable:true, minWidth:"200", maxWidth:"200"}
    ];

    var treatmentAssignmentsFields = [
        {key:'code', parser:"string"},
        {key:'doseLevelOrder', parser:"string"},
        {key:'description', parser:"string"},
        {key:'comments', parser:"string"}
    ];

    treatmentAssignmentsJSONResult = [
        <c:forEach items="${command.study.treatmentAssignments}" var="treatmentAssignment">
            <c:if test="${not treatmentAssignment.retired}">
                {"code":"<c:out value="${treatmentAssignment.code}" escapeXml="true" />", "doseLevelOrder":"<c:out value="${treatmentAssignment.doseLevelOrder}" escapeXml="true" />", "description":"<c:out value="${treatmentAssignment.description}" escapeXml="true" />", "comments":"<c:out value="${treatmentAssignment.comments}" escapeXml="true" />"},
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("treatmentAssignmentsTableDiv", treatmentAssignmentsJSONResult, treatmentAssignmentsColumnDefs, treatmentAssignmentsFields);
</script>

</chrome:division>
