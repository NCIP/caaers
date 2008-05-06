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
