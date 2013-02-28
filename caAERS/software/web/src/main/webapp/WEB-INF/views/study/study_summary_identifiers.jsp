<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>

<chrome:division title="Identifiers" jsAction="goToPage('IdentifiersTab')">

    <div id="identifiersTableDiv"></div>

<script language="JavaScript">

    var identifiersColumnDefs = [
        {key:"aa", label:"Assigning Authority", sortable:true, resizeable:true, minWidth:300},
        {key:"it", label:"Identifier Type", sortable:true, resizeable:true, minWidth:300},
        {key:"id", label:"Identifier", sortable:true, resizeable:true, minWidth:300}
    ];

    var identifiersFields = [
        {key:'aa', parser:"string"},
        {key:'it', parser:"string"},
        {key:'id', parser:"string"}
    ];

    identifiersJSONResult = [
        <c:forEach items="${command.study.identifiersLazy}" var="identifier">
                {
                        <c:if test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier') }">
                            <%--<c:if test="${identifier.organization.externalId != null}"></c:if>--%>
                            "aa":"${identifier.organization}",
                        </c:if>
                        <c:if test="${(identifier.class.name =='gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier')}">
                            "aa":"<c:out value="${identifier.systemName}" escapeXml="true"/>",
                        </c:if>

                    "it":"<c:out value="${identifier.type}" escapeXml="true"/>",
                    "id":"<c:out value="${identifier.value}" escapeXml="true"/>"
                },
        </c:forEach>
    ];

    initializeYUITable("identifiersTableDiv", identifiersJSONResult, identifiersColumnDefs, identifiersFields);
</script>

</chrome:division>
