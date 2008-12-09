<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty command.aeTerminology.meddraVersion}">
    <c:set var="terms" value="${command.expectedAEMeddraLowLevelTerms}" />
</c:if>

<c:if test="${not empty command.aeTerminology.ctcVersion}">
    <c:set var="terms" value="${command.expectedAECtcTerms}" />
</c:if>
<%--${param.index} <c:out value="${fn:length(command.expectedAECTCTerms)}"/><c:out value="${fn:length(command.expectedAEMeddraTerms)}"/>--%>

<tags:noform>
    <c:if test="${param.index >= 0}">
                <%-- ADD --%>
                <c:if test="${param.isSingle eq 'true'}">
                    <c:forEach begin="${param.index}" end="${param.index}" varStatus="status">
                        <tr class="ae-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="STUDY_TERM_-${status.index}" >
                            <study:oneExpectedAE isOtherSpecify="${command.expectedAECtcTerms[status.index].otherRequired}" index="${status.index}" studyTerm="${command.expectedAECtcTerms[status.index]}"/>
                            <td style="text-align:center;"><img src="<c:url value="/images/checkno.gif" />" id="DELETE_<c:out value="${status.index}" />" onclick="removeTerm(${status.index})" style="cursor:pointer;"></td>
                        </tr>
                    </c:forEach>
                </c:if>

                <%-- DELETE --%>
                <c:if test="${not param.isSingle eq 'true'}">
                    <c:forEach begin="0" end="${param.index}" varStatus="status">

                        <tr class="ae-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="STUDY_TERM_-${status.index}" >
                            <study:oneExpectedAE isOtherSpecify="${command.expectedAECtcTerms[status.index].otherRequired}" index="${status.index}" studyTerm="${command.expectedAECtcTerms[status.index]}"/>
                            <td style="text-align:center;"><img src="<c:url value="/images/checkno.gif" />" id="DELETE_<c:out value="${status.index}" />" onclick="removeTerm(${status.index})" style="cursor:pointer;"></td>
                        </tr>

                    </c:forEach>
                </c:if>
    </c:if>
</tags:noform>

