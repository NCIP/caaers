<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<ae:fieldGroupDivision fieldGroupFactoryName="courseAgent" index="${index}" style="${style}">
    <c:forEach begin="0" end="6" var="i">
        <tags:renderRow field="${fieldGroup.fields[i]}"/>
    </c:forEach>
    <div class="row">
        <div class="value">
            <label>
                <input type="checkbox" id="dose-mod-checkbox-${index}" class="dose-mod-checkbox" ${command.aeReport.treatmentInformation.courseAgents[index].doseModified ? "checked='checked'" : ''}/>
                Dose modified?
            </label>
        </div>
    </div>
    <div id="modified-dose-fields-${index}">
        <c:forEach begin="7" end="9" var="i">
            <tags:renderRow field="${fieldGroup.fields[i]}"/>
        </c:forEach>
    </div>
    <c:forEach begin="10" end="${fn:length(fieldGroup.fields) - 1}" var="i">
        <tags:renderRow field="${fieldGroup.fields[i]}"/>
    </c:forEach>
</ae:fieldGroupDivision>
