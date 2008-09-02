<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>
<c:forEach items="${indexes}" var="index" varStatus="status">

<par:parIdentifier
    title="Subject Identifier ${status.index + 1}"
    disableDelete="${fn:length(command.participant.systemAssignedIdentifiers) lt 2}"
    sectionClass="system-section-row"
    removeButtonAction="removeIdentifier"
    index="${index}"
    identifier="${command.participant.systemAssignedIdentifiers[status.index]}"
    mainGroupName="mainSys"
    containerName="addSystemIdentifierDiv"
    action="removeSystemIdentifier"/>
</c:forEach>
</tags:noform>