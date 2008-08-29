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
<%@attribute name="mainGroupName" required="true" %>

<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">${mainGroupName}${index}</c:set>

<tr id="${sectionClass}-${listEditorIndex}" class="${sectionClass}">

    <c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
        <td align="center">
            <tags:renderInputs field="${field}"/>
            <c:if test="${field.categoryName == 'autocompleter'}">
                <script>AE.createStandardAutocompleter('${field.propertyName}', function(autocompleter, text) {
                    createParticipant.matchOrganization(text, function(values) {
                    autocompleter.setChoices(values)
                })
        }, function(organization) {
            var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
            return organization.name + nciInstituteCode
        }, {initialInputValue:'<caaers:value path="${field.propertyName}.fullName" />'});</script>
            </c:if>
        </td>
    </c:forEach>
    
    <c:if test="${not disableDelete}">
        <td align="right">
            <c:set var="_action" value="${deleteParams}, '${sectionClass}-${index}', '${cssClass}'" />
            <a href="javascript:fireAction(<c:out value="${_action}" />);"><img src="/caaers/images/checkno.gif" border="0" alt="delete" ></a>
        </td>
    </c:if>
    <c:if test="${disableDelete}">
        <td>&nbsp;</td>
    </c:if>
</tr>
