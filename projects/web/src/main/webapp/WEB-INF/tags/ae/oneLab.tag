<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="lab" type="gov.nih.nci.cabig.caaers.domain.Lab" %>

<c:set var="v" value="aeReport.labs[${index}]" />

<c:set var="fieldGroupName">lab${index}</c:set>
<c:set var="fieldGroup" value="${fieldGroups[fieldGroupName]}"/>

<chrome:division title="&nbsp;${lab.labTerm.term}" cssClass="lab" id="lab-${index}" style="${style}" collapsable="true" enableDelete="true" collapsed="false">
<%--<ae:fieldGroupDivision fieldGroupFactoryName="lab" index="${index}" style="${style}" collapsed="${!empties[v]}">--%>

	 <div class="row">
            <div class="label"><label for="aeReport.labs[${index}].lab-category">Lab category</label></div>
            <div class="value">

              <div class="labCategoryValueDiv">
                <select id="aeReport.labs[${index}].lab-category">
                    <option value="0">Any</option>
                    <c:forEach items="${labCategories}" var="cat">
                        <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                </select>

              </div>
            </div>
    </div>
	
    <div class="row">
     <div class="label">
      <tags:renderLabel field="${fieldGroup.fields[0]}"/>
     </div>
     <div class="value">
       <tags:renderInputs field="${fieldGroup.fields[0]}"/>
     </div>
   </div>  
    
    <tags:renderRow field="${fieldGroup.fields[1]}"  style="display: none">
        <jsp:attribute name="label">
            <label>
                ${fieldGroup.fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>

	<div id="not-microbiology-${index}">
    <tags:renderRow field="${fieldGroup.fields[2]}" />
 
    <c:forEach begin="3" end="8" step="2" var="i">
        <div class="row">
            <div class="label"><tags:renderLabel field="${fieldGroup.fields[i]}"/></div>
            <div class="value">
                <tags:renderInputs field="${fieldGroup.fields[i]}"/>
                <form:label path="${fieldGroup.fields[i+1].propertyName}">date</form:label>
                <tags:renderInputs field="${fieldGroup.fields[i+1]}"/>
            </div>
        </div>
    </c:forEach>
    </div>
    
    <div id="microbiology-${index}" style="display: none">
    <tags:renderRow field="${fieldGroup.fields[9]}" />
    <tags:renderRow field="${fieldGroup.fields[10]}" />    
    <tags:renderRow field="${fieldGroup.fields[11]}" />        
    </div>
    
<%--</ae:fieldGroupDivision>--%>
</chrome:division>



<script>
function setTitleLab_${index}() {
    var titleID = $('titleOf_lab-${index}');
    var select = $("aeReport.labs[${index}].labTerm");
    var value = select.options[select.selectedIndex].text;
    $(titleID).innerHTML = value;
}

Event.observe($("aeReport.labs[${index}].labTerm"), "change", function() {
    setTitleLab_${index}();
});
</script>