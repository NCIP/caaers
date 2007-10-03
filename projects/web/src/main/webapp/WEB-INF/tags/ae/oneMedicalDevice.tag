<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="medicalDevice" index="${index}" style="${style}">
    <tags:errors path="aeReport.medicalDevices[${index}]"/>
    <c:forEach items="${fieldGroup.fields}" var="field">
            <tags:renderRow field="${field}"/>
     </c:forEach>
</ae:fieldGroupDivision>
