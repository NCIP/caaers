<%@attribute name="isOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="term" type="gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<c:set var="meddraVersionID" value="${not empty command.meddraVersion ? command.meddraVersion.id : 0}" />

<c:choose>
	<c:when test="${term.medDRA }">
		<c:set var="grade1" value="true"/>
		<c:set var="grade2" value="true"/>
		<c:set var="grade3" value="true"/>
		<c:set var="grade4" value="true"/>
		<c:set var="grade5" value="true"/>
	</c:when>
	<c:otherwise>
		<c:set var="grade1" value="false"/>
		<c:set var="grade2" value="false"/>
		<c:set var="grade3" value="false"/>
		<c:set var="grade4" value="false"/>
		<c:set var="grade5" value="false"/>
		<c:forEach items="${term.term.grades}" var="grade">
			<c:choose>
				<c:when test="${grade.code eq 1}">
					<c:set var="grade1" value="true"/>
				</c:when>
				<c:when test="${grade.code eq 2}">
					<c:set var="grade2" value="true"/>
				</c:when>
				<c:when test="${grade.code eq 3}">
					<c:set var="grade3" value="true"/>
				</c:when>
				<c:when test="${grade.code eq 4}">
					<c:set var="grade4" value="true"/>
				</c:when>
				<c:when test="${grade.code eq 5}">
					<c:set var="grade5" value="true"/>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:otherwise>
</c:choose>

<td>${!term.medDRA ? term.term.category.ctc.name : term.term.meddraVersion.name}</td>
<td>
<c:if test="${isOtherSpecify}">
                <c:set var="initValue" value="Begin typing here"/>
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
<td><c:choose><c:when test="${grade1 }"><ui:text path="agentSpecificTerms[${index}].grade1Frequency" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</c:when><c:otherwise>n/a</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${grade2 }"><ui:text path="agentSpecificTerms[${index}].grade2Frequency" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</c:when><c:otherwise>n/a</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${grade3 }"><ui:text path="agentSpecificTerms[${index}].grade3Frequency" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</c:when><c:otherwise>n/a</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${grade4 }"><ui:text path="agentSpecificTerms[${index}].grade4Frequency" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</c:when><c:otherwise>n/a</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${grade5 }"><ui:text path="agentSpecificTerms[${index}].grade5Frequency" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</c:when><c:otherwise>n/a</c:otherwise></c:choose></td>
<td><ui:checkbox path="agentSpecificTerms[${index}].expected" readonly="false"/></td>
<td><ui:text path="agentSpecificTerms[${index}].expectednessFrequency" readonly="false" size="3" maxlength="6" pattern="\d(\d)?(\.\d(\d)?)?"/>%</td>
