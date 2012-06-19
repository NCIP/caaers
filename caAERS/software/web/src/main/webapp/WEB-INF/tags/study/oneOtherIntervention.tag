<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="otherIntervention" type="gov.nih.nci.cabig.caaers.domain.OtherIntervention" required="true" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<c:if test="${!empty otherIntervention.studyTherapyType}">
    <c:set var="title">${otherIntervention.name}&nbsp;${otherIntervention.description}</c:set>
</c:if>

<chrome:division collapsable="true" collapsed="${collapsed}" id="oi_${index}" title="&nbsp;${title}" enableDelete="true" deleteParams="'removeOtherIntervention', '${index}'">
    <div class="row">
        <div class="label">Intervention type</div>
        <div class="value"><ui:select options="${studyTherapyTypes}" path="study.otherInterventions[${index}].studyTherapyType" readonly="${!empty otherIntervention.id}"/></div>
    </div>
    <div class="row">
        <div class="label">Name</div>
        <div class="value"><ui:text path="study.otherInterventions[${index}].name" size="40"  readonly="${!empty otherIntervention.id && !empty otherIntervention.name}"/></div>
    </div>
    <div class="row">
        <div class="label">Description</div>
        <div class="value"><ui:textarea path="study.otherInterventions[${index}].description" cols="40" rows="3" /></div>
    </div>
</chrome:division>

<script>
    Event.observe('study.otherInterventions[${index}].name', 'keyup', function() {
        updateOtherInterventionBoxTitle('titleOf_oi_${index}', $('study.otherInterventions[${index}].name').value);
    });
</script>