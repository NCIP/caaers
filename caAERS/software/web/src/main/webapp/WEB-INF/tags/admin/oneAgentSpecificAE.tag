<%@attribute name="isOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="term" type="gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>

<td>
<c:if test="${isOtherSpecify}">
                <c:set var="initValue" value="Begin typing here..."/>
                <c:if test="${not empty term.otherMeddraTerm && not empty term.otherMeddraTerm.meddraTerm}">
                    <c:set var="initValue" value="${term.otherMeddraTerm.meddraTerm}"/>
                </c:if>
                <%--<tags:requiredIndicator/>--%>
                <c:out value="${term.fullName}" /><br>
                <ui:autocompleter path="agentSpecificTerms[${index}].otherMeddraTerm" initialDisplayValue="${initValue}">
                    <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                    var terminologyVersionId = 10; // get the right value 
                                    createAE.matchLowLevelTermsByCode(terminologyVersionId, text, function(values) {
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
    <c:out value="${term.fullName}" />
</c:if>

</td>
