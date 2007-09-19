<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="conmed" index="${index}" style="${style}">
    <tags:errors path="aeReport.concomitantMedications[${index}]"/>
    <tags:renderRow field="${fieldGroup.fields[0]}" />
</ae:fieldGroupDivision>
