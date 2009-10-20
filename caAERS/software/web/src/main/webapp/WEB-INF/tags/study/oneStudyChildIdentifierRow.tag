<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="idSuffix" %>
<%@attribute name="style"%>
<%@attribute name="cssClass" required="true" %>
<%@attribute name="disableDelete" type="java.lang.Boolean"  %>
<%@attribute name="exclusions" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<c:set var="mainGroup">main${index}</c:set>
<c:set var="css">${cssClass} ${index % 2 ne 0 ? 'even' : 'odd'} ${sectionClass}</c:set>
<tr id="${cssClass}-${empty idSuffix ? index : idSuffix}" class="${css}" onmouseout="this.className='${css}'" onmouseover="this.className='highlight'" style="${style}" valign="top">

    <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="fstatus">
        <c:if test="${not fn:contains(exclusions, field.displayName)}">
		    <td style="border-right:none;">

                <c:if test="${index < 2}">
                    <c:if test="${fstatus.index == 0 || fstatus.index == 4}">
                        <c:set var="fValue"><jsp:attribute name="value"><caaers:value path="${field.propertyName}" /></jsp:attribute></c:set>
                        <tags:renderInputs field="${field}" disabled="${identifiers and (index lt 2) and (fstatus.index ne 4)}" readonly="${index < 2}"/>
                    </c:if>

                    <c:if test="${fstatus.index == 1}">
                        <caaers:value path="study.identifiersLazy[${index}].type" />
                    </c:if>
                    <c:if test="${fstatus.index == 3}">
                        <caaers:value path="study.identifiersLazy[${index}].organization.name" />
                    </c:if>
                </c:if>

                <c:if test="${index >= 2}">
                    <c:set var="fValue"><jsp:attribute name="value"><caaers:value path="${field.propertyName}" /></jsp:attribute></c:set>
                    <tags:renderInputs field="${field}" disabled="${identifiers and (index lt 2) and (fstatus.index ne 4)}" readonly="${index < 2}"/>
                </c:if>

            </td>
		</c:if>
	</c:forEach>

    <c:if test="${not disableDelete}">
        <td style="border-left:none;">
            <tags:button id="${status.index}" color="blue" type="button" value="" size="small" icon="x" onclick="fireDelete(${index},'${cssClass}-${index}')"/>
        </td>
	</c:if>
	<c:if test="${disableDelete}">
	    <td>&nbsp;</td>
	</c:if>
</tr>
