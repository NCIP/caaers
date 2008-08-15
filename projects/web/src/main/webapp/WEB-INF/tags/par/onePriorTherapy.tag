<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>


<tags:errors path="priorTherapies[${index}]" />
<tags:renderRow field="${fieldGroup.fields[0]}" />
<tags:renderRow field="${fieldGroup.fields[1]}" />
<tags:renderRow field="${fieldGroup.fields[2]}"/>
<tags:renderRow field="${fieldGroup.fields[3]}"/>

<%--
<ae:fieldGroupDivision fieldGroupFactoryName="priorTherapy" index="${index}" style="${style}">
    <tags:errors path="aeReport.saeReportPriorTherapies[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}" />
    <tags:renderRow field="${fieldGroup.fields[1]}" />
    <tags:renderRow field="${fieldGroup.fields[2]}"/>
    <tags:renderRow field="${fieldGroup.fields[3]}"/>

    <c:forEach begin="${1}" end="${agentCount}" var="s">
         <ae:onePriorTherapyAgent index="${s - 1}" parentIndex="${index}"/>
    </c:forEach>
      
    <div id="pptAgent${index}" style="display: none">
        <tags:listEditorAddButton divisionClass="ptAgent${index}" label="List an Agent" buttonCssClass="ae-list-editor-button"/>
    </div>

</ae:fieldGroupDivision>
 --%>