<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<c:set var="v" value="aeReport.otherCauses[${index}]" />
<ae:fieldGroupDivision fieldGroupFactoryName="otherCause" index="${index}" style="${style}" collapsed="${!empties[v]}">
    <tags:renderRow field="${fieldGroup.fields[0]}"/>
</ae:fieldGroupDivision>
