<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@attribute name="index" required="true"%>
<%@attribute name="anatomicSite" type="gov.nih.nci.cabig.caaers.domain.AnatomicSite" required="true"%>

<c:set var="mainGroup">metastatic${index}</c:set>
<c:set var="siteField" value="${fieldGroups[mainGroup].fields[0]}" />

<ae:fieldGroupDivision fieldGroupFactoryName="metastatic" index="${index}" enableDelete="true" deleteParams="'metastaticDiseaseSite', ${index}, '_metastatic'">
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
        <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' onClick="showShowAllTable('_c2_${index}', 'aeReport.diseaseHistory.metastaticDiseaseSites[${index}].codedSite')" id="_c2_${index}">Show All</a>
    </jsp:attribute>
</ui:row>
</ae:fieldGroupDivision>



<%--
    <table width="100%" border="1">
 			<tr>
  				<td width="99%">
					<ui:autocompleter path="${siteField.propertyName}" readonly="true" 
						displayNamePath="${siteField.propertyName}.name" />
					<c:if test="${anatomicSite.id eq 110}">
					--%>
<%-- Other, Specify--%>
<%--
					<c:set var="otherSiteField" value="${fieldGroups[mainGroup].fields[1]}" />
					<c:if test="${otherSiteField.required or otherSiteField.attributes.mandatory}"><tags:requiredIndicator/></c:if>&nbsp;<ui:text path="${otherSiteField.propertyName}" required="true"/>
					</c:if>
  				</td>
  				<td>
					<a href="#anchorMetastaticDiseases" onClick="mHistory.removeDetails('metastaticDiseaseSite', ${index}, 'anchorMetastaticDiseases')">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table> 
--%>

<%-- 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="metastatic" index="${index}" style="${style}">
    <tags:errors path="aeReport.diseaseHistory.metastaticDiseaseSites[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}"
                    extraParams="<a id=\"showAll${index}\" href=\"javascript:showDiseaseSiteTable('metastaticDiseaseSitesTable${index}','metastaticDiseaseSitesTable${index}-outer')\">Show All</a>" >
        <jsp:attribute name="label">
            <label>
                ${fieldGroup.fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>

<div id="metastaticDiseaseSitesTable${index}-outer"
                 style="position: absolute; display: none; left: 640px; width:400px; z-index:99;">
<table width="100%" class="eXtremeTable" frame="border" border-color="blue" bgcolor="white">
<tbody>
<tr class="titleRow">
  <td align="left" class="title">Select a metastatic disease site :</td><td width="20px"><a href="javascript:hideShowAllTable('metastaticDiseaseSitesTable${index}-outer')">
       <img src="/caaers/images/rule/window-close.gif" id="close-image"/>
      </a></td>
</tr>
<tr>
<td colspan="2">
        <div id="metastaticDiseaseSitesTable${index}"  />
        
</td>
</tr>
</tbody>
</table>
     
</div>




    <tags:renderRow field="${fieldGroup.fields[1]}" style="display: none">
        <jsp:attribute name="label">
            <label>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
</ae:fieldGroupDivision>
--%>
