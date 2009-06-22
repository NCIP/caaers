<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>

<chrome:division id="assignment.diseaseHistory.metastaticDiseaseSites[${index}]" collapsable="false" deleteParams="'metastaticDiseaseSite', ${index}, 'anchorMetastaticDiseases', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="titleFragment">
		${anatomicSite.name}
	</jsp:attribute>

    <jsp:body>

<ui:row path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite">
    <jsp:attribute name="label">${siteField.displayName}</jsp:attribute>
    <jsp:attribute name="value">

                      <c:if test="${empty anatomicSite.name}">

                    <c:set var="initValue" value="${not empty anatomicSite ? anatomicSite.name : 'Begin typing here...'}"/>
                      <ui:autocompleter path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].codedSite" initialDisplayValue="${initValue}" size="50">
                          <jsp:attribute name="populatorJS">
                              function(autocompleter, text) {
                                  createAE.matchAnatomicSite(text, function(values) {
                                      autocompleter.setChoices(values)
                                  })
                              }
                          </jsp:attribute>
                          <jsp:attribute name="selectorJS">
                              function (obj) {
                                  return obj.name;
                              }
                          </jsp:attribute>
                      </ui:autocompleter>
                      <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' id="_c2_${index}" onclick="showShowAllTable('_c2_${index}', 'assignmentDOTdiseaseHistoryDOTmetastaticDiseaseSitesOPEN${index}CLOSEDOTcodedSite')">Show All</a>


                    <c:if test="${anatomicSite.id eq 110}">
					<ui:text path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].otherSite" required="true"/>
					</c:if>
                          
                      </c:if>
</jsp:attribute>
</ui:row>        

        </jsp:body>
</chrome:division>    