<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp"%>


<style>
    #yui-dt4-th-role {width: 250px;}
    #yui-dt4-th-org{width: 450px;}
    #yui-dt4-th-name{width: 200px;}
</style>

<chrome:division title="Personnel" jsAction="goToPage('PersonnelTab')">

    <div id="personnelTableDiv"></div>

<script language="JavaScript">

    var personnelColumnDefs = [
        {key:"name", label:"Name", sortable:true, resizeable:true},
        {key:"org", label:"Organization", sortable:true, resizeable:true},
        {key:"role", label:"Role", sortable:true, resizeable:true}
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
