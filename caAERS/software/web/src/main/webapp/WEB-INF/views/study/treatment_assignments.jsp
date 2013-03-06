<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>

<style>
    input.checkBoxes {
        padding: 2px;
        margin: 2px;
    }
</style>

<script src="<c:url value="/js/ui/ajaxCRUD.js"/>"></script>
<script language="JavaScript" type="text/JavaScript">

    jQuery(document).ready(function() {

        initTextBox();
        jQuery('#otherTAC').click(function() {
            if (jQuery('#otherTAC').is(':checked')) {
                enableTextBox();
            } else {
                disableTextBox();
            }
        })

        Event.observe($('study.otherTreatmentAssignment.visible'), "keyup", function() {
            $('study.otherTreatmentAssignment').value = $('study.otherTreatmentAssignment.visible').value;
        });
/*
        jQuery('#study.otherTreatmentAssignment.visible').change(function() {
            alert(1);
            // jQuery('study.otherTreatmentAssignment').val(jQuery('study.otherTreatmentAssignment.visible').val());
        })
*/
    });

    function enableEdit(_index) {
        var _message = "<caaers:message code="study.treatment_assignments.study.editMessage" />";

        if ( ${command.study.dataEntryStatus ? true : false} && !confirm(_message)) return;
        var _base = "study.treatmentAssignments[" + _index + "]";
//        if ($(_base + '.code')) $(_base + '.code').enable();
        if ($(_base + '.doseLevelOrder')) $(_base + '.doseLevelOrder').enable();
        if ($(_base + '.description')) $(_base + '.description').enable();
        if ($(_base + '.comments')) $(_base + '.comments').enable();
        $('BTN' + _index + 'UPDATE').hide();
        $('BTN' + _index + 'CANCEL').show();
    }

    function disableEdit(_index) {
        var _base = "study.treatmentAssignments[" + _index + "]";
//        if ($(_base + '.code')) $(_base + '.code').disable();
        if ($(_base + '.doseLevelOrder')) $(_base + '.doseLevelOrder').disable();
        if ($(_base + '.description')) $(_base + '.description').disable();
        if ($(_base + '.comments')) $(_base + '.comments').disable();
        $('BTN' + _index + 'CANCEL').hide();
        $('BTN' + _index + 'UPDATE').show();
    }

    function enableTextBox() {
        $('study.otherTreatmentAssignment.visible').enable();
        $('study.otherTreatmentAssignment.visible').value = "${command.study.otherTreatmentAssignment}";
        $('study.otherTreatmentAssignment').value = "${command.study.otherTreatmentAssignment}";
    }

    function disableTextBox() {
        $('study.otherTreatmentAssignment.visible').disable();
        $('study.otherTreatmentAssignment.visible').value = "";
        $('study.otherTreatmentAssignment').value = "";
    }

    function initTextBox() {
        <c:if test="${not empty command.study.otherTreatmentAssignment}">
        $('otherTAC').checked = true;
        </c:if>
        <c:if test="${empty command.study.otherTreatmentAssignment}">
        disableTextBox();
        </c:if>
    }

    ajaxCRUD = new AJAX_CRUD_HELPER();
    function addTA() {
        ajaxCRUD._addItem('TA', null, null, '_TA', null, ${tab.number});
    }

    function fireAction(action, index) {
        if (action == 'delete') {
            // alert(index);
            ajaxCRUD._deleteItem('TA', index, '_TA', ${tab.number});
        } 
    }
</script>

</head>
<body>

<study:summary />
<tags:tabForm tab="${tab}" formName="studyTreatmentAssignmentsForm" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="repeatingFields">
    <p><tags:instructions code="study.study_treatments.top" /></p>

        <div style="padding-left:20px;">
            <tags:button cssClass="foo" id="btn-add-agent" color="blue" value="Add Treatment Assignment" icon="Add" type="button" onclick="addTA();" size="small"/>
            <tags:indicator id="agent_AjaxIndicator" />
            <div id="_TA">
                <c:set var="size" value="${fn:length(command.study.treatmentAssignments)}" />
                <c:forEach items="${command.study.treatmentAssignments}" varStatus="status" var="ta">
                    <c:set var="newIndex" value="${size - (status.index + 1)}" />
                    <%--<c:set var="newIndex" value="${status.index}" />--%>
                    <c:set var="collapsed" value="false" />
                    <c:if test="${!command.study.treatmentAssignments[newIndex].retired}">
                        <study:treatmentAssignment title="${command.study.treatmentAssignments[newIndex].code}" index="${newIndex}" ta="${command.study.treatmentAssignments[newIndex]}" collapsed="false" collapsable="true" readOnly="true"/>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <div style="padding-left:15pt;">
        <chrome:division collapsable="false" collapsed="false" title="Other Treatment Assignment">
            <div class="row">
                <div class="label"><input type="checkbox" name="otherTAC" id="otherTAC"></div>
                <div class="value"><textarea cols="50" rows="3" name="visible" id="study.otherTreatmentAssignment.visible">${command.study.otherTreatmentAssignment}</textarea></div>
            </div>
            <input type="hidden" name="study.otherTreatmentAssignment" id="study.otherTreatmentAssignment" value="${command.study.otherTreatmentAssignment}">
        </chrome:division>
        </div>


    </jsp:attribute>

</tags:tabForm>
</body>
</html>
