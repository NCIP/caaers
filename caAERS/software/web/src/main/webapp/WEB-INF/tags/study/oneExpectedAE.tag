<%@attribute name="isOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="studyTerm" type="gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<td width="100%">
<c:set var="ctcTerm" value="study.expectedAECtcTerms[${index}]" />
<c:if test="${isOtherSpecify}">
                <c:set var="initValue" value="Begin typing here"/>
                <c:if test="${not empty studyTerm.otherMeddraTerm && not empty studyTerm.otherMeddraTerm.meddraTerm}">
                    <c:set var="initValue" value="${studyTerm.otherMeddraTerm.meddraTerm}"/>
                </c:if>
                <%--<tags:requiredIndicator/>--%>
                <c:out value="${studyTerm.fullName}" /><br>

            <c:if test="${not empty command.study.otherMeddra}">
                <ui:autocompleter path="study.expectedAECtcTerms[${index}].otherMeddraTerm" initialDisplayValue="${initValue}" readonly="false">
                    <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                    var terminologyVersionId = ${empty command.study.otherMeddra.id ? 0 : command.study.otherMeddra.id};
                                    createAE.matchLowLevelTermsByCode(terminologyVersionId, text, function(values)
                                    {
                                        autocompleter.setChoices(values)
                                    })
                            }
                    </jsp:attribute>
                    <jsp:attribute name="selectorJS">
                        function(obj) {
                            return obj.meddraTerm;
                        }
                    </jsp:attribute>
                </ui:autocompleter>
            </c:if>
    
    <b><caaers:message code="LBL_otherToxicity" /></b>:
    <c:set var="_tox"><jsp:attribute name="value"><caaers:value path="study.expectedAECtcTerms[${index}].otherToxicity" /></jsp:attribute></c:set>
    <ui:text path="study.expectedAECtcTerms[${index}].otherToxicity" readonly="${_tox ne ''}"/>

</c:if>

<c:if test="${not isOtherSpecify}">
    <c:out value="${studyTerm.fullName}" />
</c:if>

</td>
