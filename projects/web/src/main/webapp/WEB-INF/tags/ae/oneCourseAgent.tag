<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="agent" type="gov.nih.nci.cabig.caaers.domain.CourseAgent" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>

<ae:fieldGroupDivision fieldGroupFactoryName="courseAgent" index="${index}" style="${style}" enableDelete="true" deleteParams="'agent', ${index}, '_agents'" collapsed="${collapsed}">
    <tags:errors path="aeReport.treatmentInformation.courseAgents[${index}]"/>
    
<%--
    <ui:row path="aeReport.treatmentInformation.courseAgents[${index}].studyAgent">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[0].propertyName}" text="${fieldGroup.fields[0].displayName}" required="false"/></jsp:attribute>
         <jsp:attribute name="value"><ui:select path="${fieldGroup.fields[0].propertyName}" options="${fieldGroup.fields[0].attributes.options}"/></jsp:attribute>
    </ui:row>

    <ui:row path="aeReport.treatmentInformation.courseAgents[${index}].dose.amount">
         <jsp:attribute name="label"><ui:label path="${fieldGroup.fields[1].propertyName}" text="${fieldGroup.fields[1].displayName}" required="true"/></jsp:attribute>
         <jsp:attribute name="value"><ui:text path="${fieldGroup.fields[1].propertyName}"/></jsp:attribute>
    </ui:row>
--%>

 <tags:renderRow field="${fieldGroup.fields[0]}"/>
 <tags:renderRow field="${fieldGroup.fields[1]}"/>
 <tags:renderRow field="${fieldGroup.fields[2]}"/>
 <tags:renderRow field="${fieldGroup.fields[3]}"/>
 <tags:renderRow field="${fieldGroup.fields[4]}"/>
 <tags:renderRow field="${fieldGroup.fields[5]}"/>
 <tags:renderRow field="${fieldGroup.fields[6]}"/>
 <tags:renderRow field="${fieldGroup.fields[7]}"/>

    <div id="modified-dose-fields-${index}">
        <c:forEach begin="8" end="${fn:length(fieldGroup.fields) - 1}" var="i">
            <tags:renderRow field="${fieldGroup.fields[i]}"/>
        </c:forEach>
    </div>

    <script language="JavaScript">
        AE.registerCalendarPopups();
    </script>

</ae:fieldGroupDivision>
