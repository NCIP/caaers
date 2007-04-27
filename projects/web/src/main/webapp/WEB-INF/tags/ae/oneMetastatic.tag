<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="metastatic" index="${index}" style="${style}">
    <tags:errors path="aeReport.diseaseHistory.metastaticDiseaseSite[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}">
        <jsp:attribute name="label">
            <label>
                <input id="select-anatomicSite-${index}" name="anatomicOrOther${index}" type="radio"/>
                ${fieldGroup.fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    <tags:renderRow field="${fieldGroup.fields[1]}">
        <jsp:attribute name="label">
            <label>
                <input id="select-otherMetastaticDiseaseSite-${index}" name="anatomicOrOther${index}" type="radio"/>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
</ae:fieldGroupDivision>
