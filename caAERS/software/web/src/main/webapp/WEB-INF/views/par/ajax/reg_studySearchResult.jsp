<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>

<ec:table autoIncludeParameters="false" items="command.studies" var="study"
    action="${pageContext.request.contextPath}/pages/newParticipant"
    imagePath="${pageContext.request.contextPath}/images/table/*.gif"
    filterable="false"
    showPagination="false" form="command"
    cellspacing="0" cellpadding="0" border="0" width="100%" style=""
    styleClass="">
    <ec:row highlightRow="true">
        <ec:column property="primaryIdentifier" title="Primary ID" />
        <ec:column property="shortTitle" title="Short Title" />
        <ec:column property="primarySponsorCode" title="Funding Sponsor" />
        <ec:column property="phaseCode" title="Phase" />
        <ec:column property="status" title="Status" />
        <ec:column title="Sites" property="status">
           <table>
                <c:forEach items="${study.studySites}" var="site">
                   <tr><td>
                       <form:radiobutton cssClass="sitesRadioBtn siteStudy_${study.id}" path="studySite" value="${site.id}"/>${site.organization.name }
                   </td></tr>
                </c:forEach>
           </table>
        </ec:column>
    </ec:row>
</ec:table>

</tags:noform>    