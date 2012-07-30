<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="status" begin="${remove eq 'remove' ? 1 : 0}">
<c:set var="readonly" value="${not empty remove ? 'true':'false'}"/>
<par:parIdentifier
    title="Subject Identifier ${status.index + 1}"
    disableDelete="false"
    sectionClass="organization-section-row"
    removeButtonAction="removeIdentifier"
    index="${index}"
    identifier="${command.participant.organizationIdentifiers[status.index]}"
    mainGroupName="mainOrg"
    containerName="addOrganizationIdentifierDiv"
    removeAction="removeOrganizationIdentifier"
    readonly="${readonly}"/>
    
</c:forEach>
</tags:noform>