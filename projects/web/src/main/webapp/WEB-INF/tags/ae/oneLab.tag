<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="lab" index="${index}" style="${style}">
	
	 <div class="row">
            <div class="label"><label for="aeReport.labs[${index}].lab-category">Lab category</label></div>
            <div class="value">

              <div class="labCategoryValueDiv">
                <select id="aeReport.labs[${index}].lab-category" class="labCategoryClass"
                        onchange="javascript:enableDisableAjaxTable('aeReport.labs[${index}].lab-category','labTermTable${index}','showAllLabs${index}')">
                    <option value="">Any</option>
                    <c:forEach items="${labCategories}" var="cat">
                        <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                </select>

              </div>
            </div>
    </div>
	
    <div class="row">

     <div class="label">

      <input id="labname-${index}" name="testNameType${index}" value="name" type="radio"/>

      <tags:renderLabel field="${fieldGroup.fields[0]}"/>

     </div>

     <div class="value">

       <tags:renderInputs field="${fieldGroup.fields[0]}"/>
         <a id="showAllLabs${index}" href="javascript:showLabsTable($F('aeReport.labs[${index}].lab-category'),'labTermTable${index}')">Show All</a>
        
         <div id="labTermTable${index}"
                 style="position: absolute; display: block; left: 640px; width:400px; z-index:99;">
         </div>

     </div>

    </div>

    <div class="row">

     <div class="label">

      <input id="labother-${index}" name="testNameType${index}" value="other" type="radio"/>

      <tags:renderLabel field="${fieldGroup.fields[1]}"/>

     </div>

     <div class="value">

       <tags:renderInputs field="${fieldGroup.fields[1]}"/>

     </div>

    </div>

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
    
</ae:fieldGroupDivision>
