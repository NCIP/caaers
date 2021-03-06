<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="identifier" required="true" type="gov.nih.nci.cabig.caaers.domain.Identifier" %>
<%@attribute name="style" %>
<%@attribute name="title" %>
<%@attribute name="sectionClass" required="true" %>
<%@attribute name="removeButtonAction" %>
<%@attribute name="disableDelete" type="java.lang.Boolean" %>
<%@attribute name="initialValue" %>
<%@attribute name="mainGroupName" required="true" %>
<%@attribute name="containerName" required="true" %>
<%@attribute name="removeAction" required="true" %>
<%@attribute name="readonly"%>

<%--
    containerName: refreshing the <containerName> element when an item is deleted from the <items>, on remove action
    action: the fired action when the user clicks the DELETE button
--%>

<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">${mainGroupName}${index}</c:set>
<c:set var="readonly">${not empty readonly? readonly : not empty identifier.id && (not empty identifier.type && identifier.type ne '') && (not empty identifier.value && identifier.value ne '')}</c:set>

<tr id="${sectionClass}-${listEditorIndex}" class="${sectionClass}" bgcolor="#ffffff">

    <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="x">
        <c:set var="_align" value="left" />
        <c:choose>
            <c:when test="${field.categoryName == 'autocompleter'}"><c:set var="_align" value="left" /></c:when>
        </c:choose>
        <td align="${_align}">
            <tags:renderInputs field="${field}" readonly="${readonly}"/>
            <c:if test="${field.categoryName == 'autocompleter'}">
                <c:set var="x">
                    <jsp:attribute name="value"><caaers:value path="${field.propertyName}" /></jsp:attribute>
                </c:set>
                <c:if test="${not empty x}">
                    <c:set var="initValue" scope="page">
                        <jsp:attribute name="value"><caaers:value path="${field.propertyName}.fullName" /></jsp:attribute>
                    </c:set>
                </c:if>
                <c:if test="${(not readonly)}">
	                <script>
		                AE.createStandardAutocompleter('${field.propertyName}', function(autocompleter, text) {
		                    createParticipant.matchOrganization(text, function(values) {
		                        autocompleter.setChoices(values)
		                    })
		                }, function(organization) {
		                    var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
		                    return organization.name + nciInstituteCode
		        		}, {initialInputValue:'${initValue}'});
	        		</script>
	        	</c:if>
            </c:if>
        </td>
    </c:forEach>
    
    <c:if test="${not disableDelete}">
        <td align="center">
            <a href="javascript:${removeAction}('${containerName}', '<c:out value="${index}" />');"><img src="<c:url value="/images/checkno.gif" />?${requestScope.webCacheId}" border="0" alt="delete" ></a>
        </td>
    </c:if>
    <c:if test="${disableDelete}">
        <td>&nbsp;</td>
    </c:if>
</tr>
