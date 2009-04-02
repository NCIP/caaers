<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="expanded" type="java.lang.Boolean" %>

<c:set var="v" value="aeReport.otherCauses[${index}]" />
<ae:fieldGroupDivision fieldGroupFactoryName="otherCause" index="${index}" style="${style}" collapsed="${!empties[v] && !expanded}">
    <tags:renderRow field="${fieldGroup.fields[0]}"/>
</ae:fieldGroupDivision>

<script>
    function setTitle_${index}() {
        var titleID = "titleOf_otherCause-" + ${index};
        var name = $("aeReport.otherCauses[${index}].text");
        var value = name.value;
        $(titleID).innerHTML = value;
    }

    setTitle_${index}.defer();

    Event.observe($("aeReport.otherCauses[${index}].text"), "keyup", function() {
        setTitle_${index}();
    });
</script>
