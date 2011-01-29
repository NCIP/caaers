<%@include file="/WEB-INF/views/taglibs.jsp" %>

<table width="100%" border="0">
<tr>
    <td valign="top">

        <c:if test="${not empty roles.ae_reporter or not empty roles.ae_expedited_report_reviewer}">
            <jsp:include page="/pages/dashboard/aeReporter" />
        </c:if>


        <c:if test="${not empty roles.study_creator or not empty roles.study_qa_manager or not empty roles.supplemental_study_information_manager or not empty roles.study_team_administrator or not empty roles.study_site_participation_administrator}">
            <jsp:include page="/pages/dashboard/study" />
        </c:if>


        <c:if test="${not empty roles.registration_qa_manager or not empty roles.subject_manager or not empty roles.registrar}">
            <jsp:include page="/pages/dashboard/subject" />
        </c:if>


        <c:if test="${not empty roles.user_administrator}">
            <jsp:include page="/pages/dashboard/users" />
        </c:if>

    </td>
    <td valign="top">
        <%@include file="/WEB-INF/views/dashboard/dashboardTasks.jsp"%>
    </td>
<tr>
</table>
