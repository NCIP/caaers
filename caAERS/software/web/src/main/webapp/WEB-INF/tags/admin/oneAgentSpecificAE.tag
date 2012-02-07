<%@attribute name="isOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="term" type="gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:set var="meddraVersionID" value="${not empty command.meddraVersion ? command.meddraVersion.id : 0}" />

<td>${!term.medDRA ? term.term.category.ctc.name : term.term.meddraVersion.name}</td>
<td>
<c:if test="${isOtherSpecify}">
                <c:set var="initValue" value="Begin typing here..."/>
                <c:if test="${not empty term.otherMeddraTerm && not empty term.otherMeddraTerm.meddraTerm}">
                    <c:set var="initValue" value="${term.otherMeddraTerm.meddraTerm}"/>
                </c:if>
                <%--<tags:requiredIndicator/>--%>
                <c:out value="${term.fullName}" />&nbsp;&nbsp;&nbsp;
                <c:set var="_tox"><jsp:attribute name="value"><caaers:value path="agentSpecificTerms[${index}].otherToxicity" /></jsp:attribute></c:set>
                <b><caaers:message code="LBL_otherToxicity" /></b>:
                <ui:text path="agentSpecificTerms[${index}].otherToxicity" readonly="${_tox ne ''}"/>

<%--
                <ui:autocompleter path="agentSpecificTerms[${index}].otherMeddraTerm" initialDisplayValue="${initValue}">
                    <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                    var terminologyVersionId = ${meddraVersionID}; 
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
--%>
</c:if>

<c:if test="${not isOtherSpecify}">
    <c:out value="${term.fullName}" />
</c:if>

</td>
<td><ui:text path="agentSpecificTerms[${index}].grade1Frequency" readonly="false" size="3"/>%</td>
<td><ui:text path="agentSpecificTerms[${index}].grade2Frequency" readonly="false" size="3"/>%</td>
<td><ui:text path="agentSpecificTerms[${index}].grade3Frequency" readonly="false" size="3"/>%</td>
<td><ui:text path="agentSpecificTerms[${index}].grade4Frequency" readonly="false" size="3"/>%</td>
<td><ui:text path="agentSpecificTerms[${index}].grade5Frequency" readonly="false" size="3"/>%</td>
<td><ui:checkbox path="agentSpecificTerms[${index}].expected" readonly="false"/></td>
<td><ui:text path="agentSpecificTerms[${index}].expectednessFrequency" readonly="false" size="3"/>%</td>
