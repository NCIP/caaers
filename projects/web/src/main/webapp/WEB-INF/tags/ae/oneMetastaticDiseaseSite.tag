<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>
<%@attribute name="otherSite" type="java.lang.String"%>

<c:set var="mainGroup">metastatic${index}</c:set>
<c:set var="siteField" value="${fieldGroups[mainGroup].fields[0]}" />

<chrome:division id="aeReport.diseaseHistory.metastaticDiseaseSites[${index}]" collapsable="false" deleteParams="'metastaticDiseaseSite', ${index}, '_metastatic', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="title">
		${anatomicSite.name}
        <c:if test="${not empty otherSite}">: ${otherSite}</c:if>
    </jsp:attribute>

    <jsp:attribute name="titleFragment">
	</jsp:attribute>

    <jsp:body>

<c:if test="${empty anatomicSite.name || (anatomicSite.id == 110 && empty otherSite)}">
<ui:row path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite">
    <jsp:attribute name="label">
    	<ui:label path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite" mandatory="${siteField.attributes.mandatory}" 
    		required="${siteField.required}" text="${siteField.displayName}"/>
    	</jsp:attribute>
    <jsp:attribute name="value">
        <c:set var="initValue" value="${not empty anatomicSite.name ? anatomicSite.name : 'Begin typing here...'}"/>
        <ui:autocompleter path="${siteField.propertyName}" readonly="false" mandatory="${siteField.attributes.mandatory}"
        	displayNamePath="${siteField.propertyName}.name" initialDisplayValue="${initValue}" enableClearButton="true">
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
        &nbsp;
            <span id="other_${index}" style="display:none;">
                    <%-- Other, Specify--%>
                    &nbsp;Other, specify: <ui:text path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].otherSite" required="false"/>
            </span>
        <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' onClick="showShowAllTable('_c2_${index}', 'aeReportDOTdiseaseHistoryDOTmetastaticDiseaseSitesOPEN${index}CLOSEDOTcodedSite')" id="_c2_${index}">Show All</a>
    </jsp:attribute>
</ui:row>
        </c:if>
        
    </jsp:body>
    
</chrome:division>

<script>
    function setTitleMDS_${index}() {
        var titleID = $('titleOf_aeReport.diseaseHistory.metastaticDiseaseSites[${index}]');
        var name = $("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite-input");
        var value = name.value;
        if ($("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite").value == 110) {
            $('other_${index}').show();
            value += ": " + $("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].otherSite").value;
        } else {
            $('other_${index}').hide();
        }
        $(titleID).innerHTML = value;
    }

    Event.observe($("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite-input"), "blur", function() {
        setTitleMDS_${index}();
    });
    Event.observe($("aeReport.diseaseHistory.metastaticDiseaseSites[${index}].otherSite"), "change", function() {
        setTitleMDS_${index}();
    });
</script>
