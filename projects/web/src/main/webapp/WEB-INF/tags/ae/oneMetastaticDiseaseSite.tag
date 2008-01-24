<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="metastatic" index="${index}" style="${style}">
    <tags:errors path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}"
                    extraParams="<a id=\"showAll${index}\" href=\"javascript:showDiseaseSiteTable('metastaticDiseaseSitesTable${index}')\">Show All</a>" >
        <jsp:attribute name="label">
            <label>
                <input id="select-codedSite-${index}" name="anatomicOrOther${index}" type="radio"/>
                ${fieldGroup.fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    <div id="metastaticDiseaseSitesTable${index}"
         style="position: absolute; display: block; left: 640px; width:400px; z-index:99;">
    </div>

    <tags:renderRow field="${fieldGroup.fields[1]}">
        <jsp:attribute name="label">
            <label>
                <input id="select-otherSite-${index}" name="anatomicOrOther${index}" type="radio"/>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
</ae:fieldGroupDivision>
