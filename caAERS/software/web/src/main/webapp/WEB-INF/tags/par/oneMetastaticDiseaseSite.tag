<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>
<%@attribute name="metastaticSite" type="gov.nih.nci.cabig.caaers.domain.StudyParticipantMetastaticDiseaseSite" required="true"%>

<chrome:division id="assignment.diseaseHistory.metastaticDiseaseSites[${index}]" collapsable="false" deleteParams="'metastaticDiseaseSite', ${index}, 'anchorMetastaticDiseases', {}" enableDelete="true" collapsed="false">

    <jsp:attribute name="title">${metastaticSite.name}</jsp:attribute>
    <jsp:attribute name="titleFragment"></jsp:attribute>

    <jsp:body>

        <c:if test="${empty metastaticSite.name}">

<ui:row path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite">
    <jsp:attribute name="label"><caaers:message code="LBL_subject.medical.history.site.name" /></jsp:attribute>
    <jsp:attribute name="value">


                    <c:set var="initValue" value="${not empty anatomicSite ? anatomicSite.name : 'Begin typing here...'}"/>
                      <ui:autocompleter path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].codedSite" initialDisplayValue="${initValue}" size="50" required="true" title="Metastatic disease site">
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
                          <jsp:attribute name="optionsJS"> {
                            	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                            		$('assignment.diseaseHistory.metastaticDiseaseSites[${index}].codedSite').value = selectedChoice.id;
                            		if(selectedChoice.id == 110){
                            			AE.slideAndShow('assignment.diseaseHistory.metastaticDiseaseSites[${index}].other');
                            		}else{
                            			$('assignment.diseaseHistory.metastaticDiseaseSites[${index}].other').hide();
                            			$('assignment.diseaseHistory.metastaticDiseaseSites[${index}].otherSite').value='';
                            		}
                            	}
                            }
                         </jsp:attribute>
                      </ui:autocompleter>
                      <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' id="_c2_${index}" onclick="showShowAllTable('_c2_${index}', 'assignmentDOTdiseaseHistoryDOTmetastaticDiseaseSitesOPEN${index}CLOSEDOTcodedSite')">Show All</a>
					  <div id="assignment.diseaseHistory.metastaticDiseaseSites[${index}].other" style="display:none;">
					   &nbsp;
					   <ui:label path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].otherSite" text=""></ui:label>
					   <ui:text path="assignment.diseaseHistory.metastaticDiseaseSites[${index}].otherSite" />
					  </div>
					 
                          
</jsp:attribute>
</ui:row>        
        </c:if>

        </jsp:body>
</chrome:division>    