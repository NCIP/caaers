<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="otherCause" index="${index}" style="${style}">
    <tags:renderRow field="${fieldGroup.fields[0]}"/>
</ae:fieldGroupDivision>
