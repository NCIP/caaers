<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>
    <ec:table autoIncludeParameters="false"
              items="command.participantSearchResults"
              var="participant"
              action="${pageContext.request.contextPath}/pages/home"
              imagePath="${pageContext.request.contextPath}/images/table/*.gif"
              filterable="false"
              showPagination="false"
              form="command"
              cellspacing="0" cellpadding="0" border="0" width="100%" style="" styleClass="">

    <ec:row highlightRow="true">
        <ec:column property="kk" style="width:10px" filterable="false" sortable="false" title=" ">
            <form:radiobutton path="participant" value="${participant.id}" />
        </ec:column>
        <ec:column property="primaryIdentifier" title="Primary ID"/>
        <ec:column property="firstName" title="First Name"/>
        <ec:column property="lastName" title="Last Name" />
        <ec:column property="dateOfBirth" title="Date of Birth"/>
        <ec:column property="gender" title="Gender" />
        <ec:column property="race" title="Race" />
        <ec:column property="ethnicity" title="Ethnicity" />
    </ec:row>
    </ec:table>
</tags:noform>

