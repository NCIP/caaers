<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>

<c:set var="mainGroup">metastatic${index}</c:set>
<c:set var="siteField" value="${fieldGroups[mainGroup].fields[0]}" />

<chrome:division id="aeReport.diseaseHistory.metastaticDiseaseSites[${index}]" collapsable="false" deleteParams="'metastaticDiseaseSite', ${index}, '_metastatic', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="title">
		${anatomicSite.name}
	</jsp:attribute>

    <jsp:attribute name="titleFragment">
	</jsp:attribute>

    <jsp:body>

<ui:row path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite">
    <jsp:attribute name="label">${siteField.displayName}</jsp:attribute>
    <jsp:attribute name="value">
        <c:set var="initValue" value="${not empty anatomicSite.name ? anatomicSite.name : 'Begin typing here...'}"/>
        <ui:autocompleter path="${siteField.propertyName}" readonly="false" displayNamePath="${siteField.propertyName}.name"  initialDisplayValue="${initValue}">
              <jsp:attribute name="populatorJS"> function(autocompleter, text) {
                  createAE.matchAnatomicSite(text, function(values) {
                  autocompleter.setChoices(values)
              })
              }
              </jsp:attribute>
            <jsp:attribute name="selectorJS"> function (obj) {
                return obj.name;
             }
            </jsp:attribute>
        </ui:autocompleter>
        <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' onClick="showShowAllTable('_c2_${index}', 'aeReportDOTdiseaseHistoryDOTmetastaticDiseaseSitesOPEN${index}CLOSEDOTcodedSite')" id="_c2_${index}">Show All</a>
    </jsp:attribute>
</ui:row>

    </jsp:body>
    
</chrome:division>

<script>
    function setTitleMDS_${index}() {
        var titleID = $('titleOf_aeReport.diseaseHistory.metastaticDiseaseSites[${index}]');
        var name = $("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite-input");
        var value = name.value;
        $(titleID).innerHTML = value;
    }

    Event.observe($("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite-input"), "blur", function() {
        setTitleMDS_${index}();
    });
</script>
