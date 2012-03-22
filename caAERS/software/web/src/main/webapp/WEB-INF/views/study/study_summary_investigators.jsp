<%@include file="/WEB-INF/views/taglibs.jsp"%>

<style>
    #yui-dt3-th-role {width: 250px;}
    #yui-dt3-th-org{width: 450px;}
    #yui-dt3-th-name{width: 200px;}
</style>

<chrome:division title="Investigators" jsAction="goToPage('InvestigatorsTab')">

    <div id="investigatorsTableDiv"></div>

<script language="JavaScript">

    var investigatorsColumnDefs = [
        {key:"name", label:"Name", sortable:true, resizeable:true},
        {key:"org", label:"Organization", sortable:true, resizeable:true},
        {key:"role", label:"Role", sortable:true, resizeable:true}
    ];

    var investigatorsFields = [
        {key:'name', parser:"string"},
        {key:'org', parser:"string"},
        {key:'role', parser:"string"}
    ];

    investigatorsJSONResult = [
        <c:forEach items="${command.study.activeStudyOrganizations}" var="studySite" >
        <c:forEach items="${studySite.studyInvestigators}" var="studyInvestigator" varStatus="i">
                <c:if test="${studyInvestigator.active}">
                    {"name":"${studyInvestigator.siteInvestigator.investigator.fullName}","org":"${studyInvestigator.siteInvestigator.organization}","role":"${command.studyInvestigatorRoles[studyInvestigator.roleCode]}"},
                </c:if>
            </c:forEach>
        </c:forEach>
    ];

    initializeYUITable("investigatorsTableDiv", investigatorsJSONResult, investigatorsColumnDefs, investigatorsFields);
</script>

</chrome:division>
