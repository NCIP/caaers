<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp"%>



<chrome:division title="Expected AEs" jsAction="goToPage('ExpectedAEsTab')">

    <div id="expectedAeTableDiv"></div>

<script language="JavaScript">

    var expectedAeColumnDefs = [
        {key:"name", label:"Term name", sortable:true, resizeable:true, minWidth:"900", maxWidth:"900"}
    ];

    var expectedAeFields = [
        {key:'name', parser:"string"}
    ];

    <c:if test="${command.study.aeTerminology.term eq 'MEDDRA'}">
        <c:set var="terms" value="${command.study.expectedAEMeddraLowLevelTerms}" />
    </c:if>

    <c:if test="${command.study.aeTerminology.term eq 'CTC'}">
        <c:set var="terms" value="${command.study.expectedAECtcTerms}" />
    </c:if>

    expectedAeJSONResult = [
        <c:forEach items="${terms}" var="term">
            <c:set var="_name">${term.fullName} <c:if test="${command.study.aeTerminology.term eq 'CTC' && term.otherMeddraTerm != null}">(${term.otherMeddraTerm.fullName})</c:if> ${term.otherToxicity}</c:set>

            {"name":"<c:out value="${_name}" escapeXml="true" />"},
        </c:forEach>
    ];

    initializeYUITable("expectedAeTableDiv", expectedAeJSONResult, expectedAeColumnDefs, expectedAeFields);
</script>

</chrome:division>
