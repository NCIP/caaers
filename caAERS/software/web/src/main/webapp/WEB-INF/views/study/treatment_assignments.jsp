<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>

<script src="<c:url value="/js/ui/ajaxCRUD.js"/>"></script>
<script language="JavaScript" type="text/JavaScript">

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
                    <c:set var="collapsed" value="false" />
                        <study:treatmentAssignment title="${command.study.treatmentAssignments[newIndex].code}" index="${newIndex}" ta="${command.study.treatmentAssignments[newIndex]}" collapsed="false" />
                </c:forEach>
            </div>
        </div>

    </jsp:attribute>

</tags:tabForm>
</body>
</html>