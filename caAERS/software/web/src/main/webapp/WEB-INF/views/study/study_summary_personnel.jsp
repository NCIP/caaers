<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Personnel">

    <div id="personnelTableDiv"></div>

<script language="JavaScript">

    var personnelColumnDefs = [
        {key:"name", label:"Name", sortable:true, resizeable:true, minWidth:"200", maxWidth:"200"},
        {key:"org", label:"Organization", sortable:true, resizeable:true, minWidth:"500", maxWidth:"500"},
        {key:"role", label:"Role", sortable:true, resizeable:true, minWidth:"250", maxWidth:"250"}
    ];

    var personnelFields = [
        {key:'name', parser:"string"},
        {key:'org', parser:"string"},
        {key:'role', parser:"string"}
    ];

    personnelJSONResult = [
        <c:forEach items="${command.study.activeStudyOrganizations}" var="studySite" >
            <c:forEach items="${studySite.studyPersonnels}" var="studyPersonnel" varStatus="i">
                <c:if test="${studyPersonnel.active}">
                    {"name":"${studyPersonnel.siteResearchStaff.researchStaff.fullName}","org":"${studyPersonnel.siteResearchStaff.organization}","role":"${command.studyPersonnelRoles[studyPersonnel.roleCode]}"},
                </c:if>
            </c:forEach>
        </c:forEach>
    ];

    initializeYUITable("personnelTableDiv", personnelJSONResult, personnelColumnDefs, personnelFields);
</script>

</chrome:division>
