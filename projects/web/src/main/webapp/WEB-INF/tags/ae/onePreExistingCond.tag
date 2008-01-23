<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="conmed" index="${index}" style="${style}">
    <tags:errors path="aeReport.saeReportPreExistingConditions[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}">
        <jsp:attribute name="label">
            <label>

            	<c:if test="${fieldGroup.fields[0].required or fieldGroup.fields[0].attributes.mandatory}"><tags:requiredIndicator/></c:if>
                ${fieldGroup.fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    <tags:renderRow field="${fieldGroup.fields[1]}"  style="display: none">
        <jsp:attribute name="label">
            <label>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
</ae:fieldGroupDivision>
