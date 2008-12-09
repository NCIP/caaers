<%@attribute name="isOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="studyTerm" type="gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>

<td>
<c:set var="ctcTerm" value="expectedAECtcTerms[${index}]" />
<c:if test="${isOtherSpecify}">
                <c:set var="initValue" value="Begin typing here..."/>
                <c:if test="${not empty studyTerm.otherMeddraTerm && not empty studyTerm.otherMeddraTerm.meddraTerm}">
                    <c:set var="initValue" value="${studyTerm.otherMeddraTerm.meddraTerm}"/>
                </c:if>
                <%--<tags:requiredIndicator/>--%>
                <c:out value="${studyTerm.fullName}" /><br>
                <ui:autocompleter path="expectedAECtcTerms[${index}].otherMeddraTerm" initialDisplayValue="${initValue}">
                    <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                    var terminologyVersionId = ${empty command.otherMeddra.id ? 0 : command.otherMeddra.id};
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

<c:if test="${not isOtherSpecify}">
    <c:out value="${studyTerm.fullName}" />
</c:if>

</td>
