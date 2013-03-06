<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Diseases" jsAction="goToPage('DiseaseTab')">
    <div id="diseasesTableDiv"></div>

<script language="JavaScript">

    var diseasesJSONResult = [];

<c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'CTEP'}">

    var diseasesColumnDefs = [
        {key:"primary",            label:"Primary",                sortable:true,      resizeable:true, minWidth:200, maxWidth:200},
        {key:"diseaseTerm",        label:"Disease Term",           sortable:true,      resizeable:true, minWidth:700, maxWidth:700}
    ];

    var diseasesFields = [
        {key:'primary',           parser:"string"},
        {key:'diseaseTerm',       parser:"string"}
    ];

<c:if test="${not empty command.study.activeCtepStudyDiseases}">
    diseasesJSONResult = [

    <c:forEach items="${command.study.activeCtepStudyDiseases}" var="studyDisease">
        {"primary":"${studyDisease.leadDisease ? '&times;' : ''}", "diseaseTerm":"<c:out value="${studyDisease.term.ctepTerm}" escapeXml="true" />"},
    </c:forEach>

    ];
</c:if>
</c:if>

<c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'MEDDRA'}">

    var diseasesColumnDefs = [
        {key:"diseaseTerm",            label:"Disease Term",                sortable:true,      resizeable:true, minWidth:200, maxWidth:200},
        {key:"meddraCode",             label:"MedDRA Code",                 sortable:true,      resizeable:true, minWidth:700, maxWidth:700}
    ];

    var diseasesFields = [
        {key:'diseaseTerm',           parser:"string"},
        {key:'meddraCode',            parser:"string"}
    ];

    <c:if test="${not empty command.study.activeMeddraStudyDiseases}">
    diseasesJSONResult = [

    <c:forEach items="${command.study.activeMeddraStudyDiseases}" var="studyDisease">
        {"diseaseTerm":"<c:out value="${studyDisease.term.meddraTerm}" escapeXml="true" />", "meddraCode":"<c:out value="${studyDisease.term.meddraCode}" escapeXml="true" />"},
    </c:forEach>

    ];
</c:if>
</c:if>

<c:if test="${command.study.diseaseTerminology.diseaseCodeTerm == 'OTHER'}">

    var diseasesColumnDefs = [
        {key:"diseaseTerm",            label:"Disease Term",                sortable:true,      resizeable:true, minWidth:900, maxWidth:900}
    ];

    var diseasesFields = [
        {key:'diseaseTerm',           parser:"string"}
    ];

    <c:if test="${not empty command.study.activeStudyConditions}">
    diseasesJSONResult = [

        <c:forEach items="${command.study.activeStudyConditions}" var="studyDisease">
            {"diseaseTerm":"<c:out value="${studyDisease.term.conditionName}" escapeXml="true" />"},
        </c:forEach>

    ];
    </c:if>
</c:if>

initializeYUITable("diseasesTableDiv", diseasesJSONResult, diseasesColumnDefs, diseasesFields);
</script>

</chrome:division>
