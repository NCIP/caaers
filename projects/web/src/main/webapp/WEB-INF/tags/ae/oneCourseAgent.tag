<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="courseAgent" index="${index}" style="${style}">
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
</ae:fieldGroupDivision>
